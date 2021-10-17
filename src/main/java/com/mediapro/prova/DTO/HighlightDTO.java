package com.mediapro.prova.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;


public class HighlightDTO {

	private long id;
	private String highlight_title;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHighlight_title() {
		return highlight_title;
	}
	public void setHighlight_title(String highlight_title) {
		this.highlight_title = highlight_title;
	}
	
	public HighlightDTO() {
	
	}
	
	public HighlightDTO(long id, String highlight_title) {
		this.id = id;
		this.highlight_title = highlight_title;
	}
	
	
}
