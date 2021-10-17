package com.mediapro.prova.providers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.mediapro.prova.DTO.ResponseCompetition;
import com.mediapro.prova.models.Competition;

@Service
public class CompetitionProviderScoreBat implements CompetitionProvider {
	
	public static final String URLAPI = "https://www.scorebat.com/video-api/v3/";
	
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;


	@Override
	public List<Competition> getAllCompetitions() {
	    CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseCompetition responseCompetition = circuitBreaker.run(() -> restTemplate.getForObject(URLAPI, ResponseCompetition.class), 
			      throwable -> getDefaultCompetitions());
		
		
		return responseCompetition.getResponse();
	}


	@Override
	public ResponseCompetition getDefaultCompetitions() {
	    BufferedReader json = null;
	    ResponseCompetition response = new ResponseCompetition();
		try {
			json = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:competition.json")));
			response = new Gson().fromJson(json, ResponseCompetition.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	

}
