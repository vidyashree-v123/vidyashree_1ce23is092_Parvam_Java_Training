package com.springbootgamingclubproject.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgamingclubproject.dto.request.RechargeRequestDTO;
import com.springbootgamingclubproject.dto.response.RechargeResponse;
import com.springbootgamingclubproject.entity.DailyCollection;
import com.springbootgamingclubproject.entity.Member;
import com.springbootgamingclubproject.entity.Recharge;
import com.springbootgamingclubproject.exception.ResourceNotFoundException;
import com.springbootgamingclubproject.repository.DailyCollectionRepository;
import com.springbootgamingclubproject.repository.MemberRepository;
import com.springbootgamingclubproject.repository.RechargeRepository;
import com.springbootgamingclubproject.util.MapperUtil;

@Service
public class RechargeService {
	@Autowired
	private RechargeRepository rechargeRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private DailyCollectionRepository dailyCollectionRepository;

	public RechargeResponse createRecharge(RechargeRequestDTO request) {
		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + request.getMemberId()));

		Recharge recharge = MapperUtil.rechargeRequestToEntity(request, member);
		member.setBalance(member.getBalance().add(request.getAmount())); // Update wallet
		memberRepository.save(member);

		recharge = rechargeRepository.save(recharge);
		// --- Update DailyCollection ---
		LocalDate today = LocalDate.now();
		DailyCollection dailyCollection = dailyCollectionRepository.findByCollectionDate(today)
				.orElse(new DailyCollection(today, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

		dailyCollection.setTotalRecharges(dailyCollection.getTotalRecharges().add(request.getAmount()));
		dailyCollection.setNetCollection(dailyCollection.getTotalRecharges().subtract(dailyCollection.getTotalSpent()));

		dailyCollectionRepository.save(dailyCollection);
		// --- End DailyCollection update ---
		return MapperUtil.rechargeToResponse(recharge);
	}

	public List<RechargeResponse> getAllRecharges() {
		return MapperUtil.rechargeListToResponseList(rechargeRepository.findAll());
	}

	public List<RechargeResponse> getRechargesByMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberId));
		return MapperUtil.rechargeListToResponseList(rechargeRepository.findByMember(member));
	}
}
