package com.oncolator.app.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oncolator.app.entity.dataEntity.Users;
import com.oncolator.app.repository.UsersRepository;

@Component
public class UsersService {
	
	private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }
    
    public Users saveUser(Users users) {
    	return usersRepository.save(users);
    }

}
