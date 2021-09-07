package com.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.beans.Donation;
import com.project.beans.MoneyTransfer;
import com.project.beans.User;
import com.project.dto.DonationRequest;
import com.project.dto.MoneyRequest;
import com.project.repository.DonationRepository;
import com.project.repository.MoneyRepository;
@Service
public class DonationServiceImpl implements DonationService{
	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	MoneyRepository moneyRepository;
	
	@Override
	public boolean submitDonation(DonationRequest request,Optional<User> optuser) {
		
		Donation donation = new Donation();
		donation.setCategory(request.getCategory());
		donation.setOther(request.getOther());
		donation.setQty(request.getQty());
		donation.setHouseNo(request.getHouseNo());
		donation.setZip(request.getZip());
		donation.setColony(request.getColony());
		donation.setLandMark(request.getLandMark());
		donation.setCity(request.getCity());
		donation.setUser(optuser.get());
		donation.setState(request.getState());
		donationRepository.save(donation);
	  return true;
	}


	@Override
	public void submitMoney(MoneyRequest request, Optional<User> optUser) {
		
		MoneyTransfer money= new MoneyTransfer();
		money.setMoney(request.getMoney());
		User user=optUser.get();
		money.setUser(optUser.get());
		moneyRepository.save(money);
	}
}
