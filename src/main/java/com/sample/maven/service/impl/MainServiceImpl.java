package com.sample.maven.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.maven.dao.MainDao;
import com.sample.maven.dto.empDto;
import com.sample.maven.service.MainService;

@Transactional
@Service("mainService")
public class MainServiceImpl implements MainService {

	private static final Logger log = LoggerFactory.getLogger(MainServiceImpl.class);

	@Resource(name = "mainDao")
	private MainDao mainDao;

	public List<empDto> selectMainList(HashMap<String, Object> paramMap) {
		return mainDao.selectMainList(paramMap);
	}

}
