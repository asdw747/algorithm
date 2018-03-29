package com.zhangys.service.impl;

import com.zhangys.dao.DepartmentDao;
import com.zhangys.entity.DepartmentEntity;
import com.zhangys.service.IDepartmentService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by 123 on 2017/3/11.
 */

@Service("DepartmentService")
public class DepartmentService implements IDepartmentService{

//    @Resource( name = "departmentDao" )
    private DepartmentDao dao;

    @Override
    public DepartmentEntity getDptByName(String dptName){
        DepartmentEntity departmentEntity = null;
        try{
            departmentEntity = dao.findByName(dptName);
        }catch(Exception e){
            e.printStackTrace();
        }
        return departmentEntity;
    }

    @Override
    public List<DepartmentEntity> getAllDpt(){
        List<DepartmentEntity> list = null;
        try{
            list = dao.get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean createDpt(DepartmentEntity departmentEntity){
        boolean result = false;
        try{
            if(getDptByName(departmentEntity.getDepName()) == null){
                dao.create(departmentEntity);
                result = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void deleteDpt(DepartmentEntity departmentEntity){
        try{
            dao.delete(departmentEntity);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateDpt(DepartmentEntity departmentEntity){
        try{
            dao.update(departmentEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
