package com.zhangys.dao.common;

import com.google.common.base.Preconditions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 123 on 2017/3/7.
 */
public abstract class AbstractHibernateDao<T extends Serializable> implements IOperation<T> {
    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractHibernateDao() { }

    protected final void setClazz(final Class<T> clazzToSet) {
        this.clazz = Preconditions.checkNotNull(clazzToSet);
    }

    public AbstractHibernateDao(Class<T> clazz) {
        this.clazz = Preconditions.checkNotNull(clazz);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected final Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public final T get(final long id) {
        Session session = openSession();
        T entity = null;
        try {
            session.beginTransaction();
            entity = (T) session.get(clazz, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return entity;
    }

    @Override
    public final List<T> get() {
        Session session = openSession();
        List<T> tmpList = null;
        try {
            session.beginTransaction();
            tmpList = session.createQuery("from " + clazz.getName()).list();
//            tmpList = session.createSQLQuery("select * from department").list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tmpList;
    }

//    @Override
//    public Page<T> getPage(Page<T> page) {
//        Session session = openSession();
//        try {
//            page.setResult(queryForResult(page,session));
//
//            int totalPages, totalRows;
//            totalRows = queryForCount(page,session);
//
//            if (page.getPageSize() == 0) {
//                totalPages = totalRows;
//            } else {
//                if (totalRows % page.getPageSize() == 0)
//                    totalPages = totalRows / page.getPageSize();
//                else
//                    totalPages = totalRows / page.getPageSize() + 1;
//            }
//
//            page.setTotalPages(totalPages);
//            page.setTotalRows(totalRows);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return page;
//    }

    @Override
    public final T update(final T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public final void create(final T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public void create(List<T> entities) {
        Session session = sessionFactory.openSession();
        try {
            Transaction tx = session.beginTransaction();
            for (T entity : entities) {
                session.save(entity);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public final T merge(final T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public void merge(List<T> entities) {
        Session session = sessionFactory.openSession();
        try {
            Transaction tx = session.beginTransaction();
            for (T entity : entities) {
                session.merge(entity);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public final boolean delete(final T entity) {
        Session session = openSession();

        boolean isSuccess = false;

        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return isSuccess;
    }

    @Override
    public final void delete(List<T> entities) throws Exception {
        Session session = openSession();
        try {
            Transaction tx = session.beginTransaction();
            for (T entity : entities) {
                session.delete(entity);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public final boolean deleteById(final long entityId) {
        final T entity = get(entityId);
        Preconditions.checkState(entity != null);
        return delete(entity);
    }

    @Override
    public void deleteAll() {
        String hql = "delete from " + clazz.getName();
        Session session = openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
