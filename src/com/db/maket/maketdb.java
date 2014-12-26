package com.db.maket;

import com.db.get.getData;
import com.db.get.processData;
import com.db.mongo.Mongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class maketdb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * System.out.print("test"); FileWriter fileWriter=new
		 * FileWriter("e:\\Result.txt",true); int [] a=new
		 * int[]{11112,222,333,444,555,666}; for (int i = 0; i < a.length; i++)
		 * { fileWriter.write(String.valueOf(a[i])+" "); } fileWriter.flush();
		 * fileWriter.close();
		 */

		try {
			getData get=new getData();
			String json=get.get("IF1501",1);
			Mongo mongo=new Mongo();
			DBCollection collect=mongo.get("person");
			//mongo.insert(collect, json);
			
			
			///GET ALL
			processData process=new processData();
			int totalNum=process.getTotalNum(json);
			int page=totalNum/1000+1;
			for(int i=0;i<page;i++)
			{
				json=get.get("IF1501",i);
				process.process(json);
				//process
			}
			
			


		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

}
