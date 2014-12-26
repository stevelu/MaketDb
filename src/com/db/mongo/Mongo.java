package com.db.mongo;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class Mongo {

	static DB db = null;

	public Mongo() {
		// TODO Auto-generated constructor stub
		db = connect();
	}

	public DB connect() {
		DB db = null;
		try {
			// 连接到 mongodb 服务
			String psw = "test";
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			// 连接到数据库
			db = mongoClient.getDB("test");
			System.out.println("Connect to database successfully");
			boolean auth = db.authenticate("test", psw.toCharArray());
			System.out.println("Authentication: " + auth);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return db;

	}

	public void creat() {
		DBObject options = BasicDBObjectBuilder.start().add("capped", true)
				.add("size", 123456).get();

		DBCollection collection = db.createCollection("phone1", options);

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

}
