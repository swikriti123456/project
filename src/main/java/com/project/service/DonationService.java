package com.project.service;

import java.util.Optional;

import com.project.beans.User;
import com.project.dto.DonationRequest;
import com.project.dto.MoneyRequest;

public interface DonationService {

	void submitDonation(DonationRequest request, Optional<User> optUser);

	void submitMoney(MoneyRequest request, Optional<User> optUser);

}
