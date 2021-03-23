package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.beans.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer>
{

}
