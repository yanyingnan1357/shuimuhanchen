package cn.yyn.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SysLog implements Serializable {

    private Long id;

    private String ip;

    private Date createBy;

    private String remark;

    private String operateUrl;

    private String operateBy;
}