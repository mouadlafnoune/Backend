package com.anime_go.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AnimeCharacter implements Serializable{

	@Id
	@GeneratedValue
	private Long idCharacter;
	private String characterName;
	private String category;
	private String legend;
	private String strength;
	@Lob 
	private byte[] photo;
	@Transient
	private Long idOwner;
	
	private boolean shared;
	
	@ManyToOne
	@JoinColumn(name= "idUser")
	private User user;

	public AnimeCharacter() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Long getIdCharacter() {
		return idCharacter;
	}

	public void setIdCharacter(Long idCharacter) {
		this.idCharacter = idCharacter;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}
    
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public Long getIdOwner() {
		return idOwner;
	}



	public void setIdOwner(Long idOwner) {
		this.idOwner = idOwner;
	}



	public String getLegend() {
		return legend;
	}



	public void setLegend(String legend) {
		this.legend = legend;
	}



	@Override
	public String toString() {
		return "AnimeCharacter [idCharacter=" + idCharacter + ", characterName=" + characterName + ", category="
				+ category + ", legend=" + legend + ", strength=" + strength + ", photo=" + Arrays.toString(photo)
				+ ", idOwner=" + idOwner + ", shared=" + shared + ", user=" + user + "]";
	}


}
