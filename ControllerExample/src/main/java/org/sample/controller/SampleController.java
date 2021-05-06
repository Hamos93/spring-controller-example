package org.sample.controller;

import org.sample.domain.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@RequestMapping("/home")
	public void home() {
		log.info("[ SampleController home 메서드 ]");
	}

	// @RequestMapping method 속성 지정
	@RequestMapping(value = "/get1", method = { RequestMethod.GET, RequestMethod.POST })
	public void get1() {
		log.info("[ SampleController get 메서드 ]");
	}
	
	// 스프링 4.3 버전부터 @GetMapping, @PostMapping 추가
	@GetMapping("/get2")
	public void get2() {
		log.info("[ SampleController get2 메서드 ]");
	}
	
	// SampleDTO를 파라미터로 사용 시 자동으로 setter 메서드가 동작하면서 파라미터를 수집
	@GetMapping("/get3")
	public void get3(SampleDTO dto) {
		log.info("[ SampleController get3 메서드 ]");
		log.info(dto);
	}
	
	// @RequestParam을 이용한 값 수집
	@GetMapping("/check")
	public void check(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("[ SampleController check 메서드 ]");
		log.info("name: " + name + ", age: " + age);
	}
	
	// @ModelAttribute를 사용하여 데이터 전달
	@GetMapping("/test")
	public String test(SampleDTO dto, @ModelAttribute("height") int height) {
		log.info("[ SampleController test 메서드 ]");
		log.info("dto: " + dto + ", height: " + height);
		
		return "/sample/test";
	}
	
	/* 컨트롤러 리턴 타입 */
	// 1. void -> 해당 URL 경로를 그대로 jsp 파일의 이름으로 사용
	@GetMapping("/hello")
	public void hello() {
		log.info("[ SampleController hello 메서드 ]");
	}

	// 2. String -> jsp 파일의 이름
	@GetMapping("/hello2")
	public String hello2() {
		log.info("[ SampleController hello2 메서드 ]");
		return "/sample/hello2";
	}
	
	// 3. 객체 타입 -> 주로 JSON 데이터를 만들어내는 용도로 사용
	// jackson-databind 라이브러리 추가
	@GetMapping("/hello3")
	public @ResponseBody SampleDTO hello3() {
		log.info("[ SampleController hello3 메서드 ]");

		SampleDTO dto = new SampleDTO();
		dto.setName("호날두");
		dto.setAge(35);
		
		return dto;
	}
}
