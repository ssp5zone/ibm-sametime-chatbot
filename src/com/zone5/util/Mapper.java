package com.zone5.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * There is a messaging history log created for each user. But they are not stored directly by user name.
 * 
 * Hence, we get the name of the file from a map called personfolders.xml.
 * 
 * @author saurabh
 *
 */
public class Mapper 
{
	
	static public String getFolderMap(String userName)
	{
		String folderName=null;
		
		// Update this directory as per needed
		File file = new File("C:/Users/<your-account>/Documents/SametimeTranscripts/personfolders.xml");
		
		try 
		  {
	           RandomAccessFile r = new RandomAccessFile(file, "r");
	           int startIndex;
	      	   	int endIndex;
	      	   	String temp1;
	      	   	String uName = null;
	           String str = null;
	           while((str = r.readLine()) != null) 
	           {
	        	   startIndex = str.lastIndexOf("displayName=\"") + 13;
		           if (startIndex>12)
		           {
		        	  temp1 = str.substring(startIndex);
		        	  endIndex = temp1.indexOf("\"");
		        	  uName = temp1.substring(0, endIndex);
		        	  //System.out.println("Found Name: "+uName);
		        	  //System.out.println("Passed Name: "+userName);
		        	  if(uName.equals(userName))
		        	  {
		        		  startIndex = str.lastIndexOf("folderPath=\"") + 12;
		        		  if (startIndex>12)
				           {
				        	  temp1 = str.substring(startIndex);
				        	  endIndex = temp1.indexOf("\"");
				        	  folderName = temp1.substring(0, endIndex);
				        	  //System.out.println("Folder Name: "+folderName);
				        	  break;
				           }
		        	  }
		        	  //System.out.println(author+" says: "+message);
		           }
	           }
	           //r.seek(r.getFilePointer());
	           
	       } catch (FileNotFoundException e) {
	           e.printStackTrace();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
		
		return folderName;
	}
	
	/**
	 * Find the name of user from the window title.
	 * 
	 * @param winName
	 * @return
	 */
	public static String getUserMap(String winName)
	{
		 String userName=null;
		 
		 int endIndex;
		 
		 endIndex = winName.indexOf(" [started:"); 
	   	 if(endIndex>-1)
	   	 {
	   		 System.out.println(winName);
	   		 userName = winName.substring(0, endIndex);
	   		 
	   	 }
		
		return userName;
	}
	
	
	/*public static void main(String[] args) 
	{
		getFolderMap("Nicolas Cage");
	}*/

}
