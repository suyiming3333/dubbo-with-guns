package com.corn.gateway.modular.cinema.vo;

import com.corn.cinema.vo.CinemaVO;
import lombok.Data;

import java.util.List;

@Data
public class CinemaListResponseVO {

    private List<CinemaVO> cinemas;

}
