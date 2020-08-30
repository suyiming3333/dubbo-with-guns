package com.corn.gateway.modular.film.vo;


import com.corn.film.vo.CatVO;
import com.corn.film.vo.SourceVO;
import com.corn.film.vo.YearVO;
import lombok.Data;

import java.util.List;

@Data
public class FilmConditionVO {

    private List<CatVO> catInfo;
    private List<SourceVO> sourceInfo;
    private List<YearVO> yearInfo;

}
