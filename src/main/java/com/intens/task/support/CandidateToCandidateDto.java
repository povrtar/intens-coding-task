package com.intens.task.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.intens.task.model.Skill;
import com.intens.task.model.Candidate;
import com.intens.task.web.dto.SkillDto;
import com.intens.task.web.dto.CandidateDto;
@Component
public class CandidateToCandidateDto implements Converter<Candidate,CandidateDto> {

@Autowired
private SkillToSkillDto toDto;
	@Override
	public CandidateDto convert(Candidate value) {
		CandidateDto dto=new CandidateDto();
		dto.setId(value.getId());
		dto.setFullName(value.getFullName());
		dto.setContactNumber(value.getContactNumber());
		dto.setDateOfBirth(value.getDateOfBirth());
		dto.setEmail(value.getEmail());
	List<SkillDto> skillsDto=new ArrayList<>();
		if(value.getSkills()==null) {
			skillsDto=null;
		}else {
			skillsDto=toDto.convert(value.getSkills());
		}
		dto.setSkillsDto(skillsDto);;
		return dto;
	}
	
	public List<CandidateDto> convert(List<Candidate> content) {
		List<CandidateDto> dtoes=new ArrayList<>();
		for(Candidate apartment:content) {
			dtoes.add(convert(apartment));
		}
		return dtoes;
	}

	
	

}
