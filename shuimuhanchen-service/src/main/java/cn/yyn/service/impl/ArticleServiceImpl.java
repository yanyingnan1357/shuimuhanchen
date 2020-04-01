package cn.yyn.service.impl;

import cn.yyn.model.dto.ArticleDto;
import cn.yyn.model.dto.ArticleWithPictureDto;
import cn.yyn.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章Service实现类
 *
 * @author:yyn
 */
@Service
public class ArticleServiceImpl implements ArticleService {


	@Override
	public void addArticle(ArticleDto articleDto) {

	}

	@Override
	public void deleteArticleById(Long id) {

	}

	@Override
	public void updateArticle(ArticleDto articleDto) {

	}

	@Override
	public void updateArticleCategory(Long articleId, Long categoryId) {

	}

	@Override
	public ArticleDto getOneById(Long id) {
		return null;
	}

	@Override
	public List<ArticleWithPictureDto> listAll() {
		return null;
	}

	@Override
	public List<ArticleWithPictureDto> listByCategoryId(Long id) {
		return null;
	}

	@Override
	public List<ArticleWithPictureDto> listLastest() {
		return null;
	}
}
