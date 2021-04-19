package com.zhangys.service;

import com.zhangys.entity.UserEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2017/3/11.
 */

@Service("UserService")
public class UserService {

//    @Resource(name = "userDao")

    public boolean createUser(UserEntity userEntity){
        boolean result = false;
        try{
            UserEntity user = getUserByName(userEntity.getLoginName());
            if(user == null){
                result = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void deleteUser(UserEntity userEntity){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateUser(UserEntity userEntity){
        try{

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<UserEntity> getAllUser(){
        List<UserEntity> result = new ArrayList<UserEntity>();
        try{

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public UserEntity getUserByName(String loginName){
        UserEntity userEntity = null;
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return userEntity;
    }
}
