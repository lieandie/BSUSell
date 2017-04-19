import entity.Client;
import entity.Item;
import entity.SellOrder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Кирилл on 12.04.2017.
 */
public class HibernateHolder {
    private final SessionFactory sessionFactory;

    public HibernateHolder() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/mydb");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "skubnikov96");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        Session session = getSession();
        session.beginTransaction();
    }

    public HibernateHolder(String host, String db, String user, String psswd) {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.setProperty("hibernate.connection.url","jdbc:mysql://"+host+"/"+db);
            configuration.setProperty("hibernate.connection.username", user);
            configuration.setProperty("hibernate.connection.password", psswd);

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
        session.merge(o);
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

    public void delete(String from){
        Session session = getSession();
        session.beginTransaction();
        Query q = session.createQuery("delete " + from);
        session.getTransaction().commit();
        session.close();
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
