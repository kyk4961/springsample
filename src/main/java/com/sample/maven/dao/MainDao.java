package com.sample.maven.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sample.maven.dto.empDto;

@Repository("mainDao")
public class MainDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	private static final Logger log = LoggerFactory.getLogger(MainDao.class);

	public List<empDto> selectMainList(HashMap<String, Object> paramMap) {
		return sqlSession.selectList("MainMapper.selectMainList", paramMap);
	}

}
