package com.example.repository;

import com.example.domain.bo.UserBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/24 21:07
 */
public interface UserRepository extends JpaRepository<UserBO, Long> ,JpaSpecificationExecutor {

    @Query(value = "select * from sys_user u where u.user_name = ?1 and u.pass_word = ?2 ",nativeQuery = true)
    UserBO findUserByUserNameAndPassword(String userName, String password);

}

