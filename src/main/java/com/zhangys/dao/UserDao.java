package com.zhangys.dao;

import com.zhangys.dao.common.AbstractHibernateDao;
import com.zhangys.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by 123 on 2017/3/7.
 */
//@Repository("userDao")
public class UserDao extends AbstractHibernateDao<UserEntity> {

    public UserDao() {
        super(UserEntity.class);
    }

    public UserEntity findByName(String userName) throws Exception {
        /*
        Session curSession = openSession();
        try {
            Criteria resultCr = curSession.createCriteria(UserEntity.class)
                    .add(Restrictions.eq("loginName", userName))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//            resultCr.setMaxResults(50);
            List<UserEntity> list = resultCr.list();
            if(null != list && list.size() > 0){
                return list.get(0);
            }else {
                return null;
            }
        }catch (Exception e){
            throw e;
        }finally {
            curSession.close();
        }*/
        return null;
    }

    public UserEntity getById(long userId) {
        /*
        Session curSession = openSession();
        UserEntity userEntity = null;
        try {
            Criteria resultCr = curSession.createCriteria(UserEntity.class)
//                    .setFetchMode("department", FetchMode.JOIN)
//                    .setFetchMode("authorization", FetchMode.JOIN)
//                    .setFetchMode("userGroup", FetchMode.JOIN)
//                    .setFetchMode("accessKey", FetchMode.JOIN)
                    .add(Restrictions.eq("id", userId))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            userEntity = (UserEntity) resultCr.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            curSession.close();
        }
        return userEntity;
        */
        return null;
    }

}
