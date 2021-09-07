package com.project.service;

import com.project.beans.Manager;
import com.project.dto.AdminManagerRequest;

public interface ManagerService {

	void addManager(AdminManagerRequest request);
	void editManager(Manager manager);
	void delete(int managerId);

}
