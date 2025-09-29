package com.springbootgamingclubproject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootgamingclubproject.api.ApiResponse;
import com.springbootgamingclubproject.dto.response.DailyCollectionResponse;
import com.springbootgamingclubproject.service.DailyCollectionService;

@RestController
@RequestMapping("/api/daily-collections")
public class DailyCollectionController {
	 private final DailyCollectionService dailyCollectionService;

	    public DailyCollectionController(DailyCollectionService dailyCollectionService) {
	        this.dailyCollectionService = dailyCollectionService;
	    }

	    @GetMapping
	    public ResponseEntity<ApiResponse <List<DailyCollectionResponse>>> getAllDailyCollections() {
	        List<DailyCollectionResponse> response= dailyCollectionService.getAllDailyCollections();
	        return ResponseEntity.ok(ApiResponse.success("Daily collections fetched successfully", response));
	    }

	    @GetMapping("/{date}")
	    public ResponseEntity<ApiResponse<DailyCollectionResponse>> getDailyCollectionByDate(@PathVariable String date) {
	        DailyCollectionResponse response = dailyCollectionService.getDailyCollectionByDate(date);
	        return ResponseEntity.ok(ApiResponse.success("Daily collection fetched successfully", response));
	    }
}
