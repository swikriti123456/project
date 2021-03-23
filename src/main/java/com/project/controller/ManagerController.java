package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.Manager;
import com.project.beans.User;
import com.project.beans.Worker;
import com.project.dto.ManagerWorkerCrudRequest;
import com.project.message.MessageResponse;
import com.project.repository.ManagerRepository;
import com.project.repository.WorkerRepository;
import com.project.service.ManagerService;
import com.project.service.WorkerService;

//@RestController
@RequestMapping("/api/worker")
public class ManagerController {
	@Autowired
	private WorkerRepository workerRepository;

	@Autowired
	private WorkerService workerService;

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private ManagerService managerService;

	@GetMapping(value="/findManager/{email}",produces = "application/json")
	public ResponseEntity<?> forAdmin(@RequestParam String email){
		Optional<Manager> userManager=managerRepository.findByEmail(email);
		
		return ResponseEntity.ok(userManager);
	}
	
	
	@PutMapping(value = "/editManager", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> forEditManager(@RequestBody Manager manager) {
		Optional<Manager> optManager = managerRepository.findByEmail(manager.getEmail());
		if (optManager.isPresent()) {
			managerService.edit(manager);
			return ResponseEntity.ok("edited successfully");
		}

		return ResponseEntity.ok("user not present");
	}

	@GetMapping(value = "/showWorkerList/{email}", produces = "application/json")
	public ResponseEntity<?> forListWorker(@RequestParam String email) {

		/*
		 * List<Worker>
		 * workerList=workerRepository.findByLocationId(request.getLocationId());
		 * 
		 * if(workerList != null) { for(Worker w:workerList) { wlist.add(w); } }
		 * 
		 * return ResponseEntity.ok(workerList);
		 */
		return ResponseEntity.ok("done");
	}

	@PostMapping(value = "/addWorker", produces = "application/json")
	public ResponseEntity<?> registerManager(@RequestBody ManagerWorkerCrudRequest request) {
		Optional<Worker> optWorker = workerRepository.findByEmail(request.getEmailId());
		if (optWorker.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Username is already taken!"));
		}
		workerService.addWorker(request);
		return ResponseEntity.ok("manager registered successfully");
	}

	@DeleteMapping("/worker/{workerId}")
	public ResponseEntity<?> forDeleteWorker(@PathVariable int workerId) {
		workerService.deleteWorker(workerId);
		return ResponseEntity.ok("deleted successfully");
	}

	
}
