package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        String hql = "from User u where u.car.model = :model and u.car.series = :series";
        try {
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter("model", model).setParameter("series", series);
            return query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Нет пользователя с таким авто");
            return null;
        }
    }
}
