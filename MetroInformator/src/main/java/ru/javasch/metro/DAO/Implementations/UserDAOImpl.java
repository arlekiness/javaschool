package ru.javasch.metro.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.DAO.Interfaces.UserDAO;
import ru.javasch.metro.model.User;

@Repository
public class UserDAOImpl<E extends User> extends GenericDAOImpl<E> implements UserDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void updateProfile(User user) {
        sessionFactory.getCurrentSession().createQuery("UPDATE User SET " +
                "firstName = :firstName, lastName = :lastName, login = :login, where id =: id")
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("login", user.getLogin())
                .setParameter("id", user.getId())
                .executeUpdate();
    }

    @Override
    public User findUserByEmail(String login) {
        User user = (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User u where login = :login")
                .setParameter("login", login)
                .uniqueResult();
        return user;
    }

}
