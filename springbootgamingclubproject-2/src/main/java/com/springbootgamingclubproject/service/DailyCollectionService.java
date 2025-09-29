package com.springbootgamingclubproject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgamingclubproject.dto.response.DailyCollectionResponse;
import com.springbootgamingclubproject.entity.DailyCollection;
import com.springbootgamingclubproject.exception.ResourceNotFoundException;
import com.springbootgamingclubproject.repository.DailyCollectionRepository;
import com.springbootgamingclubproject.util.MapperUtil;

@Service
public class DailyCollectionService {
	@Autowired
	private  DailyCollectionRepository dailyCollectionRepository;


    public List<DailyCollectionResponse> getAllDailyCollections() {
        List<DailyCollection> collections = dailyCollectionRepository.findAll();
        return MapperUtil.dailyCollectionListToResponseList(collections);
    }

    public DailyCollectionResponse getDailyCollectionByDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        DailyCollection collection = dailyCollectionRepository.findByCollectionDate(localDate)
                .orElseThrow(() -> new ResourceNotFoundException("No collection found for date " + date));
        return MapperUtil.dailyCollectionToResponse(collection);
    }
}
