package kr.co.domain;

import java.util.ArrayList;
import java.util.List;

public class SampleDTOList {

		private List<SampleDTO> list;
		public List<SampleDTO> getList() {
			return list;
		}
		public void setList(List<SampleDTO> list) {
			this.list = list;
		}
		public SampleDTOList() {
			list=new ArrayList<>();
		}
}
