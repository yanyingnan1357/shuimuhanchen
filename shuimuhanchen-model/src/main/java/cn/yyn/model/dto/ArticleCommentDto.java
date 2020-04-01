package cn.yyn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章评论信息
 * 说明：关联了tbl_comment和tbl_article_comment两张表的信息
 *
 * @author:yyn
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentDto implements Serializable {
    // tbl_comment基础字段
    private Long id;                // 评论id
    private String content;         // 评论内容
    private String name;            // 用户自定义的显示名称
    private String email;
    private String ip;
    private Date createBy;          // 创建时间

    // tbl_article_comment基础字段
    private Long articleCommentId;  // tbl_article_comment主键
    private Long articleId;         // 文章ID

}
