package com.db.get;

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
	public Boolean process(String json)
	{
		DBObject dbObject = (DBObject) JSON.parse(json);
		Object num=dbObject.get("TotalNum");
		int nu=Integer.parseInt(num.toString());
		String[] ListHead=(String[]) dbObject.get("ListHead");
		Object List=dbObject.get("List");
		
		for(int i=0;i<nu;i++)
		{
			
			
		}
		
		return true;
		
	}

}
