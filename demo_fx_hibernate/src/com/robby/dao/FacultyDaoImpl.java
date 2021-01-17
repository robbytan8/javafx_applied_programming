package com.robby.dao;

import com.robby.entity.Faculty;
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
public class FacultyDaoImpl implements DaoService<Faculty> {
    @Override
    public int addData(Faculty object) {
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
    public int deleteData(Faculty object) {
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
    public int updateData(Faculty object) {
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
    public List<Faculty> fetchAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Faculty> criteriaQuery = builder.createQuery(Faculty.class);
        criteriaQuery.from(Faculty.class);
        List<Faculty> faculties = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return faculties;
    }
}
