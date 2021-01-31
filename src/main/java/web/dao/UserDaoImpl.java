package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByEmail(String email) {
        return (User) entityManager.createQuery("from User where email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(int id, User newUser) {
        User user = entityManager.find(User.class, id);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setAge(newUser.getAge());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setRoles(newUser.getRoles());
    }

    @Override
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }
}

//package web.dao;
//
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.stereotype.Repository;
//        import web.model.User;
//
//        import javax.persistence.EntityManager;
//        import javax.persistence.EntityManagerFactory;
//        import java.util.List;
//
//@Repository
//public class UserDaoImpl implements UserDao {
//
//    private final EntityManagerFactory entityManagerFactory;
//
//    @Override
//    public User getUserByEmail(String email) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        return (User) em.createQuery("from User where email = :email")
//                .setParameter("email", email)
//                .getSingleResult();
//    }
//
//    @Autowired
//    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
//
//    @Override
//    public User getUserById(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager.find(User.class, id);
//    }
//
//    @Override
//    public void addUser(User user) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(user);
//        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    public void updateUser(int id, User newUser) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        User user = entityManager.find(User.class, id);
//        user.setFirstName(newUser.getFirstName());
//        user.setLastName(newUser.getLastName());
//        user.setAge(newUser.getAge());
//        user.setEmail(newUser.getEmail());
//        user.setPassword(newUser.getPassword());
//        user.setRoles(newUser.getRoles());
//
//        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    public void deleteUser(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        User user = entityManager.find(User.class, id);
//        entityManager.remove(user);
//
//        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<User> getAllUsers() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager.createQuery("from User").getResultList();
//    }
//}

