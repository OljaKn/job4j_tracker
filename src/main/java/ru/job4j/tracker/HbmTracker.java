package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
            return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
                session.update(item);
                session.getTransaction().commit();
                rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            List<Item> items = session.createQuery("from Item", Item.class).list();
            session.getTransaction().commit();
            return items;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            List<Item> items = session.createQuery("FROM Item WHERE name LIKE :key", Item.class)
                    .setParameter("key", "%" + key + "%")
                    .getResultList();
            session.getTransaction().commit();
            return items;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Item item = session.createQuery(
                            "from Item where id = :id", Item.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return item;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return new Item();
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}