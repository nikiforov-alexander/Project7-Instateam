package com.teamtreehouse.instateam.dao;

import com.teamtreehouse.instateam.model.Role;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by GoranB on 2017-01-25.
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> findAll() {
        //Open a session
        Session session=sessionFactory.openSession();
        //Get all categories with a Hibernate criteria
        List<Role> roles=session.createCriteria(Role.class).list();

        //Close the session
        session.close();

        return roles;


    }

    @Override
    public Role findById(Long roleId)
    {

        //Hibernate.initialize(collaborator.get);
        Session session = sessionFactory.openSession();
        Role role = session.get(Role.class, roleId);
        Hibernate.initialize(role.getCollaborators());
        session.close();
        return role;
    }

    @Override
    public void save(Role role) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(role);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(Role role) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(role);
        session.getTransaction().commit();
        session.close();

    }
}
