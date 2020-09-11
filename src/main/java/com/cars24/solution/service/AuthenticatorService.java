package com.cars24.solution.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cars24.solution.model.UserStore;
import com.cars24.solution.repository.UserRepository;

@Component
public class AuthenticatorService {

	private static final Logger log = LoggerFactory.getLogger(AuthenticatorService.class);

	@Autowired
	UserRepository userRepository;
	
	public boolean isUserLoggedIn(int userId, String token)
	{
		try {
			UserStore user = userRepository.findById(userId);
			
			if(user.getId() == userId && user.getToken().equalsIgnoreCase(token))
				return true;
			else
			{	log.info("User is not logged in, please check the headers for UserId and UserToken");
				return false;
			}
		} catch (Exception e) {
			
			log.info("User is not logged in, please check the headers for UserId and UserToken" + e.toString());
			
			return false;
		}
	}
}
