package com.intens.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intens.task.model.Skill;
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

}
