package com.timeclock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Clock")
public class ClockEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String newEntryName;
	private String newEntryTime;
	private String newExitTime;
	private String duration;
	
}
