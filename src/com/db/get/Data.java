package com.db.get;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.db.model.category;
import com.db.mongo.Mongo;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Data {
	private static Logger log = LogManager.getLogger(Data.class.getName());

	public String getList() {
		
		String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.MultiHQ";
		String para = "{\"WantCol\":[\"NOW\",\"VOL\",\"VolInStock\",\"PreVolInStock\",\"ClearPrice\",\"LB\",\"OPEN\",\"MAX\",\"MIN\",\"CLOSE\",\"XSFLAG\"],\"SetDomain\":47,\"SubCode\":\"\",\"ColType\":14,\"Startxh\":0,\"WantNum\":20,\"SortType\":0,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		URLConnectionHelper helper = new URLConnectionHelper();
		String str = helper.sendPost(apiUrl, para);
		return str;

	}

	public List<String> FilterList(String str) {
		List<String> list = new ArrayList<String>();
		//正值表达式查找IF1502
		String pattern = "IF\\d\\d\\d\\d";
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);
		// 现在创建 matcher 对象
		Matcher m = r.matcher(str);
		while (m.find()) {
			String A = m.group();
			list.add(A);
		}

		return list;
	}

	public String getTick(String AttCode, int page) {

		int pageNum = page * 1000;

		long datelong = 0;

		// String apiUrl = "http://119.97.185.7:7615/TQLEX?Entry=HQServ.Tick";
		// String  para="{\"Code\":\"IF1409\",\"Setcode\":47,\"Date\":0704,\"Startxh\":-1,\"WantNum\":10,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.Tick";
    	String para = "{\"Code\":\""
				+ AttCode
				+ "\",\"Setcode\":47,\"Date\":"
				+ datelong
				+ ",\"Startxh\":"
				+ pageNum
				+ ",\"WantNum\":1000,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";

		URLConnectionHelper helper = new URLConnectionHelper();
		String str = helper.sendPost(apiUrl, para);
		return str;

	}

	public String getDay(String AttCode) {
		// get data for day line
		int pageNum = 0;
		long Period = 4;
		// String apiUrl = "http://119.97.185.7:7615/TQLEX?Entry=HQServ.Tick";
		// String para="{"Code":"IF1501","Setcode":47,"Period":4,"Startxh":0,"WantNum":240,"TQFlag":0,"HasAttachInfo":0,"ExHQFlag":1,"CharSet":""}";
		String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.FXT";
		String para = "{\"Code\":\""
				+ AttCode
				+ "\",\"Setcode\":47,\"Period\":"
				+ Period
				+ ",\"Startxh\":"
				+ pageNum
				+ ",\"WantNum\":240,\"TQFlag\":0,\"HasAttachInfo\":0,\"ExHQFlag\":1,\"CharSet\":\"\"}";

		URLConnectionHelper helper = new URLConnectionHelper();
		String str = helper.sendPost(apiUrl, para);
		return str;
	}

	public int getTotalNum(String json) {

		DBObject dbObject = (DBObject) JSON.parse(json);
		Object num = dbObject.get("TotalNum");
		int nu = Integer.parseInt(num.toString());

		return nu;

	}

	public Boolean process(int page, String json, Mongo mon, DBCollection coll) {
		
		DBObject dbObject = (DBObject) JSON.parse(json);
		Object num = dbObject.get("TotalNum");
		int nu = Integer.parseInt(num.toString());
		List<String> ListHead = (List<String>) dbObject.get("ListHead");
		int ListSize = ListHead.size();
		List<Object> List = (List<Object>) dbObject.get("List");
		List<Object> record;
		BasicDBObject doc = new BasicDBObject();

		// subJson
		String subJson = "";
		Date date = new Date();

		for (int i = 0; i < List.size(); i++) {
			record = (java.util.List<Object>) List.get(i);
			doc.append("Date", date.toLocaleString());
			for (int j = 0; j < ListSize; j++) {
				// subJson=subJson+ListHead.get(j)+":"+record.get(j);
				doc.append(ListHead.get(j), record.get(j));
			}
			mon.insert(coll, doc);
			doc.clear();

		}

		return true;

	}

	public boolean isExist(String AttCode) {
		//  the code name is in the day line ?
		boolean result = false;
		String dayJson = this.getDay(AttCode);
		DBObject dbObject = (DBObject) JSON.parse(dayJson);
		List<String> ListHead = (List<String>) dbObject.get("ListHead");
		List<Object> List = (List<Object>) dbObject.get("List");
		BasicDBList theLast = (BasicDBList) List.get(List.size() - 1);
		String theLastDate = (String) theLast.get(0).toString();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String today = formatter.format(new Date());
		if (theLastDate.equals(today))
			result = true;
		log.info("today:" + today + "  theLastDate:" + theLastDate
				+ "  result:" + result);

		//  the last date is same with today?
		return result;
	}
}
