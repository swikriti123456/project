package com.project.service;

import com.project.beans.Worker;
import com.project.dto.ManagerWorkerCrudRequest;

public interface WorkerService {
	

	void deleteWorker(int workerId);

	 void editWorker(Worker worker);

	void addWorker(ManagerWorkerCrudRequest request);
}
