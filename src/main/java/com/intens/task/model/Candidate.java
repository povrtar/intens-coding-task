package com.intens.task.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
private String fullName;
private LocalDate dateOfBirth;
private String contactNumber;
private String email;
@ManyToMany
List<Skill> skills;

public Candidate() {
	super();
}

public Candidate(String fullName, LocalDate dateOfBirth, String contactNumber, String email) {
	super();
	this.fullName = fullName;
	this.dateOfBirth = dateOfBirth;
	this.contactNumber = contactNumber;
	this.email = email;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public LocalDate getDateOfBirth() {
	return dateOfBirth;
}

public void setDateOfBirth(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}

public String getContactNumber() {
	return contactNumber;
}

public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public List<Skill> getSkills() {
	return skills;
}

public void setSkills(List<Skill> skills) {
	this.skills = skills;
}

@Override
public String toString() {
	return "Candidate [fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", contactNumber=" + contactNumber
			+ ", email=" + email + ", skills=" + skills + "]";
}

}