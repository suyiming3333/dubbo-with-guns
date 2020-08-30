package com.corn.gateway.modular.cinema.vo;


import com.corn.cinema.vo.*;
import lombok.Data;

import java.util.List;

@Data
public class CinemaConditionResponseVO {

    private List<BrandVO> brandList;
    private List<AreaVO> areaList;
    private List<HallTypeVO> halltypeList;

}
