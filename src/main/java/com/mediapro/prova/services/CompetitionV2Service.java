package com.mediapro.prova.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
public class CompetitionV2Service {
	
	@Autowired
	private CompetitionProviderScoreBat competitionProvider;
	
	@Autowired
	private CompetitionRepository competitionRepository;
	
	@Cacheable(cacheNames = "Competitions", key="#root.method.name")
	public List<Competition> getCompetitions() {
		List<Competition> competitions = competitionProvider.getAllCompetitions();
		IntStream.range(0, competitions.size())
			.forEach(index -> competitions.get(index).setId(index + 1));
	
		competitions.forEach(competition -> competition.setVideos(setVideoIdAsIndex(competition.getVideos())));
		
		return competitions;
	}
	
	public List<Video> setVideoIdAsIndex(List<Video> videos) {
		IntStream.range(0, videos.size())
		.forEach(index -> videos.get(index).setId(index + 1));
		
		return videos;
	}
	
	@Cacheable(cacheNames = "Competitions", key="#root.method.name")
	public ResponseEntity getAllCompetitions(){
		
		List<Competition> competitions = getCompetitions();

		List<CompetitionDTO> competitionsDTO = competitions
			.stream()
			.map(c -> new CompetitionDTO(c.getId(), c.getCompetition()))
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(competitionsDTO); 
	}
	
	@Cacheable(cacheNames = "Highlights", key="#id")
	public ResponseEntity getHighlightsByCompetitionId(long id) {
		
		List<Competition> competitions = getCompetitions();
		
		Optional<Competition> competition = competitions.stream().filter(c -> c.getId() == id).findAny();
		
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
	
	@Cacheable(cacheNames = "CompetitionsInfo", key="{#idCompetition, #idHighlight}")
	public ResponseEntity getCompetitionByCompetitionIdAndHighlightId(long idCompetition, long idHighlight) {
		
		List<Competition> competitions = getCompetitions();
		
		Optional<Competition> competition = competitions.stream().filter(c -> c.getId() == idCompetition).findAny();
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
