package kr.co.mapper;

import java.util.ArrayList;

import kr.co.domain.AttachFileDTO;

public interface AttachMapper {
	//�Խ��� �۾��� �� �� ���ϰ� ���õǾ� �ִ� attach���̺��� insert
	public void insert(AttachFileDTO board);
	
	
	public void delete(AttachFileDTO aboard);
	/*public void ainsert(AttachFileDTO aboard);*/
	
	//�Խ��� ���������� ���ε�� �̹����� �Ѹ������� ������list
	public ArrayList<AttachFileDTO> fileList(int bno);
	public void modify(AttachFileDTO board);
	
	public ArrayList<AttachFileDTO> fileListPost(int bno);
	
	public boolean fileDelete(AttachFileDTO attach);

}
