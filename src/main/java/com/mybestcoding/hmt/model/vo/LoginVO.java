package com.mybestcoding.hmt.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By lixinkai on 2020/12/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private String username;
    private String password;
    private boolean rememberMe;
}
