package com.balance.life.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.DoneTag;

public interface DoneTagRepository  extends JpaRepository<DoneTag, Long> {

	DoneTag findByName(String name);

}
