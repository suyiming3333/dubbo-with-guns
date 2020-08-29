package com.corn.film.dao;

import com.corn.film.model.MoocFilmT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.corn.film.vo.FilmDetailVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author jiangzh
 * @since 2018-08-26
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {

    FilmDetailVO getFilmDetailByName(@Param("filmName") String filmName);

    FilmDetailVO getFilmDetailById(@Param("uuid") String uuid);

}
