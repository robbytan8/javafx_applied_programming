package com.robby.dao;

import com.robby.entity.Department;
import com.robby.util.DaoService;
import com.robby.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Robby Tan
 */
public class DepartmentDaoImpl implements DaoService<Department> {
    @Override
    public int addData(Department object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(object);
            transaction.commit();
            result = 1;
        } catch (HibernateException ex) {
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int deleteData(Department object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(object);
            transaction.commit();
            result = 1;
        } catch (HibernateException ex) {
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int updateData(Department object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(object);
            transaction.commit();
            result = 1;
        } catch (HibernateException ex) {
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public List<Department> fetchAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        criteriaQuery.from(Department.class);
        List<Department> departments = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return departments;
    }
}
