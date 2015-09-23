package com.balance.gym.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balance.gym.model.Excercise;
import com.balance.gym.repository.ExcerciseRepository;

@Controller
public class ExcerciseController {
	
	
	@Autowired
	private ExcerciseRepository eRepo;

	 @RequestMapping("/excercise")
	 public @ResponseBody Excercise getExcerciseTest(
	            @RequestParam(value="name", required=false, defaultValue="Bench Press") String name) {
	        return new Excercise(name);
	    }
	
	 @RequestMapping("/excercises")
	 public @ResponseBody List<Excercise> getExcercises(
	            @RequestParam(value="name", required=false, defaultValue="Bench Press") String name) {
	     if (eRepo.count() == 0) {
	    	 eRepo.save(new Excercise("Bench Press"));
	    	 eRepo.save(new Excercise("Barbel Curl"));
	     }
	     return eRepo.findAll();
	 }
		 
}
