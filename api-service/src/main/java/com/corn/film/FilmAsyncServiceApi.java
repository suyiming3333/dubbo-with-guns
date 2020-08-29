package com.corn.film;


import com.corn.film.vo.ActorVO;
import com.corn.film.vo.FilmDescVO;
import com.corn.film.vo.ImgVO;

import java.util.List;

public interface FilmAsyncServiceApi {

    // 获取影片描述信息
    FilmDescVO getFilmDesc(String filmId);

    // 获取图片信息
    ImgVO getImgs(String filmId);

    // 获取导演信息
    ActorVO getDectInfo(String filmId);

    // 获取演员信息
    List<ActorVO> getActors(String filmId);

}
