package com.balance.life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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












import com.balance.life.model.Association;
import com.balance.life.model.AssociationMetadata;
import com.balance.life.model.IDefaultStatus;
import com.balance.life.model.Status;
import com.balance.life.model.Tag;
import com.balance.life.model.Item;
import com.balance.life.repo.AssociationMetadataRepository;
import com.balance.life.repo.AssociationRepository;
import com.balance.life.repo.StatusRepository;
import com.balance.life.repo.TagRepository;
import com.balance.life.repo.ItemRepository;
import com.balance.life.util.ItemRow;




@RestController
@RequestMapping("/rest/items")
public class ItemListRestController {

	 @Autowired
	 ItemRepository itemRepository;
	 @Autowired
	 StatusRepository statusRepository;
	 @Autowired
	 AssociationRepository assocRepository;
	 @Autowired
	 AssociationMetadataRepository assocMetadataRepository;
	 @Autowired
	 TagRepository tagRepository;
	 
	 
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET, value = "/mobile")
	 public List<Item> getRestTasksForMobile() {
		 List<Item> tasks = itemRepository.findAll();   
		 return tasks;
	 }
	 
	 
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET)
	 public List<Item> getRestTasks( 
			 @RequestHeader(value = "Range") String range,
			 @RequestParam(value = "tagId", required = false) Long tagId,
	            HttpServletResponse response) {
		 String[] ranges = range.substring("items=".length()).split("-");
		 int from = Integer.valueOf(ranges[0]);
		 int to = Integer.valueOf(ranges[1]);
		 List<Item> tasks;
		 if (tagId == null || tagId.equals((long)0)) {
			 tasks = itemRepository.findAll();   
		 } else {
			 tasks = itemRepository.findAllByTagsTagId(tagId);
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
	 public Item create(@RequestBody @Valid Item item) {
		 Status initialStatus = this.statusRepository.findByName(IDefaultStatus.CREATED);
		 if (initialStatus == null) {
			 initialStatus = new Status(IDefaultStatus.CREATED);
			 statusRepository.save(initialStatus);
		 } else {
			 item.setCurrentStatus(initialStatus);
		 }
		 return this.itemRepository.save(item);
	 }
	 
	 
	 @RequestMapping(value="/{id}", method=RequestMethod.GET)
	 public Item get(@PathVariable("id") long id) {
		 return this.itemRepository.findOne(id);
	 }
	  
	 @RequestMapping(value="/{id}", method=RequestMethod.PUT)
	 public Item update(@PathVariable("id") long id, @RequestBody  ItemRow itemRow) { 
		 Item item = itemRow.getItem();
		 processTagString(item, itemRow);
		 processAssociationString(item, itemRow);
		 //this.statusRepository.save(task.getCurrentStatus());
		 return itemRepository.save(item);
	 }
	  
	 private void processAssociationString(Item item, ItemRow itemRow) {
		 String assocString = itemRow.getAssociationString();
		 if (!"".equals(assocString)) {
			 String[] newAssocs = assocString.split("(?!^\\()(?=\\()");
			 item.getAssociations().clear();
			 for (int i = 0; i < newAssocs.length; i++) {
				 String newAssoc = newAssocs[i].trim();
				 String[] pair = newAssoc.replaceAll("[()]", "").split(",");
				 String assocName =  pair[0].trim(); 
				 long targetId = Long.parseLong(pair[1].trim());
				 
				 Item targetItem = itemRepository.findOne(targetId);
				 if (targetItem != null) {
					 Association assoc = assocRepository.findBySourceAndName(item.getItemId(), assocName);
					 if (assoc == null) {
						 AssociationMetadata assocMetadata = assocMetadataRepository.findByName(assocName);
						 if (assocMetadata == null) {
							 assocMetadata = new AssociationMetadata();
							 assocMetadata.setName(assocName);
							 assocMetadataRepository.save(assocMetadata);
						 }
						 assoc = new Association();
						 assoc.setTarget(targetItem);
						 assoc.setSource(item);
						 assoc.setAssociationMetadata(assocMetadata);
						 assocRepository.save(assoc);
					 }
					 item.getAssociations().add(assoc);
				 }
			 }
		 }
	}


	private void processTagString(Item item, ItemRow itemRow) {
		 String tagString = itemRow.getTagString();
		 if (!"".equals(tagString)) {
			 String[] newTags = tagString.split(",");
			 item.getTags().clear();
			 for (int i = 0; i < newTags.length; i++) {
				 String tagSt = newTags[i].trim();
				 Tag newTag = tagRepository.findByName(tagSt);
				 if (newTag == null) {
					 newTag = new Tag(tagSt);
					 tagRepository.save(newTag);
				 }
				 item.getTags().add(newTag);
			 }
		 }
		
	}


	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	 public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
		 this.itemRepository.delete(id);
		 return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	 }

	 @RequestMapping(value="/markDone/{id}", method=RequestMethod.PUT)
	 public Item update(@PathVariable("id") long id, @RequestBody @Valid Item task) {
		 //DoneItem doneTask = new DoneItem(task.getName());
//		 for (Tag tag : task.getTags()) {
//			 String tagName = tag.getName();
//			DoneTag doneTag = doneTagRepository.findByName(tagName);
//			 if (doneTag == null) {
//				 doneTag = new DoneTag(tagName);
//				 doneTagRepository.save(doneTag);
//			 }
//			 doneTask.getTags().add(doneTag);
//		 }
//		 doneTask.setTimestamp(Calendar.getInstance().getTime());
//		 doneItemRepository.save(doneTask);
		 //taskRepository.delete(task);
		 return task;
	 }	 


}
