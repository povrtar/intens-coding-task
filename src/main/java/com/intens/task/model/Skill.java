package com.intens.task.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Skill {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
@ManyToMany
private Set<Candidate> candidatesWithSkill;
public Skill() {
	super();
}
public Skill( String name) {
	super();
	this.name = name;

}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Set<Candidate> getCandidatesWithSkill() {
	return candidatesWithSkill;
}
public void setCandidatesWithSkill(Set<Candidate> candidatesWithSkill) {
	this.candidatesWithSkill = candidatesWithSkill;
}

}
