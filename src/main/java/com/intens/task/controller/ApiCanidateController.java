package com.intens.task.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intens.task.model.Candidate;
import com.intens.task.service.CandidateService;
import com.intens.task.support.CandidateDtoToCandidate;
import com.intens.task.support.CandidateToCandidateDto;
import com.intens.task.support.SkillDtoToSkill;
import com.intens.task.web.dto.CandidateDto;
import com.intens.task.web.dto.SkillDto;

@RestController
@RequestMapping(value="/api/candidates/")
public class ApiCanidateController {
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private CandidateToCandidateDto toDTO;
	@Autowired
	SkillDtoToSkill toSkill;
	
	@Autowired
	private CandidateDtoToCandidate toCandidate;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<CandidateDto>> getCandidates(
			@RequestParam(required=false) String fullName,
			@RequestParam(required=false ) String skillIdes){

		List<Candidate> candidates = null;
		System.out.println("SkillIdes ="+skillIdes);
		if(fullName != null || skillIdes != null ) {
		
			if(fullName ==null){
				candidates =  candidateService.searchBySkills(skillIdes);
			}else {candidates=candidateService.searchByFullName(fullName);
				
			}
			
		}
		else {
			candidates = candidateService.all();
		}
System.out.println("Candidates"+candidates.toString());
		HttpHeaders headers = new HttpHeaders();
		
		return new ResponseEntity<>(
				toDTO.convert(candidates),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<CandidateDto> getCandidate(@PathVariable Long id){
		Optional<Candidate> candidate = candidateService.one(id);
		if(!candidate.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(candidate.get()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<CandidateDto> delete(@PathVariable Long id){
		Candidate deleted = candidateService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(deleted),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<CandidateDto> add(
			@Validated @RequestBody CandidateDto dto){
		System.out.println("dto is "+dto.toString());
	
		Candidate persisted = candidateService.save(
				toCandidate.convert(dto));
		System.out.println("persisted "+persisted.toString());
		return new ResponseEntity<>(
				toDTO.convert(persisted),
				HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<CandidateDto> edit(
			@Validated @RequestBody CandidateDto candidateDto,
			@PathVariable Long id){
		
		if(!id.equals(candidateDto.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Candidate persisted = candidateService.save(
				toCandidate.convert(candidateDto));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted),
				HttpStatus.OK);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
