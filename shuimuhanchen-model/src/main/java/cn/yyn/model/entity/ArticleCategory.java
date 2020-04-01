package cn.yyn.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ArticleCategory implements Serializable {
    private Long id;

    private Long categoryId;

    private Long articleId;

    private Date createBy;

    private Date modifiedBy;

    private Boolean isEffective;

}