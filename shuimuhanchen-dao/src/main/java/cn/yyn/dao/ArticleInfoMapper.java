package cn.yyn.dao;

import cn.yyn.model.entity.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleInfoMapper {

    int insertArticleInfo(@Param("articleInfo")ArticleInfo articleInfo);

    ArticleInfo selectArticleInfoById(@Param("id")Long id);

    int updateArticleInfoById(@Param("articleInfo")ArticleInfo articleInfo, @Param("id")Long id);

}