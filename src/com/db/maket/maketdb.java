package com.db.maket;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.db.get.Data;
import com.db.mongo.Mongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class maketdb {
	private static Logger log = LogManager.getLogger(maketdb.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// monday-friday 15:30 run except holiday
		//
		// step1:getlist
		// step2:is exist? creat collection
		// step3:getDate
		// step4:is exist? break
		// step5: process
		
		
		//config
		//for linux 
		//String path = System.getProperty("user.dir") +"/init.properties";
		String path = System.getProperty("user.dir") +"\\init.properties";
		

		
		log.info("---task sart---");
		Mongo mongo = new Mongo();

		String code = "IF1502";
		try {
			log.info("get config file");
			Properties p = new Properties();
			p.load(new FileInputStream(path));
			log.info("config file isEmpty"+p.isEmpty()+p.getProperty("test"));
			
			Data data = new Data();
			String listStr = data.getList();
			List<String> CodeName = data.FilterList(listStr);
			String json;
			int totalNum;
			int page;

			// code name is exist in db
			Set<String> colName = mongo.getColName();
			Iterator<String> iter = CodeName.iterator();
			for (String name : CodeName) {
				if(data.isExist(name))
				{//TODO is there data for date
					
					if (!colName.contains(name)) {// creat collections
													// mongo.creat(name);
	
					}
	
					json = data.getTick(name, 0);
					DBCollection collect = mongo.get(name);
					totalNum = data.getTotalNum(json);
					page = totalNum / 1000 + 1;
					log.info("code:" + name + "  totalNum:" + totalNum);
					for (int i = 0; i < page; i++) {
						log.info("code:" + name + "/page:" + i);
						json = data.getTick(name, i);
						// TODO step3 and step4
						//
						//
						data.process(i, json, mongo, collect);
	
						// process
					}
					log.info("---task end---");
				}
				
			    

			}

			/*
			 * //TODO step3 and step4 // // json=data.getTick(code,1);
			 * 
			 * DBCollection collect=mongo.get(code); //mongo.insert(collect,
			 * json);
			 * 
			 * 
			 * ///GET ALL int totalNum=data.getTotalNum(json); int
			 * page=totalNum/1000+1; for(int i=0;i<page;i++) {
			 * System.out.println("code:"+code+"---page:"+i);
			 * json=data.getTick(code,i); data.process(json,mongo,collect);
			 * 
			 * //process }
			 */

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}
}
