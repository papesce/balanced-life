package com.balance.life.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.DoneTask;

public interface DoneTaskRepository  extends JpaRepository<DoneTask, Long> {

}
