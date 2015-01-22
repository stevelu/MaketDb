package com.db.maket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.db.get.Data;
import com.db.mongo.Mongo;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Process extends Data
{

	private static Logger log = LogManager.getLogger(Process.class.getName());

	public Process()
	{

	}

	public Process(String iRUL)
	{
		super.Url = iRUL;
	}

	public List<String> getCodeNameList()
	{
		return FilterList(super.getList());
	}

	/**
	 * filter code name like IF1501
	 * 
	 * @param str
	 * @return list of code name
	 */
	public List<String> FilterList(String str)
	{
		List<String> list = new ArrayList<String>();
		// 正值表达式查找IF1502
		String pattern = "IF\\d\\d\\d\\d";
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);
		// 现在创建 matcher 对象
		Matcher m = r.matcher(str);
		while (m.find())
		{
			String A = m.group();
			list.add(A);
		}

		return list;
	}

	public int getTotalNum(String AttCode)
	{

		String json = super.getTick(AttCode, 0);
		DBObject dbObject = (DBObject) JSON.parse(json);
		Object num = dbObject.get("TotalNum");
		int nu = Integer.parseInt(num.toString());

		return nu;

	}

	public Boolean process(int page, String json, Mongo mon, DBCollection coll)
	{

		DBObject dbObject = (DBObject) JSON.parse(json);
		BasicDBList ListHead = (BasicDBList) dbObject.get("ListHead");
		int ListSize = ListHead.size();
		BasicDBList List = (BasicDBList) dbObject.get("List");
		BasicDBList record;
		BasicDBObject doc = new BasicDBObject();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String today = formatter.format(new Date());

		for (int i = 0; i < List.size(); i++)
		{
			record = (BasicDBList) List.get(i);
			doc.append("Date", Integer.parseInt(today));
			for (int j = 0; j < ListSize; j++)
			{
				// subJson=subJson+ListHead.get(j)+":"+record.get(j);
				doc.append(ListHead.get(j).toString(), record.get(j));
			}
			mon.insert(coll, doc);
			doc.clear();

		}

		return true;

	}

	public boolean isExist(String AttCode)
	{
		// the code name is in the day line ?
		boolean result = false;
		String dayJson = super.getDay(AttCode);
		DBObject dbObject = (DBObject) JSON.parse(dayJson);
		BasicDBList List = (BasicDBList) dbObject.get("List");
		BasicDBList theLast = (BasicDBList) List.get(List.size() - 1);
		String theLastDate = (String) theLast.get(0).toString();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String today = formatter.format(new Date());
		if (theLastDate.equals(today))
			result = true;
		log.info("today:" + today + "  theLastDate:" + theLastDate + "  result:" + result);

		// the last date is same with today?
		return result;
	}

}
