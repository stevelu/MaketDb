package com.db.maket;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db.mongo.Mongo;
import com.db.tools.Config;
import com.mongodb.DBCollection;


/**
 * main: run every weekday afternoon 15:40
 * 
 * @author beyond.lu
 * 
 * 
 */
public class maketdb {
	private static Logger log = LogManager.getLogger(maketdb.class.getName());

	public static void main(String[] args) {
		// monday-friday 15:40 run except holiday
		String host = Config.getProperty("mongo.url");
		String dbname = Config.getProperty("mongo.db");
		String username = Config.getProperty("mongo.username");
		String password = Config.getProperty("mongo.password");

		String json = null;
		int totalNum = 0;
		int page = 0;
		log.info("---task sart---");
		try {
			Mongo mongo = new Mongo(host, dbname, username, password);
			Process data = new Process();
			List<String> CodeName = data.getCodeNameList();
			CodeName.iterator();
			for (String name : CodeName) 
			{
				if (data.isExist(name))// is exist data in today
				{
					DBCollection collect = mongo.get(name);
					totalNum = data.getTotalNum(name);
					page = totalNum / 1000 + 1;
					log.info("code:" + name + "  totalNum:" + totalNum);					
					for (int i = 0; i < page; i++) 
					{
						log.info("code:" + name + "/page:" + i);
						json = data.getTick(name, i);
						data.process(i, json, mongo, collect);
					}
				}
			}
			log.info("---task end---");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}
}
