package cn.yyn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章分类传输对象
 * 说明：关联了tbl_article_category和tbl_category_info两张表的数据
 *
 * @author:yyn
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategoryDto implements Serializable {
    //  tbl_article_category表基础字段
    private Long id;            // tbl_article_category表主键
    private Long categoryId;    // 分类信息ID
    private Long articleId;     // 文章ID

    // tbl_category_info表基础字段
    private String name;        // 分类信息显示名称
    private Byte number;        // 该分类下对应的文章数量

}
