package com.mediapro.prova.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CompetitionDTO {

	private long id;
	private String competition;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCompetition() {
		return competition;
	}
	public void setCompetition(String competition) {
		this.competition = competition;
	}
	
	public CompetitionDTO() {
	}
	
	public CompetitionDTO(long id, String competition) {
		this.id = id;
		this.competition = competition;
	}

}
