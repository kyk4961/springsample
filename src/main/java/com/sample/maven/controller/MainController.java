package com.sample.maven.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.maven.dto.empDto;
import com.sample.maven.service.MainService;

@Controller
public class MainController {

	@Resource(name = "mainService")
	private MainService mainService;

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/json.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject main(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		JSONObject jsonObj = new JSONObject();

		return jsonObj;
	}

	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		List<empDto> rsList = mainService.selectMainList(paramMap);
		System.out.println(rsList);
		model.addAttribute("rsList", rsList);
		model.addAttribute("message", "Hello Spring");

		return "index";
	}

}
