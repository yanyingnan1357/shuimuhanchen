package cn.yyn.dao;

import cn.yyn.model.entity.CategoryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryInfoMapper {

    int insertCategoryInfo(@Param("categoryInfo")CategoryInfo categoryInfo);

    CategoryInfo selectCategoryInfoById(@Param("id")Long id);

    int updateCategoryInfoById(@Param("categoryInfo")CategoryInfo categoryInfo, @Param("id")Long id);

}