package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        try {
            System.out.println("\ngetUserByCar():");
            String str = "SELECT user FROM User user WHERE user.car.series = :series AND user.car.model = :model";
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(str);
            query.setParameter("model", model);
            query.setParameter("series", series);
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("No result!\n" + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUser(long id) {
        try {
            System.out.println("\ngetUser():");
            String str = "SELECT user FROM User user WHERE user.id = :id";
            Query<User> query = sessionFactory.getCurrentSession().createQuery(str);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Not found!\n" + e.getMessage());
        }
        return null;
    }

    @Override
    public void getAllUserNames() {
        try {
            System.out.println("\ngetAllUserNames():");
            String str = "SELECT firstName FROM User";
            TypedQuery<String> query = sessionFactory.getCurrentSession().createQuery(str);
            query.getResultList().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Not found!\n" + e.getMessage());
        }
    }

    @Override
    public void getAllUsersNamesAndID() {
        final int POS_ID = 0;
        final int POS_NAME = 1;
        final int POS_LAST_NAME = 2;
        try {
            System.out.println("\ngetAllUsersNamesAndID():");
            String str = "SELECT id, firstName, lastName FROM User";
            Query query = sessionFactory.getCurrentSession().createQuery(str);
            List<Object[]> list = query.getResultList();
            for (Object[] object : list) {
                Long id = (Long) object[POS_ID];
                String firstName = (String) object[POS_NAME];
                String lastName = (String) object[POS_LAST_NAME];
                System.out.printf("id: %d, firstName: %s, lastName: %s\n", id, firstName, lastName);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Not found!\n" + e.getMessage());
        }
    }
}
