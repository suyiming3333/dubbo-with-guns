package com.corn.gateway.modular.film.vo;


import com.corn.film.vo.BannerVO;
import com.corn.film.vo.FilmInfo;
import com.corn.film.vo.FilmVO;
import lombok.Data;

import java.util.List;

@Data
public class FilmIndexVO {

    private List<BannerVO> banners;
    private FilmVO hotFilms;
    private FilmVO soonFilms;
    private List<FilmInfo> boxRanking;
    private List<FilmInfo> expectRanking;
    private List<FilmInfo> top100;

}
