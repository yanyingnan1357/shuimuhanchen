package cn.yyn.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章信息类
 * 说明：关联了tbl_article_info/tbl_article_content/tbl_article_category/tbl_category_info/tbl_article_picture五张表的基础字段
 *
 * @author:yyn
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto implements Serializable {

    // tbl_article_info基础字段
    private Long id;                // 主键
    private String title;           // 文章标题
    private String summary;         // 文章简介
    private Boolean isTop;          // 文章是否置顶
    private Integer traffic;        // 文章浏览量
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createBy;          // 文章创建时间

    // tbl_article_content基础字段
    private Long articleContentId;  // ArticleContent表主键
    private String content;         // 文章内容

    // tbl_category_info基础字段
    private Long categoryId;        // 分类ID
    private String categoryName;    // 分类名称
    private Byte categoryNumber;    // 分类对应的数量

    // tbl_article_category基础字段
    private Long articleCategoryId; // ArticleCategory表主键

    // tbl_article_picture基础字段
    private Long articlePictureId;  // ArticlePicture表主键
    private String pictureUrl;      // 文章题图url

}
