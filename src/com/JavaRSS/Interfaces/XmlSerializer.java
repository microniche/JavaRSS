package com.JavaRSS.Interfaces;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.JavaRSS.Beans.Feed;

public class XmlSerializer {
	static public void writeTo(Object obj, OutputStream dest)
	{
		
		XMLEncoder encoder = null;
		 
		try 
		{
			encoder = new XMLEncoder(new BufferedOutputStream(dest));
			encoder.writeObject(obj);
			encoder.flush();
		} 
		finally
		{
			if (encoder != null)
			{
				encoder.close();
			}
		}
	}
}
