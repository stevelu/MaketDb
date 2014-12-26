package com.db.model;

import java.util.List;


public class category {
	
	//"Setcode": 47,"Code": "IF1412","Date": 0,"Startxh": 0,"TotalNum": 6100,"ListHead": ["Second","Now","NowVol","VolInStockDiff","InOutFlag","HKTTFlag","HKInOutFlag"],"List": 

		private String SBTSize;
		private String [] ListHead;
		private List<String[]> List;
		
		public String[] getListHead() {
			return ListHead;
		}
		public String getSBTSize() {
			return SBTSize;
		}
		public void setSBTSize(String sBTSize) {
			SBTSize = sBTSize;
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
