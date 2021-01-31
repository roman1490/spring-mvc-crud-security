package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getRoleList() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }
}

//package web.dao;
//
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.stereotype.Repository;
//        import web.model.Role;
//        import web.model.User;
//
//        import javax.persistence.EntityManager;
//        import javax.persistence.EntityManagerFactory;
//        import java.util.List;
//        import java.util.Set;
//
//@Repository
//public class RoleDaoImpl implements RoleDao{
//
//    private EntityManagerFactory entityManagerFactory;
//
//    @Autowired
//    public RoleDaoImpl(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<Role> getRoleList() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager.createQuery("from Role").getResultList();
//    }
//
//    @Override
//    public Role getRoleById(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager.find(Role.class, id);
//    }
//}
