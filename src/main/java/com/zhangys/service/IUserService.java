package com.zhangys.service;

import com.zhangys.entity.UserEntity;

import java.util.List;

/**
 * Created by 123 on 2017/3/11.
 */
public interface IUserService {

    List<UserEntity> getAllUser();

    UserEntity getUserByName(String loginName);

    boolean createUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity);

}
