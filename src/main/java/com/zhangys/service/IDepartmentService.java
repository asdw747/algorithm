package com.zhangys.service;

import com.zhangys.entity.DepartmentEntity;

import java.util.List;

/**
 * Created by 123 on 2017/3/11.
 */
public interface IDepartmentService {

    DepartmentEntity getDptByName(String dptName);

    List<DepartmentEntity> getAllDpt();

    boolean createDpt(DepartmentEntity departmentEntity);

    void deleteDpt(DepartmentEntity departmentEntity);

    void updateDpt(DepartmentEntity departmentEntity);

}
