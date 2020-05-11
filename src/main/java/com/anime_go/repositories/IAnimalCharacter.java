package com.anime_go.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime_go.entities.AnimeCharacter;
import com.anime_go.entities.User;

public interface IAnimalCharacter extends JpaRepository<AnimeCharacter, Long>{

	List<AnimeCharacter> findByUserOrShared(User user, boolean shared);
	
	List<AnimeCharacter> findByUser(User user);
	List<AnimeCharacter> findBySharedAndUserNotLike(boolean shared, User user);
	
	
	
}
