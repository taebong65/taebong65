package kr.co.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.domain.SampleDTO;
import kr.co.domain.SampleDTOList;
import kr.co.domain.TodoDTO;

@Controller
@RequestMapping("/sample")
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
		@RequestMapping("abc")
		public void basic() { //리턴타입이 없는 basic이름의 메서드
			logger.info("basic........");
			
			
			//return이 없으면 메서드 바로 위에 있는 @RequestMapping에 문자열이 리턴되어.jsp파일 이름이 되고
			// return이 있으면 return뒤에 있는 문자열이 리턴되어 .jsp파일 이름이 된다. 
			
			
			
		}
		
		//@RequestMapping 의 속성으로 get하는 방법
		@RequestMapping(value="/basic", method= {RequestMethod.GET,RequestMethod.POST})
		public void basicGet1() {
			
			logger.info("basic get........");
		}
		
		//@GetMappging 으로 get하는 방법
		
		@GetMapping("/basicOnlyGet")
		public void basicGet2() {
			logger.info("basic get only get.......");
		}
		// SampleDTO를 매개변수로 선언하여 , 자동으로 setter메소드 동작하여 데이터 수집
		@GetMapping("/sample01")
		
		//리턴타입 메소드명(매개변수)
		
		public String ex(SampleDTO dto) {
			
			logger.info(""+dto);
			return "sample01";
		}
		
		// name변수와 age변수로 선언하여 데이터 수집 
		@GetMapping("/sample02")
		
		//리턴타입 메소드명(매개변수)
		
		public String ex(@RequestParam("name")String name,@RequestParam("age") String age) {
			
			logger.info("name="+name);
			logger.info("age="+age);
			return "sample01";
		
		
		}
		//매개변수로 배열로 입력받아서 처리.
		@GetMapping("/ex02List")
		public String ex02List(@RequestParam("ids") ArrayList<String>ids ) {
			logger.info(""+ids);
			
			
			return "ex02List";
		}
		@GetMapping("/ex02Array")
		public String ex02List(@RequestParam("ids") String[]ids ) {
			logger.info(""+Arrays.toString(ids));
			
			 
			return "ex02List";
		}
		
		// 매개변수로 객체 리스트로 입력받아서 처리 
		@GetMapping("/ex02bean")
		public String ex02Bean(SampleDTOList list) {
			logger.info("list dtos : "+list);
			
			return "ex0Bean";
		}
		
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			binder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(dateFormat, false));
		}
		
		
		@GetMapping("/ex04")
		public String ex04(TodoDTO todo) {
			logger.info("list dtos :" +todo);
			
			return "ex04";
			
		}
		// @RequestMapping의 문자열이 sample을 1차적으로 찾고, 
	   // @RequestMapping의 문자열이 ex06을 2차적으로 찾기 때문에  localhost:8080/sample/ex06을 실행하면서 무한 실행이 된다
		// return "sample/ex06";
		
		//return에 redirect이 있으면 redirect방식이 되고 
		
		//return에 rediect이 없으면 forward방식이 되기 때문에 /web-inf/views/sample/ex06.jsp를 붙여서 화면 이동
		
		@GetMapping("/ex05")
		public String ex05(SampleDTO dto, @ModelAttribute("page") int page) {
			logger.info("page:"+page);
			return "/sample/ex05";
		}
		
		
		
		@GetMapping("/ex08")
		public @ResponseBody SampleDTO ex08() {
			
			logger.info("/ex08........");
			SampleDTO dto = new SampleDTO();
			dto.setAge(10);
			dto.setName("홍길동");
			return dto;
		}
		
		@GetMapping("/ex09")
public ResponseEntity<String> ex09() {
			
			logger.info("/ex09........");
			
			String msg="{\"name\" : \"홍길동\"}";
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", "application/json;charset=utf-8");
			
			return new ResponseEntity<>(msg,header, HttpStatus.OK);
		}
		
		// exUpload.jsp를 실행하기 위한 용도 (화면을 실행)
		@GetMapping("/exUpload")
		public void exUpload() {
			logger.info("/exUpload.....");
			
		}
		// 파일 upload를 하기위한 용도 (서버)
		
		@PostMapping("/exUploadPost")
		public void exUploadPost(ArrayList<MultipartFile> files) {
			
			files.forEach(file-> {
				logger.info("=============");
				logger.info("name : "+file.getOriginalFilename());
				logger.info("size :"+file.getSize());
			});
		}
}


//addAttribute는 매개변수가 두개면 setAttribute 역할 하나면 getAttribute역할 
