package com.db.maket;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.db.get.Data;
import com.db.mongo.Mongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class maketdb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//monday-friday 15:30 run except holiday
		//
		//step1:getlist
		//step2:is exist? creat collection
		//step3:getDate
		//step4:is exist? break
		//step5: process
		
		Mongo mongo=new Mongo();		

		
		String code="IF1502";
		try {
			Data data=new Data();
			String listStr=data.getList();
			List<String> CodeName=data.FilterList(listStr);
			String json;
			int totalNum;
			int page;
			
			//code name is exist in db
            Set<String> colName=mongo.getColName();
            Iterator<String> iter=CodeName.iterator();
            for(String name:CodeName)
            {
            	
            	if(!colName.contains(name))
        		{//creat collections
        		//mongo.creat(name);
        		
        		}
            	
            	json=data.getTick(name,1);
            	DBCollection collect=mongo.get(name);
            	totalNum=data.getTotalNum(json);
            	page=totalNum/1000+1;
    			for(int i=0;i<page;i++)
    			{
    				System.out.println("code:"+name+"---page:"+i);
    				json=data.getTick(name,i);
    				//TODO step3 and step4
    	            //
    	            //
    				data.process(json,mongo,collect);
    				
    				//process
    			}

            }

            
/*            //TODO step3 and step4
            //
            //
			 json=data.getTick(code,1);

			DBCollection collect=mongo.get(code);
			//mongo.insert(collect, json);
			
			
			///GET ALL
			int totalNum=data.getTotalNum(json);
			int page=totalNum/1000+1;
			for(int i=0;i<page;i++)
			{
				System.out.println("code:"+code+"---page:"+i);
				json=data.getTick(code,i);
				data.process(json,mongo,collect);
				
				//process
			}*/
			
			


		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

}
