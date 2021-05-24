package org.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.sample.domain.SampleVO;
import org.sample.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController // RestController 명시
@RequestMapping("/sample2")
@Log4j
public class SampleController2 {
	/* [ @RestController에서의 파라미터 처리 ] */
	
	// 일반 문자열 반환
	// 기존의 Controller에서는 문자열 반환 시 JSP 파일 이름으로 처리
	// RestController의 경우 순수한 데이터로 처리
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);

		return "안녕하세요";
	}

	// 객체 반환 -> JSON 또는 XML을 이용
	@GetMapping(value = "/getSample", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {
		return new SampleVO(1, "크리스티아누", "호날두");
	}

	// produces 속성 생략 가능
	@GetMapping(value = "/getSample2")
	public SampleVO getSample2() {
		return new SampleVO(2, "세르히오", "라모스");
	}
	
	// 컬렉션 타입 객체 반환
	@GetMapping(value = "/getList")
	public List<SampleVO> getList(){
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i +"Frist", i + "Last"))
				.collect(Collectors.toList());
	}
	
	// 맵 타입의 객체 반환
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(3, "루카", "모드리치"));
		return map;
	}
	
	
	// ResponseEntity 타입
	// 데이터를 요청한 쪽에서 HTTP 헤더 상태 메시지와 함께 정상적인 데이터인지 비정상적인 데이터인지를 구분
	@GetMapping(value = "/check", params = { "height", "weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		else result = ResponseEntity.status(HttpStatus.OK).body(vo);
		
		return result;
	}
	
	/* [ @RestController에서의 파라미터 처리 ] */
	
	// @PathVariable을 이용하여 URL 경로의 일부를 파라미터로 사용
	@GetMapping(value = "product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		return new String[] { "category: " + cat, "productId: " + pid };
	}
	
	// @RequestBody는 전달된 요청(request)의 내용(body)을 이용해서 해당 파라미터의 타입으로 변환을 요구
	// 대부분 JSON 데이터를 서버에 보내 원하는 타입의 객체로 변환하는 용도로 사용
	// REST 방식의 데이터를 전송하는 툴을 이용하여 테스트
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("Ticket: " + ticket);
		
		return ticket;
	}
	
}
