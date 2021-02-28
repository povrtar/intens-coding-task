package com.intens.task.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.intens.task.model.Candidate;
import com.intens.task.model.Skill;
import com.intens.task.repository.CandidateRepository;
import com.intens.task.repository.SkillRepository;
import com.intens.task.service.CandidateService;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class JpaCandidateService implements CandidateService {
	@Autowired
	CandidateRepository candidateRepository;
	@Autowired
	SkillRepository skillRepository;

	@Override
	public List<Candidate> all() {
		return candidateRepository.findAll();
	}

	@Override
	public Optional<Candidate> one(Long id) {
		return candidateRepository.findById(id);
	}

	
	@Override
	public Candidate save(Candidate candidate) {
		List<Long> skillIdes=new ArrayList<>();
		for (Skill skill:candidate.getSkills()) {
			skillIdes.add(skill.getId());
		}
	if(candidate.getId()!=null) {
		
			candidateRepository.deleteRelatedSkills(candidate.getId());
		
	}
	List<Skill> skills=new ArrayList<>();
	for(Long id:skillIdes) {
		skills.add(skillRepository.getOne(id));
	}
	candidate.setSkills(skills);
		Candidate persisted = candidateRepository.save(candidate);
		System.out.println("Skills +"+persisted.getSkills());
		List<Skill> skills1 = candidate.getSkills();
		for (Skill skill : skills1) {
			Set<Candidate> candidates = new HashSet<>();
			candidates = skill.getCandidatesWithSkill();
			candidates.add(persisted);
			skill.setCandidatesWithSkill(candidates);
			skillRepository.save(skill);
		}
		return persisted;

	}


	@Override
	public Candidate delete(Long id) {
		Candidate candidate = candidateRepository.getOne(id);
		candidateRepository.deleteById(id);
		return candidate;
	}

	@Override
	public List<Candidate> searchBySkills( String skillIdes) {
		Map<Long,Candidate> mapOfCandidates = new HashMap<>();
		List<Candidate> candidates=new ArrayList<>();

		String [] skillString=skillIdes.replaceAll("\\s", "").split(",");
		
		for (int i=1;i<skillString.length;i++) {
			 candidates.clear();
			System.out.println("string "+skillString[i]);
		if(skillString[i]!="") {
			candidates = ( candidateRepository).searchBySkills(Long.parseLong(skillString[i]));
		
		
		 for(Candidate candidate:candidates) {
			 
			 if(!mapOfCandidates.containsKey(  candidate.getId())) {
				 mapOfCandidates.put(candidate.getId(),candidate);
			 }
		 }
		candidates.clear();
		
		}
		}
			
		candidates.addAll(mapOfCandidates.values());
		return candidates;
	}

	@Override
	public List<Candidate> searchByFullName(String fullName) {
		return candidateRepository.findByFullName( fullName);
	}

}
