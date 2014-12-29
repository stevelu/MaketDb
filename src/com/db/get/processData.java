package com.db.get;

import java.util.List;

import com.db.mongo.Mongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class processData {

	public int getTotalNum(String json) {
		// TODO Auto-generated constructor stub

			DBObject dbObject = (DBObject) JSON.parse(json);
			Object num=dbObject.get("TotalNum");
			int nu=Integer.parseInt(num.toString());
			
			return nu;
			
		
	}
	public Boolean process(String json ,Mongo mon,DBCollection coll)
	{
		DBObject dbObject = (DBObject) JSON.parse(json);
		Object num=dbObject.get("TotalNum");
		int nu=Integer.parseInt(num.toString());
		List<String> ListHead=(List<String>) dbObject.get("ListHead");
		int ListSize=ListHead.size();
		List<Object> List=(List<Object>) dbObject.get("List");
		List<Object> record;
		BasicDBObject doc = new BasicDBObject();
		
		//subJson
		String subJson="";

		
		for(int i=0;i<List.size();i++)
		{
			record=(java.util.List<Object>) List.get(i);
			for(int j=0;j<ListSize;j++)
			{
				//subJson=subJson+ListHead.get(j)+":"+record.get(j);
				doc.append(ListHead.get(j), record.get(j));				
			}
			mon.insert(coll, doc);
			System.out.println("insert:"+i);
			doc.clear();
			
			
		}
		
		return true;
		
	}

}
