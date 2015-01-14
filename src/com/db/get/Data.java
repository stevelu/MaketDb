package com.db.get;

import com.db.tools.URLConnectionHelper;


public class Data {

	protected String Url = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.";

	/**
	 * get the list of all stock index futures 
	 * @return      the info list by json 
	 */
	public String getList() {
		//String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.MultiHQ";
		String apiUrl=Url+"MultiHQ";
		String para = "{\"WantCol\":[\"NOW\",\"VOL\",\"VolInStock\",\"PreVolInStock\",\"ClearPrice\",\"LB\",\"OPEN\",\"MAX\",\"MIN\",\"CLOSE\",\"XSFLAG\"],\"SetDomain\":47,\"SubCode\":\"\",\"ColType\":14,\"Startxh\":0,\"WantNum\":20,\"SortType\":0,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		String str = URLConnectionHelper.sendPost(apiUrl, para);
		return str;

	}

	/**
	 * get tick info
	 * @param AttCode  code name
	 * @param page     every 1000 record a page
	 * @return         1000 tick info by json
	 */
	public String getTick(String AttCode, int page) {

		int pageNum = page * 1000;
		long datelong = 0;
		//String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.Tick";
		String apiUrl=Url+"Tick";
		String para = "{\"Code\":\""
				+ AttCode
				+ "\",\"Setcode\":47,\"Date\":"
				+ datelong
				+ ",\"Startxh\":"
				+ pageNum
				+ ",\"WantNum\":1000,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";

		String str = URLConnectionHelper.sendPost(apiUrl, para);
		return str;

	}

	/**
	 * get day line info
	 * @param AttCode		
	 * @return
	 */
	public String getDay(String AttCode) {
		// get data for day line
		int pageNum = 0;
		long Period = 4;
		//String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.FXT";
		String apiUrl=Url+"FXT";
		String para = "{\"Code\":\""
				+ AttCode
				+ "\",\"Setcode\":47,\"Period\":"
				+ Period
				+ ",\"Startxh\":"
				+ pageNum
				+ ",\"WantNum\":240,\"TQFlag\":0,\"HasAttachInfo\":0,\"ExHQFlag\":1,\"CharSet\":\"\"}";

		String str = URLConnectionHelper.sendPost(apiUrl, para);
		return str;
	}

}
