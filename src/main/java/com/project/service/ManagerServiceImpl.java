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
import com.project.dto.AdminManagerRequest;
import com.project.repository.ManageRepository;
import com.project.repository.RoleRepository;
@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	ManageRepository manageRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	@Override
	public void addManager(AdminManagerRequest request) {
		
		Manager manager = new Manager();
		manager.setFirstName(request.getFirstName());
		manager.setLastName(request.getLastName());
		manager.setEmail(request.getEmail());
		manager.setGender(request.getGender());
		manager.setMobileNo(request.getMobileNo());
		manager.setPassword(passwordEncoder
				.encode(request.getPassword()));
		
		Address address =new Address();
		address.setArea(request.getArea());
		address.setCity(request.getCity());
		address.setState(request.getState());
		address.setZip(request.getZip());
		manager.setAddress(address);
		
		
		manager.setBonus(request.getBonus());
		manager.setSalary(request.getSalary());
		manager.setDateOfJoining(request.getDateOfJoining());
		manager.setAdharNumber(request.getAdharNumber());
		manager.setQualification(request.getQualification());
		manager.setTotalTeamHandleling(request.getTotalTeamHandleling());
		manager.setExperience(request.getExperience());
		
		List<Role> roles = new ArrayList<>();
		Role managerRole = roleRepository.findByRoleName(RoleName.MANAGER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(managerRole);
		manager.setRoles(roles);
		manageRepository.save(manager);
	}
	@Override
	public void editManager(Manager manager) {
		Manager emailMgr=manageRepository.findByEmail(manager.getEmail()).get();
		
		emailMgr.setFirstName(manager.getFirstName());
		emailMgr.setLastName(manager.getLastName());
		emailMgr.setEmail(manager.getEmail());
		emailMgr.setGender(manager.getGender());
		emailMgr.setMobileNo(manager.getMobileNo());
		emailMgr.setBonus(manager.getBonus());
		emailMgr.setSalary(manager.getSalary());
		emailMgr.setDateOfJoining(manager.getDateOfJoining());
		
		manageRepository.save(emailMgr);
		
	}
	
	@Override
	public void delete(int managerId) {
		manageRepository.deleteById(managerId);
		
	}
	
	
}
