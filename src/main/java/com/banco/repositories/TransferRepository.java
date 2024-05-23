package com.banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.domains.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>{
	
	public List<Transfer> findTransferBySenderId(Long id);
	
	public List<Transfer> findTransferByReceiverId(Long id);
}
