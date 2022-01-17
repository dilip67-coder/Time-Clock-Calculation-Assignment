package com.timeclock.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timeclock.model.ClockEntity;
import com.timeclock.service.ClockService;

@Controller
public class TimeClockController {

	@Autowired
	private ClockService service;

	@RequestMapping("/")
	public String timeClockHome(Model model) {

		List<ClockEntity> entryData = service.getAllData();
		
		//System.out.println("Entry Data -- > "+entryData);
		
		model.addAttribute("entryData", entryData);
		
		return "timeClock";
	}

	@RequestMapping("/calculateTime")
	public String newMember() {
	/*	
//		@RequestParam("entryTime") String entryTime,
//		@RequestParam("exitTime") String exitTime,
//		Model model
//		LocalDateTime entryDateTime = LocalDateTime.parse(entryTime);
//		LocalDateTime exitDateTime = LocalDateTime.parse(exitTime);
//		
//		  System.out.println("Entry Time -->  "+entryDateTime);
//		  System.out.println("Exit Time ==== >  "+exitDateTime);
//		 
//		Duration duration = Duration.between(entryDateTime, exitDateTime);
//		
//		/*
//		 * System.out.println("duration Time Days ==== >  "+duration.toDays());
//		 * System.out.println("duration Time hours ==== >  "+duration.toHours());
//		 * System.out.println("duration Time Minutes ==== >  "+duration.toMinutes());
//		 
//		model.addAttribute("days", duration.toDays());
//		model.addAttribute("hours", duration.toHours());
//		model.addAttribute("minutes", duration.toMinutes());
*/
		
		
		
		/* model.addAttribute("entryData", entryData); */
		
		return "timeClock";
	}

	@RequestMapping("/save")
	public String save(ClockEntity entity) {

		LocalDateTime entryDateTime = LocalDateTime.parse(entity.getNewEntryTime());
		LocalDateTime exitDateTime = LocalDateTime.parse(entity.getNewExitTime());
		Duration duration = Duration.between(entryDateTime, exitDateTime);
		String dur = "In Days -> "+duration.toDays() + "days \n In hours ->  "+duration.toHours()+ "hours \n In Minutes ->  "+duration.toMinutes()+"minutes";
		entity.setDuration(dur);
		
		service.saveEntry(entity);

		return "redirect:/";
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam("updateId") long id,
			@RequestParam("updateEntryName") String name,
			@RequestParam("updateEntryTime") String entryTime,
			@RequestParam("updateExitTime") String exitTime) {
		ClockEntity entity = new ClockEntity();
		
		System.out.println("Entry Id ==> "+id);
		entity.setId(id);
		entity.setNewEntryName(name);
		entity.setNewEntryTime(entryTime);
		entity.setNewExitTime(exitTime);
		
		  LocalDateTime entryDateTime = LocalDateTime.parse(entryTime);
		  LocalDateTime exitDateTime = LocalDateTime.parse(exitTime);
		  Duration duration = Duration.between(entryDateTime, exitDateTime); 
		  String dur = "In Days -> "+duration.toDays() +
		  "days \n In hours ->  "+duration.toHours()+
		  "hours \n In Minutes ->  "+duration.toMinutes()+"minutes";
			
			  System.out.println("Entry Time ==> "+entryDateTime);
			  System.out.println("Exit Time ==> "+exitDateTime);
			  
			 
		  entity.setDuration(dur);	  
		  
		service.updateById(entity);
		
		
		System.out.println("Entity: "+ entity);
			
		return "redirect:/";
	}
	
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		
		System.out.println("Delete method");
		
		service.deleteById(id);
		
		
		return "redirect:/";
	}
}
