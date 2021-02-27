package com.intens.task.web.dto;

import java.time.LocalDate;
import java.util.List;

public class CandidateDto {
	private Long id;
private String fullName;
private LocalDate dateOfBirth;
private String contactNumber;
private String email;
private List<SkillDto> skillsDto;
public CandidateDto() {
	super();
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
public List<SkillDto> getSkillsDto() {
	return skillsDto;
}
public void setSkillsDto(List<SkillDto> skillsDto) {
	this.skillsDto = skillsDto;
}

@Override
public String toString() {
	return "CandidateDto [fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", contactNumber=" + contactNumber
			+ ", email=" + email + ", skillsDto=" + skillsDto + "]";
}


}
