package cn.yyn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 带题图信息的文章基础信息分装类
 *
 * @author:yyn
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleWithPictureDto implements Serializable {
    // tbl_article_info基础字段
    private Long id;                    // ArticleInfo表主键
    private String title;               // 文章标题
    private String summary;             // 文章简介
    private Boolean isTop;              // 文章是否置顶
    private Integer traffic;            // 文章阅读量

    // tbl_article_picture基础字段
    private Long articlePictureId;      // ArticlePicture主键
    private String pictureUrl;          // 文章题图URL

}
