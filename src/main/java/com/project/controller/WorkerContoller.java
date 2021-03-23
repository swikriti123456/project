package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.beans.Worker;
import com.project.dto.ManagerWorkerCrudRequest;
import com.project.repository.RoleRepository;
import com.project.repository.WorkerRepository;
import com.project.service.WorkerService;

public class WorkerContoller {

	@Autowired
	private WorkerRepository workerRepository;

	@Autowired
	private WorkerService workerService;
	
	@Autowired
	RoleRepository roleRepository;

	@GetMapping(value="/findWorker/{email}",produces = "application/json")
	public ResponseEntity<?> findProfile(@RequestParam String email){
		
		Optional<Worker> workerList=workerRepository.findByEmail(email);
		
		return ResponseEntity.ok(workerList);
	}
	
	
	@PutMapping(value = "/editWorker", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> forEditManager(@RequestBody Worker worker) {
		Optional<Worker> optWorker = workerRepository.findByEmail(worker.getEmail());
		if (optWorker.isPresent()) {
			workerService.editWorker(worker);
			return ResponseEntity.ok("edited successfully");
		}

		return ResponseEntity.ok("user not present");
	}
}
