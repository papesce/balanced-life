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

import com.balance.life.model.IDefaultStatus;
import com.balance.life.model.Item;
import com.balance.life.model.ItemStatusLog;
import com.balance.life.repo.ItemRepository;
import com.balance.life.repo.ItemStatusLogRepository;
import com.balance.life.util.ItemHistory;



@RestController
@RequestMapping("/rest/history")
public class HistoryRestController {

	 @Autowired
	 ItemRepository itemRepository;
	 @Autowired
	 ItemStatusLogRepository itemStatusLogRepo;
	 
	 @ResponseBody
	 @RequestMapping(method=RequestMethod.GET)
	 public List<ItemHistory> getRestTaskHistory( 
			 @RequestHeader(value = "Range") String range,
			 HttpServletResponse response)  {
		 List<Item> items = itemRepository.getHistory();
		 List<ItemHistory> itemhs = new ArrayList<ItemHistory>();
		 for (Item item : items) {
			 ItemStatusLog doneStatus = itemStatusLogRepo.findByItemAndStatus_name(item, IDefaultStatus.DONE); 
			 ItemHistory itemh = new ItemHistory(item, doneStatus.getTimestamp());
			 itemhs.add(itemh);
		}
		 
		 String startItem = "0";
		 String endItem = Integer.toString(items.size() -1); 
		 String totalItems = Integer.toString(items.size());
		 String responseSt = "items=" + startItem + "-" + endItem + "/"
					+ totalItems;
		response.setHeader("Content-Range", responseSt);

		 return itemhs;
	 }
	
}
