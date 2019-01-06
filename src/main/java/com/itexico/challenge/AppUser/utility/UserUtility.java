package com.itexico.challenge.AppUser.utility;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.itexico.challenge.AppUser.entity.User;

@Component
public class UserUtility {

	public boolean needToUpdate(User updates, User user) {
		boolean needToUpdate = false;
		String first = updates.getFirstName();
		String last = updates.getLastName();
		Boolean active = updates.getActive();
		Date date = updates.getCreationDate();
		
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
		return needToUpdate;
	}

	
}
