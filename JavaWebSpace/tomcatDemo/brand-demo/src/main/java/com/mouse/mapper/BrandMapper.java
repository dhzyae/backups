package com.mouse.mapper;

import com.mouse.pojo.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {
    @Select("select * from tb_brand")
    List<Brand> selectAll();

}
