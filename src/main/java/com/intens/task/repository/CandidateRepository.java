
package com.intens.task.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intens.task.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	//Fali mi uslov da iybegnem dupliranje
//	@Query(value="SELECT DESTINCT * FROM candidate, candidate_skills   WHERE "
//	+"(candidate.id = candidate_skills.candidate_id) AND (?2 IS null OR candidate_skills.skills_id=?2) AND  (?1 IS NULL OR candidate.full_name=?1) ",nativeQuery = true)
//@Query(value="Select * from candidate, candidate_skills where  "+
//"(candidate.id = candidate_skills.candidate_id) AND (?2 IS null OR candidate_skills.skills_id=?2) AND  (?1 IS NULL OR candidate.full_name=?1) ",nativeQuery = true)	
//@Query(value="SELECT t0.* FROM candidate t0 left join candidate_skills t1 on t1.candidate_id = t0.id\r\n" + 
//		"left join skill t2 on t2.id = t1.skills_id " + 
//		"where t0.full_name like '%?1%'or t2.id in (?2) " + 
//		"group by t0.id ",nativeQuery = true)	

	@Query(value="SELECT t0.* FROM candidate t0 left join candidate_skills t1 on t1.candidate_id = t0.id " + 
		
			"where ( t0.id=t1.candidate_id )  and ( t1.skills_id = ?1) " + 
			"group by t0.id ",nativeQuery = true)
	
//	@Query(value="SELECT t0.* FROM candidate t0 left join candidate_skills t1 on t1.candidate_id = t0.id " + 
//		//	"left join skill t2 on t2.id = t1.skills_id " + 
//			"where (?1 is null or t0.full_name= ?1)  and (?2 is null or t1.skills_id = (?2)) " + 
//			"group by t0.id ",nativeQuery = true)
//	
	List<Candidate> searchBySkills(
			@Param("skill") Long skill);

	List<Candidate> findByFullName(String fullName);

}
