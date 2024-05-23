package com.banco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banco.domains.User;
import com.banco.services.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping("/users")
	public ResponseEntity<User> criaUsuario(@RequestBody User user) {
		User newUser = service.salvaUsuario(user);
		return ResponseEntity.status(HttpStatus.OK).body(newUser);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> listaUsuarios() {
		List<User> users = service.listaUsuarios();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
}
