package com.example.domain.bo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/24 21:49
 */
@Entity
@Data
@Table(name="sys_user")
public class UserBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = true, unique = true)
    @Pattern(regexp = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}",message = "格式错误")
    private String email;

    @Column(nullable = true, unique = false)
    private String nickName;

    @Column(nullable = true)
    private String phone;

}
