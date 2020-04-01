package cn.yyn.service;

import cn.yyn.model.dto.ArticleDto;
import cn.yyn.model.dto.ArticleWithPictureDto;

import java.util.List;

/**
 * 文章Service
 *
 */
public interface ArticleService {

    void addArticle(ArticleDto articleDto);

    void deleteArticleById(Long id);

    void updateArticle(ArticleDto articleDto);

    void updateArticleCategory(Long articleId, Long categoryId);

    ArticleDto getOneById(Long id);

    List<ArticleWithPictureDto> listAll();

    List<ArticleWithPictureDto> listByCategoryId(Long id);

    List<ArticleWithPictureDto> listLastest();
}
