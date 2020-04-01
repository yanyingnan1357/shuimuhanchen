package cn.yyn.dao;

import cn.yyn.model.entity.ArticleComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentMapper {

    int insertArticleComment(@Param("articleComment")ArticleComment articleComment);

    List<ArticleComment> selectCommentByArticleId(@Param("commentId")Long articleId);

}