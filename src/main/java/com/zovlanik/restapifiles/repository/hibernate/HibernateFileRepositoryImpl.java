package com.zovlanik.restapifiles.repository.hibernate;

import com.zovlanik.restapifiles.model.Account;
import com.zovlanik.restapifiles.model.File;
import com.zovlanik.restapifiles.repository.FileRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HibernateFileRepositoryImpl implements FileRepository {
    @Override
    public File getById(Integer id) {
        File file = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        file = session.get(File.class, id);
        session.getTransaction().commit();

        return file;
    }

    @Override
    public List<File> getAll() {
        List<File> lf = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM File order by id");
        lf = query.list();
        session.getTransaction().commit();
        return lf;
    }

    @Override
    public void create(File file) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(file);
        session.getTransaction().commit();
    }

    @Override
    public void update(File file) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(file);
        session.getTransaction().commit();
    }

    @Override
    public void deleteById(Integer id) {
        File file = getById(id); //да, костыль
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(file);
        session.getTransaction().commit();
    }
}
