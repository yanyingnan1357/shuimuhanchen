package cn.yyn.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ArticleInfo implements Serializable {
    private Long id;

    private String title;

    private String summary;

    private Boolean isTop;

    private Integer traffic;

    private Date createBy;

    private Date modifiedBy;

}