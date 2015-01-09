package com.db.mongo;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.db.maket.maketdb;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class Mongo {
	private static Logger log = LogManager.getLogger(Mongo.class.getName());

	static DB db = null;

	public Mongo() {
		// TODO Auto-generated constructor stub
		String host ="localhost";
		String dbname ="tset";
		String username ="tset";
		String password ="test";
		db = connect(host,dbname,username,password);
	}
	public Mongo(String host,String dbname,String username,String password) {
		// TODO Auto-generated constructor stub
		db = connect(host,dbname,username,password);
	}

	public DB connect(String host,String dbname,String username,String password) {
		DB db = null;
		try {
			// 连接到 mongodb 服务
			String psw = password;
			MongoClient mongoClient = new MongoClient(host, 27017);
			// 连接到数据库
			db = mongoClient.getDB(dbname);
			log.info("Connect to database successfully");
			boolean auth = db.authenticate(username, psw.toCharArray());
			log.info("Authentication: " + auth);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return db;

	}

	public void creat(String collectionName) {
		DBObject options = BasicDBObjectBuilder.start().add("capped", true).get();

		DBCollection collection = db.createCollection(collectionName, options);

	}

	public DBCollection get(String CollectName) {
		// get collection
		DBCollection collect = db.getCollection(CollectName);
		return collect;

	}

	public void insert(DBCollection collect, String json) {
		// insert object
		DBObject doc = change(json);

		collect.insert(doc);
	}
	
	public void insert(DBCollection collect, DBObject doc) {
		// insert object

		collect.insert(doc);
	}

	public DBCursor find(DBCollection collect) {
		// find
		DBCursor cursor = collect.find();
		int i = 1;
		while (cursor.hasNext()) {
			System.out.println("Inserted Document: " + i);
			System.out.println(cursor.next());
			i++;
		}
		return cursor;
	}

	public DBObject change(String json) {
		DBObject dbObject = (DBObject) JSON.parse(json);
		return dbObject;

	}

	public Set<String> getColName() {
		// TODO Auto-generated method stub
		Set<String> colName=db.getCollectionNames();
		return colName;
	}

}
