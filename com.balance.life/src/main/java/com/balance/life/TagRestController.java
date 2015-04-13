package com.balance.life;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.balance.life.model.Tag;
import com.balance.life.repo.TagRepository;




@RestController
@RequestMapping("/rest/tags")
public class TagRestController {

	 @Autowired
	 TagRepository tagRepository;
	 

	 //not used
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET)
	 public List<Tag> getTagByName(@RequestParam(value = "tagName", required = true) String tagName) {
		 return tagRepository.findAllByName(tagName);   
		 //return tag;
	 }
	 
	 
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET, value="/filter")
	 public List<Tag> getAllTagsForFilter() {
		 List<Tag> tags = tagRepository.findAll();   
		 Tag all = new Tag("Show All");
		 tags.add(all);
		 return tags;
	 }
	 
	 
}
