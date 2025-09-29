package com.springbootgamingclubproject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springbootgamingclubproject.dto.request.MemberRequest;
import com.springbootgamingclubproject.dto.response.MemberResponse;
import com.springbootgamingclubproject.entity.Member;
import com.springbootgamingclubproject.exception.ResourceNotFoundException;
import com.springbootgamingclubproject.repository.MemberRepository;
import com.springbootgamingclubproject.util.MapperUtil;
@Service
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberResponse createMember(MemberRequest request) {
		Member member = MapperUtil.memberRequestToEntity(request);
		member.setJoinDate(LocalDate.now());
		member.setBalance(java.math.BigDecimal.ZERO);
		member = memberRepository.save(member);
		return MapperUtil.memberToResponse(member);
	}

	public List<MemberResponse> getAllMembers() {
		List<Member> members = memberRepository.findAll();
		return MapperUtil.memberListToResponseList(members);
	}

	public MemberResponse getMemberById(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberId));
		return MapperUtil.memberToResponse(member);
	}

	public MemberResponse updateMember(Long memberId, MemberRequest request) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberId));
		member.setName(request.getName());
		member.setEmail(request.getEmail());
		member.setPhone(request.getPhone());
		member.setStatus(Member.Status.valueOf(request.getStatus().toUpperCase()));
		member = memberRepository.save(member);
		return MapperUtil.memberToResponse(member);
	}

	public void deleteMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberId));
		memberRepository.delete(member);
	}
}
