package com.example.dao;

import com.example.model.Payroll;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PayrollDAO {

    public Payroll save(Payroll p) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(p);
            tx.commit();
            return p;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }

    public Payroll findById(Long id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Payroll.class, id);
        }
    }

    public List<Payroll> findForEmployee(Long empId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Payroll p where p.employee.id = :eid", Payroll.class)
                    .setParameter("eid", empId).list();
        }
    }

    public void delete(Payroll p) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.remove(s.contains(p) ? p : s.merge(p));
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }
}
