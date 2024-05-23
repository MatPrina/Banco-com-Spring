package com.banco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.domains.User;
import com.banco.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository rep;
	
	public User salvaUsuario(User user) {
		User newUser = new User(user.getName(), user.getEmail(), user.getBalance());
		rep.save(newUser);
		return newUser;
	}
	
	public List<User> listaUsuarios() {
		return rep.findAll();
	}
	
	public User findById(Long id) {
		return rep.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrando"));
	}
}
