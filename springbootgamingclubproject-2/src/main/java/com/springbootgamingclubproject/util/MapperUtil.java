package com.springbootgamingclubproject.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.springbootgamingclubproject.dto.request.GameRequest;
import com.springbootgamingclubproject.dto.request.MemberRequest;
import com.springbootgamingclubproject.dto.request.RechargeRequestDTO;
import com.springbootgamingclubproject.dto.request.TransactionRequestDTO;
import com.springbootgamingclubproject.dto.response.DailyCollectionResponse;
import com.springbootgamingclubproject.dto.response.GameResponse;
import com.springbootgamingclubproject.dto.response.MemberResponse;
import com.springbootgamingclubproject.dto.response.RechargeResponse;
import com.springbootgamingclubproject.dto.response.TransactionResponse;
import com.springbootgamingclubproject.entity.DailyCollection;
import com.springbootgamingclubproject.entity.Game;
import com.springbootgamingclubproject.entity.Member;
import com.springbootgamingclubproject.entity.Recharge;
import com.springbootgamingclubproject.entity.Transaction;

public class MapperUtil {
	// ----------------- MEMBER -----------------
	public static Member memberRequestToEntity(MemberRequest request) {
		Member member = new Member();
		member.setName(request.getName());
		member.setEmail(request.getEmail());
		member.setPhone(request.getPhone());
		member.setStatus(Member.Status.valueOf(request.getStatus().toUpperCase()));
		return member;
	}

	public static MemberResponse memberToResponse(Member member) {
		MemberResponse response = new MemberResponse();
		response.setMemberId(member.getMemberId());
		response.setName(member.getName());
		response.setEmail(member.getEmail());
		response.setPhone(member.getPhone());
		response.setJoinDate(member.getJoinDate());
		response.setBalance(member.getBalance());
		response.setStatus(member.getStatus().name());
		return response;
	}

	public static List<MemberResponse> memberListToResponseList(List<Member> members) {
		List<MemberResponse> list = new ArrayList<>();
		for (Member m : members) {
			list.add(memberToResponse(m));
		}
		return list;
	}

	// ----------------- GAME -----------------
	public static Game gameRequestToEntity(GameRequest request) {
		Game game = new Game();
		game.setGameName(request.getGameName());
		game.setCostPerHour(request.getCostPerHour());
		game.setStatus(Game.Status.valueOf(request.getStatus()));
		return game;
	}

	public static GameResponse gameToResponse(Game game) {
		GameResponse response = new GameResponse();
		response.setGameId(game.getGameId());
		response.setGameName(game.getGameName());
		response.setCostPerHour(game.getCostPerHour());
		response.setStatus(game.getStatus().name());
		return response;
	}

	public static List<GameResponse> gameListToResponseList(List<Game> games) {
		List<GameResponse> list = new ArrayList<>();
		for (Game g : games) {
			list.add(gameToResponse(g));
		}
		return list;
	}

	// ----------------- RECHARGE -----------------
	public static Recharge rechargeRequestToEntity(RechargeRequestDTO request, Member member) {
		Recharge recharge = new Recharge();
		recharge.setMember(member);
		recharge.setAmount(request.getAmount());
		recharge.setRechargeDate(LocalDateTime.now());
		return recharge;
	}

	public static RechargeResponse rechargeToResponse(Recharge recharge) {
		RechargeResponse response = new RechargeResponse();
		response.setRechargeId(recharge.getRechargeId());
		response.setMemberId(recharge.getMember().getMemberId());
		response.setAmount(recharge.getAmount());
		response.setRechargeDate(recharge.getRechargeDate());
		return response;
	}

	public static List<RechargeResponse> rechargeListToResponseList(List<Recharge> recharges) {
		List<RechargeResponse> list = new ArrayList<>();
		for (Recharge r : recharges) {
			list.add(rechargeToResponse(r));
		}
		return list;
	}

	// ----------------- TRANSACTION -----------------
	public static Transaction transactionRequestToEntity(TransactionRequestDTO request, Member member, Game game) {
		Transaction transaction = new Transaction();
		transaction.setMember(member);
		transaction.setGame(game);
		transaction.setPlayTimeHrs(request.getPlayTimeHrs());
		BigDecimal cost = game.getCostPerHour().multiply(request.getPlayTimeHrs());
		transaction.setCost(cost);
		transaction.setTransactionDate(LocalDateTime.now());
		return transaction;
	}

	public static TransactionResponse transactionToResponse(Transaction transaction) {
		TransactionResponse response = new TransactionResponse();
		response.setTransactionId(transaction.getTransactionId());
		response.setMemberId(transaction.getMember().getMemberId());
		response.setGameId(transaction.getGame().getGameId());
		response.setPlayTimeHrs(transaction.getPlayTimeHrs());
		response.setCost(transaction.getCost());
		response.setTransactionDate(transaction.getTransactionDate());
		return response;
	}

	public static List<TransactionResponse> transactionListToResponseList(List<Transaction> transactions) {
		List<TransactionResponse> list = new ArrayList<>();
		for (Transaction t : transactions) {
			list.add(transactionToResponse(t));
		}
		return list;
	}

	// ----------------- DAILY COLLECTION -----------------
	public static DailyCollection dailyCollectionRequestToEntity(DailyCollectionResponse request) {
		DailyCollection collection = new DailyCollection();
		collection.setCollectionDate(request.getCollectionDate());
		collection.setTotalRecharges(request.getTotalRecharges());
		collection.setTotalSpent(request.getTotalSpent());
		collection.setNetCollection(request.getTotalRecharges().subtract(request.getTotalSpent()));
		return collection;
	}

	public static DailyCollectionResponse dailyCollectionToResponse(DailyCollection collection) {
		DailyCollectionResponse response = new DailyCollectionResponse();
		response.setCollectionId(collection.getCollectionId());
		response.setCollectionDate(collection.getCollectionDate());
		response.setTotalRecharges(collection.getTotalRecharges());
		response.setTotalSpent(collection.getTotalSpent());
		response.setNetCollection(collection.getNetCollection());
		return response;
	}

	public static List<DailyCollectionResponse> dailyCollectionListToResponseList(List<DailyCollection> collections) {
		List<DailyCollectionResponse> list = new ArrayList<>();
		for (DailyCollection c : collections) {
			list.add(dailyCollectionToResponse(c));
		}
		return list;
	}
}
