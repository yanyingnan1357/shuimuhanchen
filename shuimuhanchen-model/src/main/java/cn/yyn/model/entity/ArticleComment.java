package cn.yyn.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ArticleComment implements Serializable {
    private Long id;

    private Long articleId;

    private Long commentId;

    private Date createBy;

    private Boolean isEffective;

}