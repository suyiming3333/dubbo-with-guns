package com.corn.gateway.modular.cinema.vo;

import com.corn.cinema.vo.*;
import lombok.Data;

@Data
public class CinemaFieldResponseVO {

    private CinemaInfoVO cinemaInfo;
    private FilmInfoVO filmInfo;
    private HallInfoVO hallInfo;

}
