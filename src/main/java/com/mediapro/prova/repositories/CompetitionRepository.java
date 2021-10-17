package com.mediapro.prova.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediapro.prova.models.Competition;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

}
