package com.balance.life.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.DoneItem;

public interface DoneItemRepository  extends JpaRepository<DoneItem, Long> {

}
