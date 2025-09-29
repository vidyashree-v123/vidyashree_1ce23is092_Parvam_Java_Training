package com.springbootgamingclubproject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootgamingclubproject.api.ApiResponse;
import com.springbootgamingclubproject.dto.request.MemberRequest;
import com.springbootgamingclubproject.dto.response.MemberResponse;
import com.springbootgamingclubproject.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<MemberResponse>> createMember(@RequestBody MemberRequest request) {
		MemberResponse response = memberService.createMember(request);
		return ResponseEntity.ok(ApiResponse.success("Member created successfully", response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<MemberResponse>>> getAllMembers() {
		List<MemberResponse> response = memberService.getAllMembers();
		return ResponseEntity.ok(ApiResponse.success("Members fetched successfully", response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<MemberResponse>> getMemberById(@PathVariable Long id) {
		MemberResponse response = memberService.getMemberById(id);
		return ResponseEntity.ok(ApiResponse.success("Member fetched successfully", response));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<MemberResponse>> updateMember(@PathVariable Long id,
			@RequestBody MemberRequest request) {
		MemberResponse response = memberService.updateMember(id, request);
		return ResponseEntity.ok(ApiResponse.success("Member updated successfully", response));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteMember(@PathVariable Long id) {
		memberService.deleteMember(id);
		return ResponseEntity.ok(ApiResponse.success("Member deleted successfully", null));
	}
}
