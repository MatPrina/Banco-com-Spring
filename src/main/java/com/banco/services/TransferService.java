package com.banco.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.domains.Transfer;
import com.banco.domains.User;
import com.banco.repositories.TransferRepository;

@Service
public class TransferService {
	
	@Autowired
	private TransferRepository transferRep;
	
	@Autowired
	private UserService userService;
	
	public Transfer doTransfer(Long senderId, Long receiverId, BigDecimal amount) throws Exception{
		this.validateUser(receiverId);
		this.validateUser(senderId);
		
		Transfer newTransfer = new Transfer(senderId, receiverId, amount);
		
		User sender = userService.findById(newTransfer.getSenderId());
		User receiver = userService.findById(newTransfer.getReceiverId());
		
		if(sender.getBalance().compareTo(newTransfer.getAmount()) < 0) {
			throw new Exception("Pagante não possui saldo suficiente");
		}
		
		BigDecimal newSenderBalance = sender.getBalance().subtract(newTransfer.getAmount());
		sender.setBalance(newSenderBalance);
		
		BigDecimal newReceiverBalance = receiver.getBalance().add(newTransfer.getAmount());
		receiver.setBalance(newReceiverBalance);
		
		transferRep.save(newTransfer);
		
		return newTransfer;
		
	}
	
	public List<Transfer> findUserAllTransfers(Long id) throws Exception{
		this.validateUser(id);
		
		List<Transfer> sentTransfers = transferRep.findTransferBySenderId(id);
		List<Transfer> receivedTransfers = transferRep.findTransferByReceiverId(id);
		
		sentTransfers.addAll(receivedTransfers);
		
		List<Transfer> allTransfers = sentTransfers;
		
		return allTransfers;
		
	}
	
	public List<Transfer> findUserSentTransfers(Long id) throws Exception{
		this.validateUser(id);
		
		List<Transfer> sentTransfers = transferRep.findTransferBySenderId(id);
		
		return sentTransfers;
	}
	
	public List<Transfer> findUserReceivedTransfers(Long id) throws Exception{
		this.validateUser(id);
		
		List<Transfer> receivedTransfers = transferRep.findTransferByReceiverId(id);
		
		return receivedTransfers;
	}
	
	public void validateUser(Long id) throws Exception{
		User user = userService.findById(id);
		
		if(user == null) {
			throw new Exception("Usuário não encontrado");
		}
	}
	
}
