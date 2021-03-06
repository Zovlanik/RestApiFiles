package com.zovlanik.restapifiles.repository.hibernate;

import com.zovlanik.restapifiles.model.Account;
import com.zovlanik.restapifiles.repository.AccountRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HibernateAccountRepositoryImpl implements AccountRepository {

    @Override
    public Account getById(Integer id) {
        Account account = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        account = session.get(Account.class, id);
        session.getTransaction().commit();

        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> la = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Account order by id");

        la = query.list();
        session.getTransaction().commit();
        return la;
    }

    @Override
    public void create(Account account) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
    }

    @Override
    public void update(Account account) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(account);
        session.getTransaction().commit();
    }

    @Override
    public void deleteById(Integer id) {
        Account account = getById(id); //да, костыль
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(account);
        session.getTransaction().commit();
    }
}
