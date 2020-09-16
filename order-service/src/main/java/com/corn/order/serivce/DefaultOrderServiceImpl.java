package com.corn.order.serivce;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corn.cinema.CinemaServiceAPI;
import com.corn.cinema.vo.FilmInfoVO;
import com.corn.cinema.vo.OrderQueryVO;
import com.corn.order.OrderServiceAPI;
import com.corn.order.dao.MoocOrderTMapper;
import com.corn.order.model.MoocOrderT;
import com.corn.order.util.FTPUtil;

import com.corn.order.vo.OrderVO;
import com.stylefeng.guns.core.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
@Service(interfaceClass = OrderServiceAPI.class,group = "default")
public class DefaultOrderServiceImpl implements OrderServiceAPI {

    @Autowired
    private MoocOrderTMapper moocOrderTMapper;

    @Reference(interfaceClass = CinemaServiceAPI.class,check = true)
    private CinemaServiceAPI cinemaServiceAPI;

    @Autowired
    private FTPUtil ftpUtil;

    // 验证是否为真实的座位编号
    @Override
    @Compensable(confirmMethod = "confirmIsTrueSeats", cancelMethod = "cancelIsTrueSeats", transactionContextEditor = DubboTransactionContextEditor.class)
    public boolean isTrueSeats(String fieldId, String seats) {
        if(seats.equals("1,2,3")){
            throw new IllegalArgumentException();
        }else{
            return true;
        }
    }

    public boolean confirmIsTrueSeats(String fieldId, String seats) {
        System.out.println("this is confirmIsTrueSeats");
        return true;
    }
    public boolean cancelIsTrueSeats(String fieldId, String seats) {
        System.out.println("this is cancelIsTrueSeats");
        return true;
    }

    public boolean confirmIsNotSold(String fieldId, String seats) {
        System.out.println("this is confirmIsNotSold");
        return true;
    }
    public boolean cancelIsNotSold(String fieldId, String seats) {
        System.out.println("this is cancelIsNotSold");
        return true;
    }
//    public boolean isTrueSeats(String fieldId, String seats) {
//        // 根据FieldId找到对应的座位位置图
//        String seatPath = moocOrderTMapper.getSeatsByFieldId(fieldId);
//
//        // 读取位置图，判断seats是否为真
//        String fileStrByAddress = ftpUtil.getFileStrByAddress(seatPath);
//
//        // 将fileStrByAddress转换为JSON对象
//        JSONObject jsonObject = JSONObject.parseObject(fileStrByAddress);
//        // seats=1,2,3   ids="1,3,4,5,6,7,88"
//        String ids = jsonObject.get("ids").toString();
//
//        // 每一次匹配上的，都给isTrue+1
//        String[] seatArrs = seats.split(",");
//        String[] idArrs = ids.split(",");
//        int isTrue = 0;
//        for(String id : idArrs){
//            for(String seat : seatArrs){
//                if(seat.equalsIgnoreCase(id)){
//                    isTrue++;
//                }
//            }
//        }
//
//        // 如果匹配上的数量与已售座位数一致，则表示全都匹配上了
//        if(seatArrs.length == isTrue){
//            return true;
//        }else{
//            return false;
//        }
//    }

