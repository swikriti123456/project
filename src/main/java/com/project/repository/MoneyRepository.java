package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.beans.MoneyTransfer;

public interface MoneyRepository extends JpaRepository<MoneyTransfer, Integer>{

}
