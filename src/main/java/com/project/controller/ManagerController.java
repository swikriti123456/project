package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.beans.Worker;
import com.project.dto.ManagerWorkerCrudRequest;
import com.project.message.MessageResponse;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import com.project.repository.WorkerRepository;
import com.project.service.WorkerService;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin(origins = "http://localhost:3000")
public class ManagerController
{
	
	 @Autowired 
	  private UserRepository userRepository;
	 @Autowired
		private RoleRepository roleRepository;
		
	@Autowired
	private WorkerRepository workerRepository;
	
	@Autowired
	private WorkerService workerService;
	
	public static List<Worker> wlist=new ArrayList<>();
	
	/*@GetMapping("/all")
	public ResponseEntity<?> forListWorker(@RequestBody ManagerWorkerCrudRequest request){
		
		List<Worker> workerList=workerRepository.findByLocationId(request.getLocationId());
		
		if(workerList != null) {
			for(Worker w:workerList) {
				wlist.add(w);
			}
		}
		
		return ResponseEntity.ok(workerList);
		
	}*/
	
	@PostMapping(value = "/addWorker", produces = "application/json")
	public ResponseEntity<?> registerManager(@RequestBody ManagerWorkerCrudRequest request) {
		Optional<Worker> optWorker = workerRepository.findByEmail(request.getEmail());
		if (optWorker.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Username is already taken!"));
		}
		workerService.addWorker(request);
		return ResponseEntity.ok(" Registered successfully");
	}
	 
	 @PutMapping("/editWorker")
	 public ResponseEntity<?> forEditWorker(@RequestBody Worker worker){
		 System.out.println("in edit worker");
		workerService.editWorker(worker);
		return ResponseEntity.ok("worker edit successfully");
		 
	 }
		
	 
	 @GetMapping(value="/workerList",produces = "application/json")
		public ResponseEntity<?> forWorker(){
			Role workerRole = roleRepository.findByRoleName(RoleName.WORKER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			List<Worker> workerList=workerRepository.findByRoles(workerRole);
			
			return ResponseEntity.ok(workerList);
		}


	 @DeleteMapping("/deleteWorker/{workerId}")
	 public ResponseEntity<?> forDeleteWorker(@PathVariable int workerId){
		 workerService.deleteWorker(workerId);
		 return ResponseEntity.ok("deleted successfully");
	 }
}


