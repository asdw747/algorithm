package com.zhangys.service.impl;

import com.zhangys.dao.UserDao;
import com.zhangys.entity.UserEntity;
import com.zhangys.service.IUserService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2017/3/11.
 */

@Service("UserService")
public class UserService implements IUserService{

//    @Resource(name = "userDao")
    private UserDao dao;

    @Override
    public boolean createUser(UserEntity userEntity){
        boolean result = false;
        try{
            UserEntity user = getUserByName(userEntity.getLoginName());
            if(user == null){
                dao.create(userEntity);
                result = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteUser(UserEntity userEntity){
        try{
            dao.delete(userEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(UserEntity userEntity){
        try{
            dao.update(userEntity);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<UserEntity> getAllUser(){
        List<UserEntity> result = new ArrayList<UserEntity>();
        try{
            result = dao.get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public UserEntity getUserByName(String loginName){
        UserEntity userEntity = null;
        try{
            userEntity = dao.findByName(loginName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userEntity;
    }
}
