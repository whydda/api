package com.example.api.service.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("commonMapper")
public interface CommonMapper {
	List<Map<String, Object>> selectTest();
}
