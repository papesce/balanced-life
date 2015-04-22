package com.balance.life;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;





import com.balance.life.model.DoneTag;
import com.balance.life.model.DoneTask;
import com.balance.life.model.Goal;
import com.balance.life.model.Tag;
import com.balance.life.model.Task;
import com.balance.life.repo.GoalRepository;
import com.balance.life.util.TaskRow;

@RestController
@RequestMapping("/rest/goals")
public class GoalRestController {

	 @Autowired
	 GoalRepository goalRepository;
	
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET)
	 public List<Goal> getRestGoals( 
			 @RequestHeader(value = "Range") String range,
			 HttpServletResponse response)  {
		 List<Goal> goals = goalRepository.findAll();   
		 String startItem = "0";
		 String endItem = Integer.toString(goals.size() -1); 
		 String totalItems = Integer.toString(goals.size());
		 String responseSt = "items=" + startItem + "-" + endItem + "/"
					+ totalItems;
		response.setHeader("Content-Range", responseSt);

		 return goals;
	 }
	
	 
	 @RequestMapping(method=RequestMethod.POST)
	 @ResponseStatus(HttpStatus.CREATED)
	 public Goal create(@RequestBody @Valid Goal goal) {
		 return this.goalRepository.save(goal);
	 }
	 
	 @RequestMapping(value="/{id}", method=RequestMethod.PUT)
	 public Goal update(@PathVariable("id") long id, @RequestBody @Valid Goal goal) { 
		 //Task task = taskRow.getTask();
		 //String tagString = taskRow.getTagString();
		 //if (!"".equals(tagString)) {
		//	 String[] newTags = tagString.split(",");
		//	 task.getTags().clear();
		//	 for (int i = 0; i < newTags.length; i++) {
		//		 String tagSt = newTags[i].trim();
		//		 Tag newTag = tagRepository.findByName(tagSt);
		//		 if (newTag == null) {
		//			 newTag = new Tag(tagSt);
		//			 tagRepository.save(newTag);
		//		 }
		//		 task.getTags().add(newTag);
		//	 }
		 //}
		 return goalRepository.save(goal);
	 }
	  

}
