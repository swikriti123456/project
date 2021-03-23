package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.beans.Address;
import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.beans.Worker;
import com.project.dto.ManagerWorkerCrudRequest;
import com.project.repository.RoleRepository;
import com.project.repository.WorkerRepository;
//@Service
public class WorkerServiceImpl implements WorkerService
{

	@Autowired
	private WorkerRepository workerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void editWorker(Worker worker) {
		Worker oldWorker=workerRepository.findByEmail(worker.getEmail()).get();
		 
		oldWorker.setFirstName(worker.getFirstName());
		oldWorker.setLastName(worker.getLastName());
		oldWorker.setMobileNo(worker.getMobileNo());
		oldWorker.setEmail(worker.getEmail());
		oldWorker.setShiftingAvalibility(worker.getShiftingAvalibility());
		
		workerRepository.save(worker);
		
		
	}

	@Override
	public void deleteWorker(int workerId) {
		workerRepository.deleteById(workerId);
	}

	@Override
	public void addWorker(ManagerWorkerCrudRequest request) {
		Worker worker=new Worker();
		
		worker.setFirstName(request.getFirstName());
		worker.setLastName(request.getLastName());
		worker.setMobileNo(request.getMobileNo());
		worker.setGender(request.getGender());
		worker.setEmail(request.getEmailId());
		worker.setPassword(passwordEncoder.encode(request.getPassword()));

		Address address=new Address();
		address.setArea(request.getArea());
		address.setState(request.getState());
		address.setCity(request.getCity());
		address.setZip(request.getZip());
		worker.setAddress(address);
		
		worker.setDateOfJoining(request.getDateOfJoining());
		worker.setAadharNumber(request.getAadharNumber());
		worker.setQualification(request.getQualification());
		worker.setSalary(request.getSalary());
		worker.setBonus(request.getBonus());
		worker.setShiftingAvalibility(request.getShiftingAvalibility());
		List<Role> roles = new ArrayList<>();
		Role managerRole = roleRepository.findByRoleName(RoleName.WORKER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(managerRole);
		worker.setRoles(roles);
		workerRepository.save(worker);
	}
	
}
