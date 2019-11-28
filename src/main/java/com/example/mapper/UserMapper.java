package com.example.mapper;

import com.example.domain.UserDTO;
import com.example.domain.bo.UserBO;
import org.mapstruct.Mapper;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/24 22:25
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, UserBO>{

}
