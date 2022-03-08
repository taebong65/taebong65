package kr.co.service;

import java.util.ArrayList;

import kr.co.domain.AttachFileDTO;
import kr.co.domain.BoardDTO;
import kr.co.domain.Criteria;
import kr.co.domain.PrevNextDTO;

public interface BoardService { 
	//�Խ��� �۾���
	public void write(BoardDTO board);
	
	//�Խ��� ��ϸ���Ʈ
	public ArrayList<BoardDTO> noticeBoard(Criteria cri);

	//�Խ��� ��ϸ���Ʈ���� ������ Ŭ������ �� ������ ������ ��������
	public BoardDTO detail(BoardDTO board);
	
	public PrevNextDTO detail2(PrevNextDTO pnBoard);
	
	//�Խ��� ����������
	public void modify(BoardDTO board);
	
	//�Խ��� ����������
	public void remove(BoardDTO board);
	
	//�Ի��� ����¡�� ���� �����ͰǼ�
	public int getTotalCount(Criteria cri);
	
	//�Խ��� ���������� ���Ͼ��ε�� �̹��� ���
	public ArrayList<AttachFileDTO> fileList(int bno);
	
	public ArrayList<AttachFileDTO> fileListPost(int bno);
	

	public void delete(AttachFileDTO aboard);

	
	public boolean fileDelete(AttachFileDTO attach);


	
}
