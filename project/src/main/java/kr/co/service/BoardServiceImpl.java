package kr.co.service;

import java.util.ArrayList;

import kr.co.domain.AttachFileDTO;
import kr.co.domain.BoardDTO;
import kr.co.domain.Criteria;
import kr.co.domain.PrevNextDTO;
import kr.co.mapper.AttachMapper;
import kr.co.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService{ 
	@Autowired
	private BoardMapper bmapper;
	@Autowired
	private AttachMapper amapper;
	
	//�Խ��� �۾��� ����� �� ����
	@Transactional
	public void write(BoardDTO board) {
		
		
		bmapper.insertSelectKey(board);
		
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach->{
				attach.setBno(board.getBno());
				amapper.insert(attach);
			});
		}else {
			System.out.println("������ �����ϼ���.");
		}
			
	}
	
	//�Խ��� ��ϸ���Ʈ ����� �� ����
	public ArrayList<BoardDTO> noticeBoard(Criteria cri) {
		return bmapper.noticeBoard(cri);
	}

	@Transactional
	//�Խ��� ��ϸ���Ʈ���� ������ Ŭ������ �� ������ ������ �������� ����� ���� ����
	public BoardDTO detail(BoardDTO board) {
		//board���̺� ��ȸ�� �Ӽ��� +1
		bmapper.cntupdate(board);
		return bmapper.detail(board);
	}
	
	public PrevNextDTO detail2(PrevNextDTO pnBoard) {

		return bmapper.detail2(pnBoard);
	}
	
	//�Խ��� �ۼ��� ����� �� ����
	@Transactional
	public void modify(BoardDTO board) {
		bmapper.modify(board);
		
/*		board.getAttachList().forEach(attach->{
			// ���࿡ ���Ͼ��ε� ������ ������
			System.out.println(board.getAttachList());
			if(board.getAttachList() != null) {
				
				System.out.println(board.getAttachList());
				attach.setBno(board.getBno());
				amapper.modify(attach);
			}
			
			
		});*/
	}
	
	//�Խ��� �ۻ��� ����� �� ����
	public void remove(BoardDTO board) {
		bmapper.remove(board);
	}
	
	//�Ի��� ����¡�� ���� �����ͰǼ�
	public int getTotalCount(Criteria cri) {
		return bmapper.getTotalCount(cri);
	}
	
	//�Խ��� ���������� ���Ͼ��ε�� �̹��� ����ϴ� ���� ����
	public ArrayList<AttachFileDTO> fileList(int bno){
		return amapper.fileList(bno);
	}
	
	public ArrayList<AttachFileDTO> fileListPost(int bno){
		return amapper.fileListPost(bno);
	}

	
	public void delete(AttachFileDTO aboard) {
		 amapper.delete(aboard);
	}

	
	public boolean fileDelete(AttachFileDTO attach) {
		return amapper.fileDelete(attach);
	}

		
	
}
