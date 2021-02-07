package com.zovlanik.restapifiles.repository.hibernate;

import com.zovlanik.restapifiles.model.Event;
import com.zovlanik.restapifiles.repository.EventRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HibernateEventRepositoryImpl implements EventRepository {
    @Override
    public Event getById(Integer id) {
        Event event = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        event = session.get(Event.class, id);
        session.getTransaction().commit();

        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> le = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Event order by id");
        session.getTransaction().commit();
        le = query.list();
        return le;
    }

    @Override
    public void create(Event event) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(event);
        session.getTransaction().commit();
    }

    @Override
    public void update(Event event) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(event);
        session.getTransaction().commit();
    }

    @Override
    public void deleteById(Integer id) {
        Event event = getById(id); //да, костыль
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(event);
        session.getTransaction().commit();
    }
}
