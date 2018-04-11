package isa.projekat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String email);
	List<User> findByUserName(String userName);
	List<User> findByUserSurname(String userSurname);
	List<User> findByUserSurnameAndUserName(String userSurname, String userName);
	List<User> findAll();
}
