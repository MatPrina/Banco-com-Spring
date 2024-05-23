package com.banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.domains.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
