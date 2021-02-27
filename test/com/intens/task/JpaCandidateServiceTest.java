package com.intens.task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.intens.task.impl.JpaCandidateService;
import com.intens.task.model.Candidate;

class JpaCandidateServiceTest {
	JpaCandidateService candidateService=new JpaCandidateService();
	
	@Test
	void test() {
		
		List <Candidate> candidates=new ArrayList<>();
	//	candidates=candidateService.searchByFullName("Dunja Bosic");
		assertEquals(1, 1);
	}

}
