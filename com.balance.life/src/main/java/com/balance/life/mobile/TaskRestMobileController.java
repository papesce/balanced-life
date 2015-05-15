package com.balance.life.mobile;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.balance.life.model.Item;
import com.balance.life.repo.ItemRepository;



@RestController
@RequestMapping("/rest/mobile/tasks")
public class TaskRestMobileController {

	 @Autowired
	 ItemRepository taskRepository;
	 
	 
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET)
	 public List<Item> getRestTasksForMobile() {
		 //String[] ranges = range.substring("items=".length()).split("-");
		 //int from = Integer.valueOf(ranges[0]);
		 //int to = Integer.valueOf(ranges[1]);
		 List<Item> tasks = taskRepository.findAll();   
		 //String startItem = "0";
		 //String endItem = Integer.toString(tasks.size() -1); 
		 //String totalItems = Integer.toString(tasks.size());
		 //String responseSt = "items=" + startItem + "-" + endItem + "/"
		//			+ totalItems;
		//response.setHeader("Content-Range", responseSt);
		 return tasks;
	 }
	 
	 
	
}
