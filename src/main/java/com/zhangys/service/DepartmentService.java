package com.zhangys.service;

import com.zhangys.entity.DepartmentEntity;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by 123 on 2017/3/11.
 */

@Service("DepartmentService")
public class DepartmentService {


    public DepartmentEntity getDptByName(String dptName){
        DepartmentEntity departmentEntity = null;
        try{

        }catch(Exception e){
            e.printStackTrace();
        }
        return departmentEntity;
    }

    public List<DepartmentEntity> getAllDpt(){
        List<DepartmentEntity> list = null;
        try{

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean createDpt(DepartmentEntity departmentEntity){
        boolean result = false;
        try{
            if(getDptByName(departmentEntity.getDepName()) == null){

                result = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void deleteDpt(DepartmentEntity departmentEntity){
        try{

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateDpt(DepartmentEntity departmentEntity){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
