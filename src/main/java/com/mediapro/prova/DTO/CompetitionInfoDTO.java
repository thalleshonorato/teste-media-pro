package com.mediapro.prova.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CompetitionInfoDTO {

	private long id;
	private String competition;
	private String highlight_title;
	private String thumbnail_url;
	private String highlight_embed;
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
	public String getHighlight_title() {
		return highlight_title;
	}
	public void setHighlight_title(String highlight_title) {
		this.highlight_title = highlight_title;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public String getHighlight_embed() {
		return highlight_embed;
	}
	public void setHighlight_embed(String highlight_embed) {
		this.highlight_embed = highlight_embed;
	}
	
	public CompetitionInfoDTO() {
	}
	
	public CompetitionInfoDTO(long id, String competition, String highlight_title, String thumbnail_url,
			String highlight_embed) {

		this.id = id;
		this.competition = competition;
		this.highlight_title = highlight_title;
		this.thumbnail_url = thumbnail_url;
		this.highlight_embed = highlight_embed;
	}
	
}
