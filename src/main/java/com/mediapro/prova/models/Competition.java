package com.mediapro.prova.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "title", "competition", "date", "matchviewUrl", "competitionUrl", "thumbnail" }) })
public class Competition {

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String competition;
	private String matchviewUrl;
	private String competitionUrl;
	private String thumbnail;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss+SSSS")
	private LocalDateTime date;
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Video> videos;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompetition() {
		return competition;
	}
	public void setCompetition(String competition) {
		this.competition = competition;
	}
	public String getMatchviewUrl() {
		return matchviewUrl;
	}
	public void setMatchviewUrl(String matchviewUrl) {
		this.matchviewUrl = matchviewUrl;
	}
	public String getCompetitionUrl() {
		return competitionUrl;
	}
	public void setCompetitionUrl(String competitionUrl) {
		this.competitionUrl = competitionUrl;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
}
