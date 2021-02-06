package com.zovlanik.restapifiles.repository.hibernate;

import com.zovlanik.restapifiles.model.Account;
import com.zovlanik.restapifiles.repository.AccountRepository;
import org.hibernate.Session;

import java.util.List;

public class HibernateAccountRepositoryImpl implements AccountRepository {

    @Override
    public Account getById(Integer integer) {
        Account account = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        account = session.get(Account.class, id);
        session.getTransaction().commit();

        return account;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public void create(Account account) {

    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
