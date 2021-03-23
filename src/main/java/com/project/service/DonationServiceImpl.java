package com.project.service;

import java.util.List;
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
//@Service
public class DonationServiceImpl implements DonationService
{
	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	private MoneyRepository moneyRepository;
	
	@Override
	public void submitDonation(DonationRequest request, Optional<User> optUser) {
		
		Donation donation=new Donation();
		donation.setDonationCateg(request.getDonationCateg());
		donation.setOtherCateg(request.getOtherCateg());
		donation.setQty(request.getQty());
		
		donation.setHouseNo(request.getHouseNo());
		donation.setColony(request.getColony());
		donation.setLandMark(request.getLandMark());
		donation.setPinNo(request.getPinNo());
		donation.setCity(request.getCity());
		donation.setState(request.getState());
		donation.setUser(optUser.get());
		donationRepository.save(donation);
		
	}


	@Override
	public void submitMoney(MoneyRequest request, Optional<User> optUser) {
		MoneyTransfer money=new MoneyTransfer();
		money.setMoney(request.getMoney());
		
		money.setUser(optUser.get());
		
		moneyRepository.save(money);
	}

}
