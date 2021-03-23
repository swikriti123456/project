package com.project.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.Manager;
import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.beans.User;
import com.project.beans.Worker;
import com.project.dto.AdminMangerRequest;

import com.project.message.MessageResponse;
import com.project.repository.EmployeeRepository;
import com.project.repository.ManagerRepository;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import com.project.repository.WorkerRepository;
import com.project.service.AdminService;
import com.project.service.ManagerService;

@RestController
@RequestMapping("/api/admin")
public class AdminController 
{  
	
	  @Autowired 
	  private WorkerRepository workerRepository;
	  
	  @Autowired 
	  private AdminService adminService;
	  
	  @Autowired 
	  private UserRepository userRepository;
	 
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ManagerService managerService;
	
	@GetMapping(value="/findAdmin",produces = "application/json")
	public ResponseEntity<?> forAdmin(){
		List<User> userAdmin=userRepository.findByRoles("admin");
		
		return ResponseEntity.ok(userAdmin);
	}
	
	@PutMapping(value="/editAdmin",produces = "application/json",consumes = "application/json")
	public ResponseEntity<?> forEditAdmin(@RequestBody User user){
		Optional<User> optAdmin=userRepository.findByEmail(user.getEmail());
		if(optAdmin.isPresent()) {
			adminService.editAdmin(user);
			return ResponseEntity.ok("edited successfully");
		}
	
		return ResponseEntity.ok("user not present");
	}
	//for show userlist to the admin 
	@GetMapping(value="/userList",produces = "application/json")
	public ResponseEntity<?> forShowUserList(){
		
		List<User> userList=userRepository.findByRoles("User");
		
		return ResponseEntity.ok(userList);
	}

	
	@GetMapping(value="/workerList",produces = "application/json")
	public ResponseEntity<?> forWorker(){
		Role workerRole = roleRepository.findByRoleName(RoleName.WORKER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		List<Worker> workerList=workerRepository.findByRole(workerRole);
		
		return ResponseEntity.ok(workerList);
	}

	
	@GetMapping(value="/managerList",produces = "application/json")
	public ResponseEntity<?> forShowManager(){
		
		Role managerRole = roleRepository.findByRoleName(RoleName.MANAGER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		List<Manager> managerList=managerRepository.findByRole(managerRole);
		
		return ResponseEntity.ok(managerList);
	}
	
	@DeleteMapping("/deleteManager/{managerId}")
	public ResponseEntity<?> forEditManager(@PathVariable int managerId){
		
		managerService.delete(managerId);
		
		return ResponseEntity.ok("deleted successfully");
	}
	
	@PostMapping(value="/addManager")
	public ResponseEntity<?> registerManager(@RequestBody AdminMangerRequest request){
		Optional<Manager> optManager=managerRepository.findByEmail(request.getEmail());
		if(optManager.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Username is already taken!"));
		}
		managerService.addManager(request);
		return ResponseEntity.ok("manager registered successfully");
	}
	
}
