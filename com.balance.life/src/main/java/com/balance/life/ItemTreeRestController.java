package com.balance.life;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.balance.life.model.DoneItem;
import com.balance.life.model.DoneTag;
import com.balance.life.model.Tag;
import com.balance.life.model.Item;
import com.balance.life.repo.DoneTagRepository;
import com.balance.life.repo.DoneItemRepository;
import com.balance.life.repo.TagRepository;
import com.balance.life.repo.ItemRepository;
import com.balance.life.util.ItemRow;
import com.balance.life.util.ItemTreeRow;




@RestController
@RequestMapping("/rest/tree")
public class ItemTreeRestController {

	 @Autowired
	 ItemRepository itemRepository;
	 @Autowired
	 DoneItemRepository doneItemRepository;
	 @Autowired
	 DoneTagRepository doneTagRepository;
	 @Autowired
	 TagRepository tagRepository;
	 
	 
	 
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET)
	 public List<Item> getRestItems( 
			 @RequestHeader(value = "Range") String range,
			 @RequestParam(value = "parent", required = false) Long parentId,
	            HttpServletResponse response) {
		 //String[] ranges = range.substring("items=".length()).split("-");
		 //int from = Integer.valueOf(ranges[0]);
		 //int to = Integer.valueOf(ranges[1]);
		 List<Item> items;
		 if (parentId == null) {
			 items = itemRepository.findAll();  
		 } else {
			 //tasks = itemRepository.findAllByTagsTagId(tagId);
			 items = new ArrayList<Item>();
		 }
		 String startItem = "0";
		 String endItem = Integer.toString(items.size() -1); 
		 String totalItems = Integer.toString(items.size());
		 String responseSt = "items=" + startItem + "-" + endItem + "/"
					+ totalItems;
		response.setHeader("Content-Range", responseSt);
		
		//List<ItemTreeRow> treeItems = new ArrayList<ItemTreeRow>();
		//for (Item item : items) {
		//	ItemTreeRow treeItem = new ItemTreeRow(item);
		//	treeItems.add(treeItem);
		//}
		return items;
	 }
	
	 
	 
	

}