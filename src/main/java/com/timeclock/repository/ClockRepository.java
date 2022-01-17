package com.timeclock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timeclock.model.ClockEntity;

@Repository
public interface ClockRepository extends JpaRepository<ClockEntity, Long> {

}
