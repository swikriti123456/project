package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.beans.Address;
import com.project.beans.Manager;
import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.dto.AdminMangerRequest;
import com.project.repository.ManagerRepository;
import com.project.repository.RoleRepository;
@Service
public class ManagerServiceImpl implements ManagerService
{

	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void edit(Manager manager) {
		Manager oldManager=managerRepository.findByEmail(manager.getEmail()).get();
		oldManager.setFirstName(manager.getFirstName());

		oldManager.setLastName(manager.getLastName());
		oldManager.setMobileNo(manager.getMobileNo());
		oldManager.setGender(manager.getGender());
		oldManager.setEmail(manager.getEmail());
		oldManager.setBonus(manager.getBonus());
		oldManager.setSalary(manager.getSalary());
		
		oldManager.setTotalTeamHandeling(manager.getTotalTeamHandeling());
		
		
		managerRepository.save(oldManager);
		
	}

	@Override
	public void delete(int managerId) {
		managerRepository.deleteById(managerId);
	}

	@Override
	public void addManager(AdminMangerRequest request) {
		Manager manager=new Manager();
		
		manager.setFirstName(request.getFirstName());
		manager.setLastName(request.getLastName());
		manager.setMobileNo(request.getMobileNo());
		manager.setGender(request.getGender());
		manager.setEmail(request.getEmail());
		manager.setPassword(passwordEncoder.encode(request.getPassword()));
	
		Address address=new Address();
		address.setArea(request.getArea());
		address.setState(request.getState());
		address.setCity(request.getCity());
		address.setZip(request.getZip());
		manager.setAddress(address);
		
		manager.setDateOfJoining(request.getDateOfJoining());
		manager.setAadharNumber(request.getAadharNumber());
		manager.setQualification(request.getQualification());
		manager.setSalary(request.getSalary());
		manager.setBonus(request.getBonus());
		manager.setTotalTeamHandeling(request.getTotalTeamHandeling());
		manager.setExperience(request.getExperience());
		
		List<Role> roles = new ArrayList<>();
		Role managerRole = roleRepository.findByRoleName(RoleName.MANAGER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(managerRole);
		manager.setRoles(roles);
		managerRepository.save(manager);
	}

}
