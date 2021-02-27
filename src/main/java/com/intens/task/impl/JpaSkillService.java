package com.intens.task.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intens.task.model.Skill;
import com.intens.task.repository.SkillRepository;
import com.intens.task.service.SkillService;
@Service
public class JpaSkillService implements SkillService {
@Autowired
private SkillRepository skillRepository;
	@Override
	public Optional<Skill> one(Long id) {
		return skillRepository.findById(id);
	}

	@Override
	public List<Skill> all() {
		return skillRepository.findAll();
	}

	@Override
	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}

	@Override
	public Skill delete(Long id) {
		Optional<Skill> skill=skillRepository.findById(id);
		if(skill.isPresent()) {
			skillRepository.deleteById(id);
		}
		return skill.get();
		}

}
