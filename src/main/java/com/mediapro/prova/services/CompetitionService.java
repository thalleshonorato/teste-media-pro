package com.mediapro.prova.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mediapro.prova.DTO.CompetitionDTO;
import com.mediapro.prova.DTO.CompetitionInfoDTO;
import com.mediapro.prova.DTO.HighlightDTO;
import com.mediapro.prova.models.Competition;
import com.mediapro.prova.models.Video;
import com.mediapro.prova.providers.CompetitionProviderScoreBat;
import com.mediapro.prova.repositories.CompetitionRepository;

@Service
public class CompetitionService {
	
	@Autowired
	private CompetitionProviderScoreBat competitionProvider;
	
	@Autowired
	private CompetitionRepository competitionRepository;
	
	public void synchronizeCompetitions() {
		List<Competition> competitions = competitionProvider.getAllCompetitions();
		try {
			competitionRepository.saveAll(competitions);
		}catch(Exception e) {
			
		}
	}
	
	public ResponseEntity getAllCompetitions(){
		synchronizeCompetitions();
		
		List<Competition> competitions = competitionRepository.findAll();

		List<CompetitionDTO> competitionsDTO = competitions
			.stream()
			.map(c -> new CompetitionDTO(c.getId(), c.getCompetition()))
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(competitionsDTO); 
	}
	
	public ResponseEntity getHighlightsByCompetitionId(long id) {
		Optional<Competition> competition = competitionRepository.findById(id);
		
		if(competition.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Competition with Id not exists!");
		}
		
		List<HighlightDTO> highlights = competition
												.get()
												.getVideos()
												.stream()
												.map(video -> new HighlightDTO(video.getId(), video.getTitle()))
												.collect(Collectors.toList());
		
		return ResponseEntity.ok(highlights);
	}
	
	public ResponseEntity getCompetitionByCompetitionIdAndHighlightId(long idCompetition, long idHighlight) {
		Optional<Competition> competition = competitionRepository.findById(idCompetition);
		if(competition.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Competition with Id not exists!");
		}
		Optional<Video> video = competition.get().getVideos().stream().filter(c -> c.getId() == idHighlight).findAny();
		if(video.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Highlight with Id not exists!");
		}
		
		CompetitionInfoDTO competitionInfoDTO = new CompetitionInfoDTO(
				competition.get().getId(),
				competition.get().getCompetition(),
				competition.get().getThumbnail(),
				video.get().getTitle(),
				video.get().getEmbed()
		);
		
		return ResponseEntity.ok(competitionInfoDTO);
	}

}
