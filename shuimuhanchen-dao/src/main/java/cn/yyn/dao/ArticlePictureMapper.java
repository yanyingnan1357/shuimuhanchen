package cn.yyn.dao;

import cn.yyn.model.entity.ArticlePicture;
import org.apache.ibatis.annotations.Param;

public interface ArticlePictureMapper {

    int insertArticlePicture(@Param("articlePicture")ArticlePicture articlePicture);

    ArticlePicture selectPictureByArticleId(@Param("articleId")Long articleId);

    int updatePictureByArticleId(@Param("articlePicture")ArticlePicture articlePicture, @Param("articleId")Long articleId);

}