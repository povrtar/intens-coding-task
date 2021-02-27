package com.intens.task.support;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.intens.task.model.Skill;
import com.intens.task.web.dto.SkillDto;

@Component
public class SkillToSkillDto implements Converter<Skill,SkillDto> {
@Autowired
CandidateToCandidateDto candidateToDto;
	@Override
	public SkillDto convert(Skill source) {
		SkillDto dto=new SkillDto();
		dto.setId(source.getId());
		dto.setName(source.getName());
		return dto;
	}
public List<SkillDto> convert(List<Skill> skills) {
List<SkillDto> dtos=new ArrayList<>();
	for(Skill skill:skills) {
		dtos.add(convert(skill));
	}
	return dtos;
}
}
