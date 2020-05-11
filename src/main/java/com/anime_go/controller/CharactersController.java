package com.anime_go.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.anime_go.entities.AnimeCharacter;
import com.anime_go.entities.User;
import com.anime_go.exception.ResourceNotFoundException;
import com.anime_go.repositories.IAnimalCharacter;
import com.anime_go.repositories.IUser;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/characters")
public class CharactersController {
   
	@Autowired
	private IAnimalCharacter characterRepository;
	@Autowired
	private IUser userRepository;
	
	@GetMapping("/")
	public ResponseEntity findAll() {
		return ResponseEntity.ok(characterRepository.findAll());
	}
	@GetMapping("/all/{idUser}")
	public ResponseEntity findAllUserCharacters(@PathVariable Long idUser) {
		if(idUser == null) {
			return ResponseEntity.badRequest().body("Cannot find anime with null user");		
		}
		User user = userRepository.getOne(idUser);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		List<AnimeCharacter> userChars = characterRepository.findByUser(user);
		List<AnimeCharacter> sharedChars = characterRepository.findBySharedAndUserNotLike(true, user);
		userChars.forEach(Character-> Character.setIdOwner(idUser));
		sharedChars.forEach(Character-> Character.setIdOwner(-1L));
		userChars.addAll(sharedChars);
		return ResponseEntity.ok(userChars);
	}
	
	
	@GetMapping("/{idCharacter}")
	public ResponseEntity findCharacterById(@PathVariable(name="idCharacter") Long idCharacter)  {
		if(idCharacter == null) {
			return ResponseEntity.badRequest().body("Cannot find anime with null ID");
		}
		AnimeCharacter character = characterRepository.getOne(idCharacter);
		if(character == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(character);
	}
	
	@PostMapping("/")
	public ResponseEntity createCharacter(@RequestBody AnimeCharacter character) {
		if(character == null) {
			return ResponseEntity.badRequest().body("Cannot create character with empty fields");
		}
		return ResponseEntity.ok(characterRepository.save(character));
	}
	
	
	@DeleteMapping("/{idCharacter}")
	public ResponseEntity deleteCharacter(@PathVariable(name = "idCharacter") Long idCharacter) {
		if(idCharacter == null) {
			return ResponseEntity.badRequest().body("Cannot remove character with null ID");
		}
		AnimeCharacter character = characterRepository.getOne(idCharacter);
		if(character == null) {
			return ResponseEntity.notFound().build();
		}
		characterRepository.delete(character);
		return ResponseEntity.ok("Character removed with success");
	}
	
	@PutMapping("/updatecharacter/{idCharacter}")
	public ResponseEntity updateCharacter(@PathVariable(name = "idCharacter") Long idCharacter,
			@Valid @RequestBody AnimeCharacter characterDetails) {
		
		
		if(idCharacter == null) {
			return ResponseEntity.badRequest().body("Cannot update character with null ID");
		}
		AnimeCharacter character = characterRepository.getOne(idCharacter);
		
		if(character == null) {
			return ResponseEntity.notFound().build();
		}
		character.setIdCharacter(characterDetails.getIdCharacter());
		character.setCharacterName(characterDetails.getCharacterName());
		character.setCategory(characterDetails.getCategory());
		character.setStrength(characterDetails.getStrength());
		
		characterRepository.save(character);
		return ResponseEntity.ok("Character update with success");
		
	}
	
	@GetMapping("/share/{idCharacter}/{isShared}")
	public ResponseEntity shareCharacter(@PathVariable(name = "idCharacter") Long idCharacter,
			@PathVariable(name = "isShared") boolean isShared) {
		if(idCharacter == null) {
			return ResponseEntity.badRequest().body("Cannot remove character with null ID");
		}
		AnimeCharacter character = characterRepository.getOne(idCharacter);
		if(character == null) {
			return ResponseEntity.notFound().build();
		}
		
		character.setShared(!isShared);
		return ResponseEntity.ok(characterRepository.save(character));
	}
}
