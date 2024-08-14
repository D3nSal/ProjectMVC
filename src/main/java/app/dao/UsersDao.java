package app.dao;

import jakarta.persistence.EntityManager;
import app.models.User;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UsersDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<User> showUsers(){
        return em.createQuery("from User").getResultList();
    }

    @Transactional
    public void saveUser(User user){
        em.persist(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(int id){
        return em.find(User.class, id);
    }

    @Transactional
    public void updateUser(User user, int id){
        User changedUser = getUserById(id);
        changedUser.setName(user.getName());
        changedUser.setLastName(user.getLastName());
        changedUser.setEmail(user.getEmail());
        em.merge(changedUser);
    }

    @Transactional
    public void deleteUser(int id){
        User user = getUserById(id);
        em.remove(user);
    }

}
