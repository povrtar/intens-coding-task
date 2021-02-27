package com.intens.task.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intens.task.model.Skill;
import com.intens.task.service.SkillService;
import com.intens.task.support.SkillDtoToSkill;
import com.intens.task.support.SkillToSkillDto;
import com.intens.task.web.dto.SkillDto;


@RestController
@RequestMapping("/api/skills/")
public class ApiSkillController {
	@Autowired
	private SkillService skillService;
	@Autowired
	private SkillToSkillDto toDto;
	@Autowired
	private SkillDtoToSkill toEntity;
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<SkillDto>> getAmenities(){
		List<Skill> skills=skillService.all();	
		return new ResponseEntity<>(
				toDto.convert(skills),
							HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<SkillDto> getAmenity(@PathVariable Long id){
		Optional<Skill> skill = skillService.one(id);
		if(!skill.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDto.convert(skill.get()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<SkillDto> delete(@PathVariable Long id){
		Skill deleted = skillService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDto.convert(deleted),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<SkillDto> add(
			@Validated @RequestBody SkillDto dto){
		
		Skill persisted = skillService.save(
				toEntity.convert(dto));
		
		return new ResponseEntity<>(
				toDto.convert(persisted), 
				HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<SkillDto> edit(
			@Validated @RequestBody SkillDto dto,
			@PathVariable Long id){
		
		if(!id.equals(dto.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Skill persisted = skillService.save(
				toEntity.convert(dto));
		
		return new ResponseEntity<>(
				toDto.convert(persisted),
				HttpStatus.OK);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
