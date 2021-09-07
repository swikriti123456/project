package com.project.service;

import java.util.Optional;

import com.project.beans.User;
import com.project.dto.DonationRequest;
import com.project.dto.MoneyRequest;

public interface DonationService {


	boolean submitDonation(DonationRequest request, Optional<User> optuser);

	void submitMoney(MoneyRequest request, Optional<User> optUser);

}
