package com.project.service;

import com.project.beans.Manager;
import com.project.beans.User;
import com.project.dto.AdminMangerRequest;

public interface ManagerService {

	void edit(Manager request);

	void delete(int managerId);

	void addManager(AdminMangerRequest request);


}