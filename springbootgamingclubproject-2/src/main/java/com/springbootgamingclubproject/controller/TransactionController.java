package com.springbootgamingclubproject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootgamingclubproject.api.ApiResponse;
import com.springbootgamingclubproject.dto.request.TransactionRequestDTO;
import com.springbootgamingclubproject.dto.response.TransactionResponse;
import com.springbootgamingclubproject.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(@RequestBody TransactionRequestDTO request) {
		TransactionResponse response = transactionService.createTransaction(request);
		return ResponseEntity.ok(ApiResponse.success("Transaction successful", response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllTransactions() {
		List<TransactionResponse> response = transactionService.getAllTransactions();
		return ResponseEntity.ok(ApiResponse.success("Transactions fetched successfully", response));
	}

	@GetMapping("/member/{memberId}")
	public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactionsByMember(@PathVariable Long memberId) {
		List<TransactionResponse> response = transactionService.getTransactionsByMember(memberId);
		return ResponseEntity.ok(ApiResponse.success("Member transactions fetched successfully", response));
	}

	@GetMapping("/game/{gameId}")
	public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactionsByGame(@PathVariable Long gameId) {
		List<TransactionResponse> response = transactionService.getTransactionsByGame(gameId);
		return ResponseEntity.ok(ApiResponse.success("Game transactions fetched successfully", response));
	}
}
