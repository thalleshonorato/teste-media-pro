package com.mediapro.prova.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediapro.prova.services.CompetitionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/competition")
@Api("Api Competitions")
@CrossOrigin(origins="*")
public class CompetitionController {
	
    Logger logger = LoggerFactory.getLogger(CompetitionController.class);
	
	@Autowired
	private CompetitionService competitionService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation("Returns a list of competitions")
	@ResponseBody
	public ResponseEntity<?> getCompetitions() {
		try {
			return competitionService.getAllCompetitions();
		}catch(Exception e) {
			logger.error("Err!", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation("Returns a list of highlights from a competition")
	@ResponseBody
	public ResponseEntity<?> getCompetitionsById(@PathVariable("id") long id) {
		try {
			return competitionService.getHighlightsByCompetitionId(id);
		}catch(Exception e) {
			logger.error("Err!", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{idCompetition}/highlight/{idHighlight}", method = RequestMethod.GET)
	@ApiOperation("Returns information from a competition and highlights")
	@ResponseBody
	public ResponseEntity<?> getCompetitionsByCompetitionIdAndHighlightId(@PathVariable("idCompetition") long idCompetition, 
			@PathVariable("idHighlight") long idHighlight) {
		
		try {
			return competitionService.getCompetitionByCompetitionIdAndHighlightId(idCompetition, idHighlight);
		}catch(Exception e) {
			logger.error("Err!", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
