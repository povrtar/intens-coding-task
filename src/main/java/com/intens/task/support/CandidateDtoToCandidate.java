package com.intens.task.support;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.intens.task.model.Skill;
import com.intens.task.model.Candidate;
import com.intens.task.service.CandidateService;
import com.intens.task.web.dto.CandidateDto;

@Component
public class CandidateDtoToCandidate implements Converter<CandidateDto,Candidate>{
	@Autowired
	private CandidateService candidateService;

@Autowired
private SkillDtoToSkill toSkill;
	@Override
	public Candidate convert(CandidateDto source) {
		
		Long id = source.getId();
		Candidate candidate = id == null ? new Candidate() : candidateService.one(id).get();
		
		
		candidate.setId(source.getId());
		candidate.setFullName(source.getFullName());
		candidate.setDateOfBirth(source.getDateOfBirth());
		candidate.setContactNumber(source.getContactNumber());
		candidate.setEmail(source.getEmail());
		List <Skill> skills=new ArrayList<>();
		if(source.getSkillsDto()==null) {
			skills=null;
		}else {
		skills=toSkill.convert(source.getSkillsDto());
		}
		candidate.setSkills(skills);
		return candidate;
	}

}
