package com.example.dao;

import com.example.model.Employee;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAO {

    public Employee save(Employee e) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(e);
            tx.commit();
            return e;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }

    public Employee findById(Long id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Employee.class, id);
        }
    }

    public List<Employee> findAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Employee", Employee.class).list();
        }
    }

    public void delete(Employee e) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.remove(s.contains(e) ? e : s.merge(e));
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }

    public Employee update(Employee e) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Employee merged = (Employee) s.merge(e);
            tx.commit();
            return merged;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }

    public Employee findByEmail(String email) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Employee e where e.email = :email", Employee.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }
}
