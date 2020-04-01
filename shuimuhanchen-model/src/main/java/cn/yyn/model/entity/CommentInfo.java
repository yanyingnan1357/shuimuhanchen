package cn.yyn.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CommentInfo implements Serializable {
    private Long id;

    private String content;

    private Date createBy;

    private String email;

    private String name;

    private String ip;

    private Boolean isEffective;

}