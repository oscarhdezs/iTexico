package com.itexico.challenge.AppUser.utility;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itexico.challenge.AppUser.entity.Role;
import com.itexico.challenge.AppUser.entity.User;

@Component
public class UserUtility {

	public boolean needToUpdate(User updates, User user) {
		boolean needToUpdate = false;
		String first = updates.getFirstName();
		String last = updates.getLastName();
		Boolean active = updates.getActive();
		Date date = updates.getCreationDate();
		List<Role> roles = updates.getRoles();
		if(first!=null) {
			user.setFirstName(first);
			needToUpdate = true;
		}
		if(last!=null) {
			user.setLastName(last);
			needToUpdate = true;
		}
		if(active!=null) {
			user.setActive(active);
			needToUpdate = true;
		}
		if(date!=null) {
			user.setCreationDate(date);
			needToUpdate = true;
		}
		if (roles != null) {
			for (int x = 0; x < user.getRoles().size(); x++) {
				user.getRoles().get(x).setActive(roles.get(x).getActive());
				user.getRoles().get(x).setRoleName(roles.get(x).getRoleName());
			}
			needToUpdate = true;
		}
		return needToUpdate;
	}

	
}
