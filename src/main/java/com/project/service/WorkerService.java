package com.project.service;

import com.project.beans.Worker;
import com.project.dto.AdminManagerRequest;
import com.project.dto.ManagerWorkerCrudRequest;


public interface WorkerService {
	void addWorker(ManagerWorkerCrudRequest request);
	void editWorker(Worker worker);

	void deleteWorker(int workerId);
}
