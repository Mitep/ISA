package isa.projekat.repository;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String email);
	
}
