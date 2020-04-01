package cn.yyn.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CategoryInfo implements Serializable {

    private Long id;

    private String name;

    private Byte number;

    private Date createBy;

    private Date modifiedBy;

    private Boolean isEffective;

}