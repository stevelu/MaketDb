package com.db.get;

public class getData {

	public String get(String AttCode,int page) {
		
		int pageNum = 0;
		if (page==0)
			page = 1;
		pageNum = page - 1;
		// page

		if (pageNum < 0)
			pageNum = 0;
		pageNum = pageNum * 1000;

		// String apiUrl = "http://119.97.185.7:7615/TQLEX?Entry=HQServ.Tick";
		// String
		// para="{\"Code\":\"IF1409\",\"Setcode\":47,\"Date\":0704,\"Startxh\":-1,\"WantNum\":10,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.Tick";
		// String
		// para="{\"Code\":\"IF1412\",\"Setcode\":47,\"Date\":0,\"Startxh\":0,\"WantNum\":1000,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		String para = "{\"Code\":\""
				+ AttCode
				+ "\",\"Setcode\":47,\"Date\":0,\"Startxh\":"
				+ pageNum
				+ ",\"WantNum\":1000,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";

		URLConnectionHelper helper = new URLConnectionHelper();
		String str = helper.sendPost(apiUrl, para);
		return str;

	}
}
