package com.spring.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.entity.Musician;
import com.spring.rest.error.NotFoundException;

@RestController
@RequestMapping("/api")
public class MusicianRestController {
	
	private List<Musician> musicianList = new ArrayList<Musician>();
	
	@PostConstruct
	public void populateMusicianList() {
		
		Musician musician1 = new Musician("Bruce", "Dickinson");
		Musician musician2 = new Musician("John", "Bon Jovi");
		Musician musician3 = new Musician("Axl", "Rose");
		
		musicianList.add(musician1);
		musicianList.add(musician2);
		musicianList.add(musician3);
	}
	
	@GetMapping("/musicians")
	public List<Musician> getMusicians() {
		return musicianList;
	}
	
	@GetMapping("/musicians/{id}")
	public Musician getMusician(@PathVariable int id) {
		
		if (id < 0 || id >= musicianList.size()) {
			throw new NotFoundException("Musician not found");
		}
		
		return musicianList.get(id);
	}
}
