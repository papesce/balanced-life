package com.balance.life.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.TaskAudit;

//not used yet

public interface TaskAuditRepository extends JpaRepository<TaskAudit, Long> {

}
