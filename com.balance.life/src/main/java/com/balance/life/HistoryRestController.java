package com.balance.life;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.balance.life.model.Item;



@RestController
@RequestMapping("/rest/history")
public class HistoryRestController {

	 //Autowired
	 //DoneItemRepository doneTaskRepository;
	
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET)
	 public List<Item> getRestTaskHistory( 
			 @RequestHeader(value = "Range") String range,
			 HttpServletResponse response)  {
		 List<Item> tasks = new ArrayList<Item>();//doneTaskRepository.findAll();   
		 String startItem = "0";
		 String endItem = Integer.toString(tasks.size() -1); 
		 String totalItems = Integer.toString(tasks.size());
		 String responseSt = "items=" + startItem + "-" + endItem + "/"
					+ totalItems;
		response.setHeader("Content-Range", responseSt);

		 return tasks;
	 }
	
}
