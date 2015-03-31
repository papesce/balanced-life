package com.balance.life;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.balance.life.model.Tag;
import com.balance.life.repo.TagRepository;




@RestController
@RequestMapping("/rest/tags")
public class TagRestController {

	 @Autowired
	 TagRepository tagRepository;
	 
	 
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET, value="/filter")
	 public List<Tag> getRestTasksForMobile() {
		 List<Tag> tags = tagRepository.findAll();   
		 Tag all = new Tag("Show All");
		 tags.add(all);
		 return tags;
	 }
	 
	 
}
