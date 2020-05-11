package com.anime_go.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime_go.entities.User;

public interface IUser extends JpaRepository<User, Long>{

	User findByMailAndPassword(String mail, String password);
}
