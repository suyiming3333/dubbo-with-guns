package com.corn.gateway.modular.cinema.vo;


import com.corn.cinema.vo.*;
import lombok.Data;

import java.util.List;

@Data
public class CinemaFieldsResponseVO {

    private CinemaInfoVO cinemaInfo;
    private List<FilmInfoVO> filmList;

}
