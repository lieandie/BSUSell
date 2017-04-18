import entity.Client;
import entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Created by Кирилл on 12.04.2017.
 */
public class HibernateHolder {
    private final SessionFactory sessionFactory;

    public HibernateHolder() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        Session session = getSession();
        session.beginTransaction();
    }

    public Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public ArrayList getClients(){
        Session session = getSession();
        session.beginTransaction();
        Query q = session.createQuery("from Client");
        ArrayList result = (ArrayList) q.list();
        session.close();
        return result;
    }

    public void delete(Object o){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(o);
        transaction.commit();
        session.close();
    }

    public void add(Object o){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(o);
        transaction.commit();
        session.close();
    }
    public ArrayList get(String entityName){
        Session session = getSession();
        session.beginTransaction();
        Query q = session.createQuery("from " + entityName);
        ArrayList result = (ArrayList) q.list();
        session.close();
        return result;
    }

    public void deleteItem(int id){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Item o = new Item();
        o.setId(id);
        session.delete(o);
        transaction.commit();
        session.close();
    }


}
