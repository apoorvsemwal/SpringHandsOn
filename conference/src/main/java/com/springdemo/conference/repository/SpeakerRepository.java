package com.springdemo.conference.repository;

import java.util.List;

import com.springdemo.conference.model.Speaker;

public interface SpeakerRepository {

	List<Speaker> findAll();

}