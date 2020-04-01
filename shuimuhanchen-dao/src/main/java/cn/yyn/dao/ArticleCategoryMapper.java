package cn.yyn.dao;

import cn.yyn.model.entity.ArticleCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCategoryMapper {

    int insertArticleCategory(@Param("articleCategory")ArticleCategory articleCategory);

    List<ArticleCategory> selectByCategoryId(@Param("categoryId")Long categoryId);

    int updateCategoryByArticleId(@Param("articleCategory")ArticleCategory articleCategory, @Param("articleId")Long articleId);

}