package com.balance.life.util;

import java.util.Calendar;

import com.balance.life.model.Item;
import com.balance.life.model.ItemStatusLog;
import com.balance.life.model.Status;
import com.balance.life.repo.ItemStatusLogRepository;
import com.balance.life.repo.StatusRepository;

public class StatusLogger {

	public static void changeStatus(Item item, StatusRepository statusRepository,
			String statusName, ItemStatusLogRepository itemStatusLogRepository) {
		 Status status = statusRepository.findByName(statusName);
		 if (status == null) {
			 status = new Status(statusName);
			 statusRepository.save(status);
		 }
		 item.setCurrentStatus(status);
		 ItemStatusLog logStatus = new ItemStatusLog();
		 logStatus.setItem(item);
		 logStatus.setStatus(status);
		 logStatus.setTimestamp(Calendar.getInstance().getTime());
		 itemStatusLogRepository.save(logStatus);
	}

}
