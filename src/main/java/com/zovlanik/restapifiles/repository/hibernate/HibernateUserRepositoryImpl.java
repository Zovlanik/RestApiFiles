package com.zovlanik.restapifiles.repository.hibernate;

import com.zovlanik.restapifiles.model.User;
import com.zovlanik.restapifiles.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.engine.internal.Collections;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {
    @Override
    public User getById(Integer id) {
        User user = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        user = session.get(User.class, id);
        session.getTransaction().commit();

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> lu = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM User order by id");
        lu = query.list();
        session.getTransaction().commit();
        return lu;
    }

    @Override
    public void create(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
    }

    @Override
    public void deleteById(Integer id) {
        User user = getById(id); //да, костыль
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }
}
