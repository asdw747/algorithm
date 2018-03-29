package com.zhangys.dao;

import com.zhangys.dao.common.AbstractHibernateDao;
import com.zhangys.entity.DepartmentEntity;

/**
 * Created by 123 on 2017/3/11.
 */
//@Repository("departmentDao")
public class DepartmentDao extends AbstractHibernateDao<DepartmentEntity> {

    public DepartmentDao(){ super(DepartmentEntity.class); }

    public DepartmentEntity findByName(String depName) throws Exception{
        /*
        Session session = openSession();
        DepartmentEntity departmentEntity = null;
        try{
            //qbc查询方法
            Criteria resultCr = session.createCriteria(DepartmentEntity.class)
                    .add(Restrictions.eq("depName", depName))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<DepartmentEntity> list = resultCr.list();
            if(null != list && list.size() > 0){
                departmentEntity = list.get(0);
            }
        }catch(Exception e){
            throw e;
        }finally {
            session.close();
        }
        return departmentEntity;
        */
        return null;
    }

}
