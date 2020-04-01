package cn.yyn.service;

import cn.yyn.model.dto.ArticleCommentDto;
import cn.yyn.model.entity.CommentInfo;

import java.util.List;

/**
 * 评论Service
 *
 */
public interface CommentService {

    void addComment(CommentInfo commentInfo);

    void addArticleComment(ArticleCommentDto articleCommentDto);

    void deleteCommentById(Long id);

    void deleteArticleCommentById(Long id);

    List<CommentInfo> listAllComment();

    List<ArticleCommentDto> listAllArticleCommentById(Long id);

}
