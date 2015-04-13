package com.balance.life;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.balance.life.model.Tag;
import com.balance.life.model.Task;
import com.balance.life.repo.TagRepository;
import com.balance.life.repo.TaskRepository;
import com.balance.life.util.TaskRow;




@RestController
@RequestMapping("/rest/tasks")
public class TaskRestController {

	 @Autowired
	 TaskRepository taskRepository;
	 @Autowired
	 TagRepository tagRepository;
	 
	 
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET, value = "/mobile")
	 public List<Task> getRestTasksForMobile() {
		 List<Task> tasks = taskRepository.findAll();   
		 return tasks;
	 }
	 
	 
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET)
	 public List<Task> getRestTasks( 
			 @RequestHeader(value = "Range") String range,
			 @RequestParam(value = "tagId", required = false) Long tagId,
	            HttpServletResponse response) {
		 String[] ranges = range.substring("items=".length()).split("-");
		 int from = Integer.valueOf(ranges[0]);
		 int to = Integer.valueOf(ranges[1]);
		 List<Task> tasks;
		 if (tagId == null || tagId.equals((long)0)) {
			 tasks = taskRepository.findAll();   
		 } else {
			 tasks = taskRepository.findAllByTagsTagId(tagId);
		 }
		 String startItem = "0";
		 String endItem = Integer.toString(tasks.size() -1); 
		 String totalItems = Integer.toString(tasks.size());
		 String responseSt = "items=" + startItem + "-" + endItem + "/"
					+ totalItems;
		response.setHeader("Content-Range", responseSt);
		 return tasks;
	 }
	
	 
	 
	 @RequestMapping(method=RequestMethod.POST)
	 @ResponseStatus(HttpStatus.CREATED)
	 public Task create(@RequestBody @Valid Task task) {
		 return this.taskRepository.save(task);
	 }
	 
	 
	 @RequestMapping(value="/{id}", method=RequestMethod.GET)
	 public Task get(@PathVariable("id") long id) {
		 return this.taskRepository.findOne(id);
	 }
	  
	 @RequestMapping(value="/{id}", method=RequestMethod.PUT)
	 public Task update(@PathVariable("id") long id, @RequestBody @Valid TaskRow taskRow) { 
		 Task task = taskRow.getTask();
		 String tagString = taskRow.getTagString();
		 String[] newTags = tagString.split(","); 
		 task.getTags().clear();
		 for (int i = 0; i < newTags.length; i++) {
			String tagSt = newTags[i].trim();
			Tag newTag = tagRepository.findByName(tagSt);
			if (newTag == null) {
				  newTag = new Tag(tagSt);
				  tagRepository.save(newTag);
			}
			task.getTags().add(newTag);
		 }
		 return taskRepository.save(task);
	 }
	  
	 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	 public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
		 this.taskRepository.delete(id);
		 return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	 }
}
