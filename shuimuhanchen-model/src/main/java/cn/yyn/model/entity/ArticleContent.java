package cn.yyn.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ArticleContent implements Serializable {
    private Long id;

    private String content;

    private Long articleId;

    private Date createBy;

    private Date modifiedBy;

}