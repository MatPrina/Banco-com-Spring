package com.banco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banco.domains.Transfer;
import com.banco.services.TransferService;

@RestController
public class TransferController {
	
	@Autowired
	private TransferService service;
	
	@PostMapping("/transfer")
	public ResponseEntity<Transfer> realizaTransferencia(@RequestBody Transfer transfer) throws Exception {
		Transfer newTransfer =  service.doTransfer(transfer.getSenderId(), transfer.getReceiverId(), transfer.getAmount());
		
		return ResponseEntity.ok(newTransfer);
	}
	
	@GetMapping("/{id}/transfers")
	public ResponseEntity<List<Transfer>> listUserAllTransfers(@PathVariable Long id) throws Exception{
		List<Transfer> transfers = service.findUserAllTransfers(id);
		return ResponseEntity.ok(transfers);
	}
	
	@GetMapping("/{id}/transfers/sent")
	public ResponseEntity<List<Transfer>> listUserSentTransfers(@PathVariable Long id) throws Exception{
		List<Transfer> transfers = service.findUserSentTransfers(id);
		return ResponseEntity.ok(transfers);
	}
	
	@GetMapping("/{id}/transfers/received")
	public ResponseEntity<List<Transfer>> listUserReceivedTransfers(@PathVariable Long id) throws Exception{
		List<Transfer> transfers = service.findUserReceivedTransfers(id);
		return ResponseEntity.ok(transfers);
	}
}
