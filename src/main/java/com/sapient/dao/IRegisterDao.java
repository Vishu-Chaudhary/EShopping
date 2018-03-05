package com.sapient.dao;

import com.sapient.entity.UserDetails;

public interface IRegisterDao {
	
	UserDetails getUser(String userName) throws NotFoundException;
	int  addUser(UserDetails reg) throws UserNameExistsException; 

}
