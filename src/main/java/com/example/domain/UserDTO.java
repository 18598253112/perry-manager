package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/24 22:19
 */
@Data
public class UserDTO implements Serializable {

    private Long id;

    private String userName;

    @JsonIgnore
    private String passWord;

    private String email;

    private String nickName;

    private String phone;

}
