package com.mediapro.prova.providers;

import java.util.List;

import com.mediapro.prova.DTO.ResponseCompetition;
import com.mediapro.prova.models.Competition;

public interface CompetitionProvider {
	
	public List<Competition> getAllCompetitions();
	
	public ResponseCompetition getDefaultCompetitions();
}
