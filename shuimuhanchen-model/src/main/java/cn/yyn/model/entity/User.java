package cn.yyn.model.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 用户类先设置自己一个人
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User implements Serializable {

    private String username;

    private String password;

}
