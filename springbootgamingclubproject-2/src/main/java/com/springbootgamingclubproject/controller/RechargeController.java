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
import com.springbootgamingclubproject.dto.request.RechargeRequestDTO;
import com.springbootgamingclubproject.dto.response.RechargeResponse;
import com.springbootgamingclubproject.service.RechargeService;

@RestController
@RequestMapping("/api/recharges")
public class RechargeController {
	private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RechargeResponse>> createRecharge(@RequestBody RechargeRequestDTO request) {
        RechargeResponse response = rechargeService.createRecharge(request);
        return ResponseEntity.ok(ApiResponse.success("Recharge successful", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RechargeResponse>>> getAllRecharges() {
        List<RechargeResponse> response = rechargeService.getAllRecharges();
        return ResponseEntity.ok(ApiResponse.success("Recharges fetched successfully", response));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<List<RechargeResponse>>> getRechargesByMember(@PathVariable Long memberId) {
        List<RechargeResponse> response = rechargeService.getRechargesByMember(memberId);
        return ResponseEntity.ok(ApiResponse.success("Member recharges fetched successfully", response));
    }
}
