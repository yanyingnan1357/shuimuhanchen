package cn.yyn.dao;

import cn.yyn.model.entity.ArticleContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleContentMapper {

    int insertArticleContent(@Param("articleContent")ArticleContent articleContent);

    ArticleContent selectContentByArticleId(@Param("articleId")Long articleId);

    int updateContentByArticleId(@Param("articleContent")ArticleContent articleContent, @Param("articleId")Long articleId);
}