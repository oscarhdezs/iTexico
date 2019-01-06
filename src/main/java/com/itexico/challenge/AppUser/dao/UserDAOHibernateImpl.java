package com.itexico.challenge.AppUser.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itexico.challenge.AppUser.entity.Role;
import com.itexico.challenge.AppUser.entity.User;
import com.itexico.challenge.AppUser.utility.UserUtility;

@Repository
public class UserDAOHibernateImpl implements UserDAO {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private UserUtility userUtility;

	@Override
	@Transactional
	public boolean updateUser(User updates, int id) {
		Session session = entityManager.unwrap(Session.class);
		boolean isUpdated = false;
		User user = session.get(User.class, id);
		if (user != null) {
			isUpdated = userUtility.needToUpdate(updates, user);
		}
		if (isUpdated) {
			session.update(user);
		}
		return isUpdated;
	}

	@Override
	@Transactional
	public List<User> getActiveUsers() {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery(" from User where active=:activeStatus", User.class);
		query.setParameter("activeStatus", true);
		List<User> users = query.getResultList();
		
		return users;
	}

	@Override
	@Transactional
	public User getUser(int id) {
		Session session = entityManager.unwrap(Session.class);
		User user = session.get(User.class, id);
		return user;
	}

	@Override
	@Transactional
	public User createUser(User user) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<User> exist = session.createQuery("from User where first_name=:name",User.class);
		exist.setParameter("name", user.getFirstName().trim());
		List<User> existUser = exist.getResultList();
		if(!existUser.isEmpty()) {
			user = null;
			return user;
		}
		
		
		List<Integer> rolesId = new ArrayList<>();
		List<Role> roles = user.getRoles();
		user.setRoles(null);
		session.save(user);
		
		for (int x = 0; x < roles.size(); x++) {
			Role role = new Role();
			role = roles.get(x);
			// Check if the role exist
			Query<Role> query = session.createQuery(" from Role where role_name=:role", Role.class);
			query.setParameter("role", role.getRoleName().trim());
			List<Role> roleTable = query.getResultList();
			if (roleTable.size()>0) {
				session.update(roleTable.get(0));
				rolesId.add(roleTable.get(0).getRoleId());
			} else {
				session.save(role);
				rolesId.add(role.getRoleId());
			}
		}
		if(rolesId.size()>0) {
			Query query;
			query = session.createSQLQuery("insert into user_roles (user_id,role_id) values (:userId,:roleId)");
			for(int x=0;x<rolesId.size();x++) {
				
				query.setParameter("userId", user.getUserId());
				query.setParameter("roleId", rolesId.get(x));
				query.executeUpdate();
			}
		}
		
		return user;
	}

}
