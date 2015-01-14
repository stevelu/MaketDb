package com.db.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config
{
	private static Properties props = new Properties();

	static
	{
		load();
	}

	private static void load()
	{
		InputStream in = null;
		// String path = System.getProperty("user.dir") +"/init.properties";
		// for windows
		// String path = System.getProperty("user.dir") +"\\init.properties";
		
		String path = System.getProperty("user.dir") + "\\init.properties";
		try
		{
			//in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			in = new FileInputStream(path);
			props.load(in);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static String getProperty(final String name)
	{
		return props.getProperty(name);
	}
}

