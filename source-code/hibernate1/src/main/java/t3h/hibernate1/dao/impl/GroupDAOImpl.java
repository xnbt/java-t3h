package t3h.hibernate1.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import t3h.hibernate1.dao.GroupDAO;
import t3h.hibernate1.model.Group;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupDAOImpl implements GroupDAO {
    @Autowired
    LocalSessionFactoryBean sessionFactory;

    @Override
    @Transactional
    public void insert(Group group) {
        Session session = sessionFactory.getObject().openSession();
        Session session3 = sessionFactory.getObject().getCurrentSession();
        try {
            System.out.println("session=" + session);
            session.save(group);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Group> getAll() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from hn_group");
            return (List<Group>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }
}
