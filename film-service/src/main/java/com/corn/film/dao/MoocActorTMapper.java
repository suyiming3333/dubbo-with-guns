package com.corn.film.dao;

import com.corn.film.model.MoocActorT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.corn.film.vo.ActorVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author jiangzh
 * @since 2018-08-26
 */
public interface MoocActorTMapper extends BaseMapper<MoocActorT> {

    List<ActorVO> getActors(@Param("filmId") String filmId);

}
