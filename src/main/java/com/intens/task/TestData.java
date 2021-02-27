package com.intens.task;

import java.time.LocalDate;
import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intens.task.model.Skill;
import com.intens.task.repository.CandidateRepository;
import com.intens.task.repository.SkillRepository;
import com.intens.task.model.Candidate;


@Component
public class TestData {
@Autowired
private SkillRepository skillService;
@Autowired
 CandidateRepository candidateService;
	@PostConstruct
	public void init() {

		Skill skill1=new Skill("Java Programing");
		Skill skill2=new Skill("C# Programing");
		Skill skill3=new Skill("Database design");
	    Skill skill4=new Skill("English language");
	    Skill skill5=new Skill("German lenguage");
skillService.save(skill1);
skillService.save(skill2);
skillService.save(skill3);
skillService.save(skill4);
skillService.save(skill5);

		List<Skill> skills1=new ArrayList<>();
		skills1.add(skill1);
		skills1.add(skill2);
		skills1.add(skill3);
		List<Skill>skills2=new ArrayList<>();
		skills2.add(skill3);
		skills2.add(skill4);
		skills2.add(skill5);
        Candidate candidate1=new Candidate("Bosic Mihajlo",LocalDate.of(2007, 10, 28),"063-3333-333","mihajlo@hotmail.com");
        Candidate candidate2=new Candidate("Bosic Dunja",LocalDate.of(2010, 11, 13),"064-333-333","dunja@hotmail.com");
		candidate1.setSkills(skills1);
		candidate2.setSkills(skills2);
		candidateService.save(candidate1);
		candidateService.save(candidate2);
		Set<Candidate> candidatesSet1=new HashSet<>();
		Set<Candidate> candidatesSet2=new HashSet<>();
		candidatesSet1.add(candidate1);
		candidatesSet2.add(candidate2);
		skill1.setCandidatesWithSkill(candidatesSet1);
		skill2.setCandidatesWithSkill(candidatesSet1);
		candidatesSet1.add(candidate2);
		skill3.setCandidatesWithSkill(candidatesSet1);
		skill4.setCandidatesWithSkill(candidatesSet2);
		skill5.setCandidatesWithSkill(candidatesSet2);
		skillService.save(skill1);
		skillService.save(skill2);
		skillService.save(skill3);
		skillService.save(skill4);
		skillService.save(skill5);
		
	}
}
