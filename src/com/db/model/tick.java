package com.db.model;

import java.util.List;


public class tick {
	
	//"Setcode": 47,"Code": "IF1412","Date": 0,"Startxh": 0,"TotalNum": 6100,"ListHead": ["Second","Now","NowVol","VolInStockDiff","InOutFlag","HKTTFlag","HKInOutFlag"],"List": 

		private String Setcode;
		private String Code;
		private String Date;
		private String Startxh;
		private String TotalNum;
		private String [] ListHead;
		private List<String[]> List;
		public String getSetcode() {
			return Setcode;
		}
		public void setSetcode(String setcode) {
			Setcode = setcode;
		}
		public String getCode() {
			return Code;
		}
		public void setCode(String code) {
			Code = code;
		}
		public String getDate() {
			return Date;
		}
		public void setDate(String date) {
			Date = date;
		}
		public String getStartxh() {
			return Startxh;
		}
		public void setStartxh(String startxh) {
			Startxh = startxh;
		}
		public String getTotalNum() {
			return TotalNum;
		}
		public void setTotalNum(String totalNum) {
			TotalNum = totalNum;
		}
		public String[] getListHead() {
			return ListHead;
		}
		public void setListHead(String[] listHead) {
			ListHead = listHead;
		}
		public List<String[]> getList() {
			return List;
		}
		public void setList(List<String[]> list) {
			List = list;
		}
		
		

}
