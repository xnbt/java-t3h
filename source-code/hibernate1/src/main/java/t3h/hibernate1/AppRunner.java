package t3h.hibernate1;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import t3h.hibernate1.dao.GroupDAO;
import t3h.hibernate1.model.Group;


@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    LocalSessionFactoryBean sessionFactory;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional
    public void exploreSession() {


     //   Session session3 = sessionFactory.getObject().getCurrentSession();
//        Session session4 = sessionFactory.getObject().getCurrentSession();
//
//        System.out.println("session3 == session4:" + (session3 == session4));
    }

    @Override
    public void run(String... args) throws Exception {
        Group group = new Group();
        for (int i = 0; i < 20; i++) {
            group.setName("group" + i);
            groupDAO.insert(group);
        }
        exploreSession();
    }
}
