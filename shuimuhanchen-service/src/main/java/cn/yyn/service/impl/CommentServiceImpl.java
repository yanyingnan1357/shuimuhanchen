package cn.yyn.service.impl;

import cn.yyn.model.dto.ArticleCommentDto;
import cn.yyn.model.entity.CommentInfo;
import cn.yyn.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 留言/评论Service实现类
 *
 * @author:yyn
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Override
    public void addComment(CommentInfo commentInfo) {

    }

    @Override
    public void addArticleComment(ArticleCommentDto articleCommentDto) {

    }

    @Override
    public void deleteCommentById(Long id) {

    }

    @Override
    public void deleteArticleCommentById(Long id) {

    }

    @Override
    public List<CommentInfo> listAllComment() {
        return null;
    }

    @Override
    public List<ArticleCommentDto> listAllArticleCommentById(Long id) {
        return null;
    }
}
