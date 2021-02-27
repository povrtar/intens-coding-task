package com.intens.task.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.intens.task.model.Skill;
import com.intens.task.repository.SkillRepository;
import com.intens.task.web.dto.SkillDto;

@Component
public class SkillDtoToSkill implements Converter <SkillDto,Skill> {
@Autowired
SkillDtoToSkill toSkill;
@Autowired
SkillRepository skillRepository;
	@Override
	public Skill convert(SkillDto source) {
	if(source!=null){	
		Skill skill=source.getId()==null?new Skill():skillRepository.getOne(source.getId());
		skill.setId(source.getId());
		skill.setName(source.getName());
		return skill;
	}
	else return null;
	}
public List<Skill> convert(List<SkillDto> source){
	if(source==null) {
		return null;
	}
	List <Skill>skills=new ArrayList<>();
	for(SkillDto dto:source) {
		skills.add(convert(dto));
	}
	return skills;
}
}
