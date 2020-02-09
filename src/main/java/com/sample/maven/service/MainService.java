package com.sample.maven.service;

import java.util.HashMap;
import java.util.List;

import com.sample.maven.dto.empDto;

public interface MainService {

	public List<empDto> selectMainList(HashMap<String, Object> paramMap);

}
