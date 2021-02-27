package com.intens.task.service;

import java.util.List;
import java.util.Optional;

import com.intens.task.model.Skill;

public interface SkillService {
	Optional<Skill> one(Long id);	
	List<Skill> all();
	Skill save(Skill skill);
	Skill delete(Long id);
	
}
