package com.estudiospallione.nina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudiospallione.nina.entities.Cheque;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, String> {

}