    // 判断是否为已售座位
    @Override
    @Compensable(confirmMethod = "confirmIsNotSold", cancelMethod = "cancelIsNotSold", transactionContextEditor = DubboTransactionContextEditor.class)
    public boolean isNotSoldSeats(String fieldId, String seats) {
        if(seats.equals("4,5")){
            throw new IllegalArgumentException();
        }else{
            return true;
        }
    }
//    public boolean isNotSoldSeats(String fieldId, String seats) {
//
//        EntityWrapper entityWrapper = new EntityWrapper();
//        entityWrapper.eq("field_id",fieldId);
//        //获取有效order里预定的座位
//        List<MoocOrderT> list = moocOrderTMapper.selectList(entityWrapper);
//        String[] seatArrs = seats.split(",");
//        // 有任何一个编号匹配上，则直接返回失败
//        for(MoocOrderT moocOrderT : list){
//            String[] ids = moocOrderT.getSeatsIds().split(",");
//            for(String id : ids){
//                for(String seat : seatArrs){
//                    if(id.equalsIgnoreCase(seat)){
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }

    // 创建新的订单
    @Override
    public OrderVO saveOrderInfo(
            Integer fieldId, String soldSeats, String seatsName, Integer userId) {

        // 编号
        String uuid = UUIDUtil.genUuid();

        // 影片信息
        FilmInfoVO filmInfoVO = cinemaServiceAPI.getFilmInfoByFieldId(fieldId);
        Integer filmId = Integer.parseInt(filmInfoVO.getFilmId());

        // 获取影院信息
        OrderQueryVO orderQueryVO = cinemaServiceAPI.getOrderNeeds(fieldId);
        Integer cinemaId = Integer.parseInt(orderQueryVO.getCinemaId());
        double filmPrice = Double.parseDouble(orderQueryVO.getFilmPrice());

        // 求订单总金额  // 1,2,3,4,5
        int solds = soldSeats.split(",").length;
        double totalPrice = getTotalPrice(solds,filmPrice);

        MoocOrderT moocOrderT = new MoocOrderT();
        moocOrderT.setUuid(uuid);
        moocOrderT.setSeatsName(seatsName);
        moocOrderT.setSeatsIds(soldSeats);
        moocOrderT.setOrderUser(userId);
        moocOrderT.setOrderPrice(totalPrice);
        moocOrderT.setFilmPrice(filmPrice);
        moocOrderT.setFilmId(filmId);
        moocOrderT.setFieldId(fieldId);
        moocOrderT.setCinemaId(cinemaId);
        moocOrderT.setCinemaName(orderQueryVO.getCinemaName());
        moocOrderT.setFilmName(filmInfoVO.getFilmName());

        Integer insert = moocOrderTMapper.insert(moocOrderT);
        if(insert>0){
            // 返回查询结果
            OrderVO orderVO = moocOrderTMapper.getOrderInfoById(uuid);
            if(orderVO == null || orderVO.getOrderId() == null){
                log.error("订单信息查询失败,订单编号为{}",uuid);
                return null;
            }else {
                return orderVO;
            }
        }else{
            // 插入出错
            log.error("订单插入失败");
            return null;
        }
    }

    private static double getTotalPrice(int solds,double filmPrice){
        BigDecimal soldsDeci = new BigDecimal(solds);
        BigDecimal filmPriceDeci = new BigDecimal(filmPrice);

        BigDecimal result = soldsDeci.multiply(filmPriceDeci);

        // 四舍五入，取小数点后两位
        BigDecimal bigDecimal = result.setScale(2, RoundingMode.HALF_UP);

        return bigDecimal.doubleValue();
    }


    @Override
    public Page<OrderVO> getOrderByUserId(Integer userId,Page<OrderVO> page) {
        Page<OrderVO> result = new Page<>();
        if(userId == null){
            log.error("订单查询业务失败，用户编号未传入");
            return null;
        }else{
            /**
             * Table 'guns_order.mooc_field_t' doesn't exist
             * 由于每个服务对应各自数据库，因此不能直接链表查询
             * 解决方法：
             * 1、电影场次信息如开始时间、结束时间冗余到订单表(优先考虑)
             * 2、调用cinemaapi获取所有场次信息然后组装
             */
            List<OrderVO> ordersByUserId = moocOrderTMapper.getOrdersByUserId(userId,page);
            if(ordersByUserId==null && ordersByUserId.size()==0){
                result.setTotal(0);
                result.setRecords(new ArrayList<>());
                return result;
            }else{
                // 获取订单总数
                EntityWrapper<MoocOrderT> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("order_user",userId);
                Integer counts = moocOrderTMapper.selectCount(entityWrapper);
                // 将结果放入Page
                result.setTotal(counts);
                result.setRecords(ordersByUserId);

                return result;
            }
        }
    }

    // 根据放映查询，获取所有的已售座位
    /*

        1  1,2,3,4
        1  5,6,7

     */
    @Override
    public String getSoldSeatsByFieldId(Integer fieldId) {
        if(fieldId == null){
            log.error("查询已售座位错误，未传入任何场次编号");
            return "";
        }else{
            String soldSeatsByFieldId = moocOrderTMapper.getSoldSeatsByFieldId(fieldId);
            return soldSeatsByFieldId;
        }
    }

    @Override
    public OrderVO getOrderInfoById(String orderId) {

        OrderVO orderInfoById = moocOrderTMapper.getOrderInfoById(orderId);

        return orderInfoById;
    }

    @Override
    public boolean paySuccess(String orderId) {
        MoocOrderT moocOrderT = new MoocOrderT();
        moocOrderT.setUuid(orderId);
        moocOrderT.setOrderStatus(1);

        Integer integer = moocOrderTMapper.updateById(moocOrderT);
        if(integer>=1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean payFail(String orderId) {
        MoocOrderT moocOrderT = new MoocOrderT();
        moocOrderT.setUuid(orderId);
        moocOrderT.setOrderStatus(2);

        Integer integer = moocOrderTMapper.updateById(moocOrderT);
        if(integer>=1){
            return true;
        }else{
            return false;
        }
    }
}
