package com.anime_go.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable {
	
	@Id
	@GeneratedValue
	private Long idUser;
	
	private String firstName;
	private String lastName;
	private String mail;
	private String password;
	@Lob 
	private byte[] photo;
	
	@OneToMany(mappedBy = "user")
	private List<AnimeCharacter> characters;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@JsonIgnore
	public List<AnimeCharacter> getCharacters() {
		return characters;
	}

	public void setCharacters(List<AnimeCharacter> characters) {
		this.characters = characters;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", mail=" + mail
				+ ", password=" + password + ", photo=" + Arrays.toString(photo) + ", characters=" + characters + "]";
	}
	
	
	
	
	

}
