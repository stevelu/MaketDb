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
		// String apiUrl = "http://119.97.185.7:7615/TQLEX?Entry=HQServ.Tick";
		// String
		// para="{\"Code\":\"IF1409\",\"Setcode\":47,\"Date\":0704,\"Startxh\":-1,\"WantNum\":10,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.MultiHQ";
		String para = "{\"WantCol\":[\"NOW\",\"VOL\",\"VolInStock\",\"PreVolInStock\",\"ClearPrice\",\"LB\",\"OPEN\",\"MAX\",\"MIN\",\"CLOSE\",\"XSFLAG\"],\"SetDomain\":47,\"SubCode\":\"\",\"ColType\":14,\"Startxh\":0,\"WantNum\":20,\"SortType\":0,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		URLConnectionHelper helper = new URLConnectionHelper();
		String str = helper.sendPost(apiUrl, para);
		//
		// {"SBTSize": 19,"ListHead":
		// ["Code","Setcode","Name","CLOSE","OPEN","MAX","MIN","NOW","VOL","LB","PreVolInStock","VolInStock","ClearPrice","XSFLAG"],"List":
		// [["IFL0",47,"沪深当月","3468.400","3475.000","3526.000","3451.800","3518.000",788466,"0.994",123590,145008,"0.000",1],["IFL1",47,"沪深下月","3505.200","3510.200","3564.000","3485.200","3553.200",12191,"0.968",7102,7241,"0.000",1],["IFL2",47,"沪深下季","3535.800","3557.600","3597.400","3516.600","3589.600",60144,"1.035",47428,50518,"0.000",1],["IFL3",47,"沪深隔季","3564.000","3564.000","3618.600","3538.600","3612.400",23439,"0.987",23736,24197,"0.000",1],["TFL0",47,"国债当季","96.856","96.838","96.850","96.444","96.570",5451,"1.663",19479,19418,"0.000",3],["TFL1",47,"国债下季","97.388","97.300","97.554","97.010","97.134",538,"3.324",2151,2347,"0.000",3],["TFL2",47,"国债隔季","97.686","97.516","97.564","97.366","97.468",12,"0.667",130,126,"0.000",3],["IF300",47,"沪深300","3455.460","3450.810","3482.510","3422.170","3476.980",173067702,"1.043",0,0,"0.000",2],["TF1503",47,"国债1503","96.856","96.838","96.850","96.444","96.570",5451,"1.663",19479,19418,"0.000",3],["IF1503",47,"沪深1503","3535.800","3557.600","3597.400","3516.600","3589.600",60144,"1.035",47428,50518,"0.000",1],["IFL8",47,"沪深主力","3468.400","3475.000","3526.000","3451.800","3518.000",788466,"0.994",123590,145008,"0.000",1],["IFL9",47,"沪深加权","3496.800","3513.800","3552.800","3477.200","3545.200",884240,"0.996",201856,226964,"0.000",1],["TFL8",47,"国债主力","96.856","96.838","96.850","96.444","96.570",5451,"1.663",19479,19418,"0.000",3],["TFL9",47,"国债加权","96.914","96.853","96.860","96.507","96.636",6001,"1.735",21760,21891,"0.000",3],["TF1506",47,"国债1506","97.388","97.300","97.554","97.010","97.134",538,"3.324",2151,2347,"0.000",3],["IF1506",47,"沪深1506","3564.000","3564.000","3618.600","3538.600","3612.400",23439,"0.987",23736,24197,"0.000",1],["IF1501",47,"沪深1501","3468.400","3475.000","3526.000","3451.800","3518.000",788466,"0.994",123590,145008,"0.000",1],["TF1509",47,"国债1509","97.686","97.516","97.564","97.366","97.468",12,"0.667",130,126,"0.000",3],["IF1502",47,"沪深1502","3505.200","3510.200","3564.000","3485.200","3553.200",12191,"0.968",7102,7241,"0.000",1]]}

		// Gson gson=new Gson();
		// category cate=new category();
		// cate=gson.fromJson(str, category.class);
		// BasicDBObject list=(BasicDBObject)JSON.parse(str);
		// Object objList=list.get("List");

		// test

		// end
		return str;

	}

	public List<String> FilterList(String str) {
		List<String> list = new ArrayList<String>();

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
		// String
		// para="{\"Code\":\"IF1409\",\"Setcode\":47,\"Date\":0704,\"Startxh\":-1,\"WantNum\":10,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.Tick";
		// String
		// para="{\"Code\":\"IF1412\",\"Setcode\":47,\"Date\":0,\"Startxh\":0,\"WantNum\":1000,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		String para = "{\"Code\":\""
				+ AttCode
				// + "\",\"Setcode\":47,\"Date\":0,\"Startxh\":"
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
		//get data for day line
		int pageNum = 0;

		long Period = 4;

		// String apiUrl = "http://119.97.185.7:7615/TQLEX?Entry=HQServ.Tick";
		// String
		// para="{"Code":"IF1501","Setcode":47,"Period":4,"Startxh":0,"WantNum":240,"TQFlag":0,"HasAttachInfo":0,"ExHQFlag":1,"CharSet":""}";
		String apiUrl = "http://119.97.185.12:7615/TQLEX?Entry=HQServ.FXT";
		// String
		// para="{\"Code\":\"IF1412\",\"Setcode\":47,\"Date\":0,\"Startxh\":0,\"WantNum\":1000,\"HasAttachInfo\":1,\"ExHQFlag\":1,\"CharSet\":\"\"}";
		String para = "{\"Code\":\""
				+ AttCode
				// + "\",\"Setcode\":47,\"Date\":0,\"Startxh\":"
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
		// TODO Auto-generated constructor stub

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
			//log.info(coll.getName() + "/page" + page + "/insert:" + i);
			doc.clear();

		}

		return true;

	}

	public boolean isExist(String AttCode) {
		// TODO the code name is in the day line ?
		boolean result=false;
		String dayJson=this.getDay(AttCode);
		DBObject dbObject = (DBObject) JSON.parse(dayJson);
		List<String> ListHead = (List<String>) dbObject.get("ListHead");
		List<Object> List = (List<Object>) dbObject.get("List");
		BasicDBList theLast=(BasicDBList)List.get(List.size()-1);
		String theLastDate=(String)theLast.get(0).toString();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String today=formatter.format(new Date());
		if(theLastDate.equals(today))result=true;


		//TODO  the last date is same with today?
		return result;
	}
}
