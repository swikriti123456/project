package com.project.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.Manager;
import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.beans.User;
import com.project.beans.Worker;
import com.project.dto.AdminManagerRequest;
import com.project.message.MessageResponse;
import com.project.repository.ManageRepository;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import com.project.repository.WorkerRepository;
import com.project.service.ManagerService;
import com.project.service.WorkerService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ManageRepository managerRepository;
	
	@Autowired
	WorkerService workerService;
	
      @Autowired 
	  private WorkerRepository workerRepository;
	  
	 @Autowired 
	  private UserRepository userRepository;
	 @Autowired
		private RoleRepository roleRepository;
		
	@PostMapping("/addManager")
	public ResponseEntity<?> registerManager(@RequestBody AdminManagerRequest request){
		Optional<Manager> optManager= managerRepository.findByEmail(request.getEmail());
		if(optManager.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error:User Invalid"));
		}
		managerService.addManager(request);
		return ResponseEntity.ok("Manager Registered successfully");
	}
	
	@GetMapping(value="/findAdmin",produces = "application/json")
	public ResponseEntity<?> forAdmin(){
		Role adminRole = roleRepository.findByRoleName(RoleName.ADMIN)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		List<User> userAdmin=userRepository.findByRoles(adminRole);
		
		return ResponseEntity.ok(userAdmin);
	}
	
	@GetMapping(value="/userList",produces = "application/json")
	public ResponseEntity<?> forUserList(){
		Role userRole = roleRepository.findByRoleName(RoleName.USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		List<User> user=userRepository.findByRoles(userRole);
		
		return ResponseEntity.ok(user);
	}
	
	
	
	@GetMapping(value="/managerList",produces = "application/json")
	public ResponseEntity<?> forShowManager(){
		
		Role managerRole = roleRepository.findByRoleName(RoleName.MANAGER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		List<Manager> managerList=managerRepository.findByRoles(managerRole);
		
		return ResponseEntity.ok(managerList);
	}
	
	@PutMapping(value="/editManager",produces = "application/json",consumes = "application/json")
	public ResponseEntity<?> editManager(@RequestBody Manager manager){
		Optional<Manager> optManager=managerRepository.findByEmail(manager.getEmail());
		if(!optManager.isPresent()) {
			return ResponseEntity.ok("User not found");
		}
		managerService.editManager(manager);
		return ResponseEntity.ok("Edit successfully..");
	}
	
	@DeleteMapping(value="/deleteManager/{managerId}",produces = "application/json")
	public ResponseEntity<?> forDeleteManager(@PathVariable int managerId){
		managerService.delete(managerId);
		return ResponseEntity.ok("Deleted successfully");
	}
	
}
