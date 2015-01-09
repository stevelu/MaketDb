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


/**
 * main: run every weekday afternoon
 * 
 * @author beyond.lu
 * 
 *
 */
public class maketdb {
	private static Logger log = LogManager.getLogger(maketdb.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// monday-friday 15:30 run except holiday

		// config
		// for linux
		// String path = System.getProperty("user.dir") +"/init.properties";
		String path = System.getProperty("user.dir") + "\\init.properties";
		String json=null;
		int totalNum=0;
		int page=0;
		log.info("---task sart---");
		try {
			log.info("get config file");
			Properties p = new Properties();
			p.load(new FileInputStream(path));
			String host=p.getProperty("mongo.url");
			String dbname=p.getProperty("mongo.db");
			String username=p.getProperty("mongo.username");
			String password=p.getProperty("mongo.password");

			Mongo mongo = new Mongo(host,dbname,username,password);
			Data data = new Data();
			String listStr = data.getList();
			List<String> CodeName = data.FilterList(listStr);
			// code name is exist in db
			Set<String> colName = mongo.getColName();
			Iterator<String> iter = CodeName.iterator();
			for (String name : CodeName) {
				if (data.isExist(name))// is exist data in today
				{// TODO is there data for date

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


		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}
}
