package cn.yyn.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ArticlePicture implements Serializable {
    private Long id;

    private Long articleId;

    private String pictureUrl;

    private Date createBy;

    private Date modifiedBy;

}