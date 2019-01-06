package com.itexico.challenge.AppUser.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.itexico.challenge.AppUser.entity.Role;
import com.itexico.challenge.AppUser.entity.User;

@Controller
public class UserDTO {

	public void serviceToObjectActiveUsers(List<User> users) {
		List<Role> roles = new ArrayList<>();
		List<Role> realRoles;
		Role role;
		for (int x = 0; x < users.size(); x++) {
			realRoles = new ArrayList<>();
			roles = users.get(x).getRoles();
			for (int y = 0; y < roles.size(); y++) {
				role = new Role();
				role.setActive(roles.get(y).getActive());
				role.setRoleId(roles.get(y).getRoleId());
				role.setRoleName(roles.get(y).getRoleName());
				realRoles.add(role);
			}
			users.get(x).setRoles(realRoles);
		}
	}

	public void serviceToObjectUser(User user) {
		List<Role> roles = new ArrayList<>();
		roles = user.getRoles();
		Role role;
		List<Role> realRoles = new ArrayList<>();
		for (int y = 0; y < roles.size(); y++) {
			role = new Role();
			role.setActive(roles.get(y).getActive());
			role.setRoleId(roles.get(y).getRoleId());
			role.setRoleName(roles.get(y).getRoleName());
			realRoles.add(role);
		}
		user.setRoles(realRoles);
	}

}
