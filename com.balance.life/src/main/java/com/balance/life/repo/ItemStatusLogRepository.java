package com.balance.life.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;




import com.balance.life.model.Item;
import com.balance.life.model.ItemStatusLog;

public interface ItemStatusLogRepository extends JpaRepository<ItemStatusLog, Long>{
	
	ItemStatusLog findByItemAndStatus_name(Item item, String statusName);

}
