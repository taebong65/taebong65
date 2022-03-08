package kr.co.controller;


import java.util.ArrayList;

import kr.co.domain.AttachFileDTO;
import kr.co.domain.BoardDTO;
import kr.co.domain.Criteria;
import kr.co.domain.PageDTO;
import kr.co.domain.PrevNextDTO;
import kr.co.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("page")
public class BoardController {
	@Autowired
	private BoardService service;
	
	//�۾��� ȭ������
	@GetMapping("write")
	public void write() {}

	
	@PostMapping("write")
	public String writePost(BoardDTO board) {
		System.out.println(board);
		service.write(board);
		
		return "redirect:/page/detail?bno="+board.getBno();
	}
	
	
	
	@GetMapping("noticeBoard")
	public void noticeBoard(Model model, Criteria cri) {
		
		
		model.addAttribute("list", service.noticeBoard(cri));

		
		int total = service.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotalCount(cri)));
	}

	
	//�Խ��� ��� ����Ʈ���� ������ Ŭ���ϸ�
	@GetMapping("detail")
	public void detail(BoardDTO board, Model model, PrevNextDTO pnBoard) {
		model.addAttribute("detail", service.detail(board));		
		model.addAttribute("detail2", service.detail2(pnBoard));
		
	}
	
	//�Խ��� ������������ �̹����� ����ϱ� ���� select�� ����� javascript��
	@GetMapping(value="/fileList/{bno}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)//ajax
	public ResponseEntity<ArrayList<AttachFileDTO>> fileList(@PathVariable int bno){

		System.out.println(service.fileList(bno));
		
		return new ResponseEntity<>(service.fileList(bno),HttpStatus.OK);
		
	}
	
	
	//�� ���� ȭ������
	@GetMapping("modify")
	public void modify(BoardDTO board, Model model) {

		model.addAttribute("detail", service.detail(board));
	}
	
	//�ۼ��� ��ư�� Ŭ���ϸ�
	@GetMapping("fileDelete")
	public String fileDelete(AttachFileDTO attach, BoardDTO board) {
		
		service.fileDelete(attach);
		System.out.println("boolean = " + service.fileDelete(attach));
		
		/*rttr.addAttribute("bno", board.getBno());*/
		
		return "redirect:/page/modify?bno="+board.getBno();
	}
	
	@PostMapping("modify")
	public String modifyPost(BoardDTO board) {
		
		service.modify(board);
		
		return "redirect:/page/detail?bno="+board.getBno();
		
	}
	
	//�ۻ��� ��ư�� Ŭ���ϸ�
	@GetMapping("remove")
	public String remove(BoardDTO board) {
		//delete
		service.remove(board);
		return "redirect:/page/noticeBoard";
	}


	
	
}
