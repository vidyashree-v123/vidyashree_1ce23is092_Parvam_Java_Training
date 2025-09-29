package com.springbootgamingclubproject.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgamingclubproject.dto.request.TransactionRequestDTO;
import com.springbootgamingclubproject.dto.response.TransactionResponse;
import com.springbootgamingclubproject.entity.DailyCollection;
import com.springbootgamingclubproject.entity.Game;
import com.springbootgamingclubproject.entity.Member;
import com.springbootgamingclubproject.entity.Transaction;
import com.springbootgamingclubproject.exception.ResourceNotFoundException;
import com.springbootgamingclubproject.repository.DailyCollectionRepository;
import com.springbootgamingclubproject.repository.GameRepository;
import com.springbootgamingclubproject.repository.MemberRepository;
import com.springbootgamingclubproject.repository.TransactionRepository;
import com.springbootgamingclubproject.util.MapperUtil;

@Service
public class TransactionService {
	@Autowired
	private  TransactionRepository transactionRepository;
	@Autowired
	private  MemberRepository memberRepository;
	@Autowired
	private  GameRepository gameRepository;
	@Autowired
	private DailyCollectionRepository dailyCollectionRepository;


	public TransactionResponse createTransaction(TransactionRequestDTO request) {
		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + request.getMemberId()));
		Game game = gameRepository.findById(request.getGameId())
				.orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + request.getGameId()));

		// Calculate cost
		BigDecimal cost = game.getCostPerHour().multiply(request.getPlayTimeHrs());

		if (member.getBalance().compareTo(cost) < 0) {
			throw new RuntimeException("Insufficient balance");
		}

		// Deduct wallet
		member.setBalance(member.getBalance().subtract(cost));
		memberRepository.save(member);

		// Save transaction
		Transaction transaction = MapperUtil.transactionRequestToEntity(request, member, game);
		transaction.setCost(cost); // make sure cost is saved
		transaction = transactionRepository.save(transaction);

		// --- Update DailyCollection ---
		LocalDate today = LocalDate.now();
		DailyCollection dailyCollection = dailyCollectionRepository.findByCollectionDate(today)
				.orElse(new DailyCollection(today, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

		dailyCollection.setTotalSpent(dailyCollection.getTotalSpent().add(cost));
		dailyCollection.setNetCollection(dailyCollection.getTotalRecharges().subtract(dailyCollection.getTotalSpent()));

		dailyCollectionRepository.save(dailyCollection);
		// --- End DailyCollection update ---

		return MapperUtil.transactionToResponse(transaction);
	}

	public List<TransactionResponse> getAllTransactions() {
		return MapperUtil.transactionListToResponseList(transactionRepository.findAll());
	}

	public List<TransactionResponse> getTransactionsByMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberId));
		return MapperUtil.transactionListToResponseList(transactionRepository.findByMember(member));
	}

	public List<TransactionResponse> getTransactionsByGame(Long gameId) {
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + gameId));
		return MapperUtil.transactionListToResponseList(transactionRepository.findByGame(game));
	}
}
