package com.cf.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {
	/**
	 * 返回Thymeleaf模板.
	 */
	@RequestMapping("/index.do")
	public String helloHtml(){
		return "file";
	}

}
