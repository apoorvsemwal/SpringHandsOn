package com.conference.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conference.model.Speaker;
import com.conference.repository.SpeakerRepository;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {
	
	//Hard Coded Reference - Could have used Factory Design Pattern but we'll let Spring do that.
	//private SpeakerRepository speakerRepo = new HibernateSpeakerRepositoryImpl();
	
	private SpeakerRepository speakerRepo;
	
	public SpeakerServiceImpl() {
		super();
	}

	//For constructor based injection
	public SpeakerServiceImpl(SpeakerRepository speakerRepo) {
		super();
		this.speakerRepo = speakerRepo;
	}
	
	//For setter based injection
	//This will automatically inject this dependency which we were calling explicitly earlier 
	//in the config class.
	@Autowired 
	public void setSpeakerRepo(SpeakerRepository speakerRepo) {
		this.speakerRepo = speakerRepo;
	}

	@Override
	public List<Speaker> findAll() {
		return speakerRepo.findAll();
	} 	
}
