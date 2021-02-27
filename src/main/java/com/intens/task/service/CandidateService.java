package com.intens.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.intens.task.model.Candidate;
import com.intens.task.model.Skill;

public interface CandidateService {

	List<Candidate> all();
	
	Optional<Candidate> one(Long id);
	Candidate save(Candidate candidate);
	Candidate delete(Long id);
	List<Candidate> searchByFullName(String fullName);
	List<Candidate> searchBySkills(String skills);
	
}
