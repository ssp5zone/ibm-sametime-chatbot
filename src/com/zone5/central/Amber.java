package com.zone5.central;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.zone5.reader.HtmlReader2;
import com.zone5.util.EnumAllWindowNames;

/**
 * The main program where all the thinking happens.
 * 
 * @author saurabh
 *
 */
public class Amber 
{
	public static HashSet<String> winSet = new HashSet<String>();
	//public static HashMap<String,String> winMap = new HashMap<String,String>();
	
	public static void main(String[] args) 
	{
		Timer timer = new Timer();

		// Pooling to check if any new chat has opened. 
		// Yes we support, multiple chats at the same time.
		timer.scheduleAtFixedRate(new TimerTask() 
		{

			@Override
			public void run() 
			{
				List<String> winList = EnumAllWindowNames.getChatWindowList();
				for(String winName:winList)
				{
					// If we are not already chatting with this user
					if(!checkSet(winSet, winName))
					{
						// add to our list and attach a reader
						winSet.add(winName);
						System.out.println("Added: "+winName);
						HtmlReader2.attachReader(winName);
					}
				}
			}
			
		}, 0, 5000); 
		
		

	}
	
	/**
	 * 
	 * Check if we are already talking with this user.
	 * 
	 * @param hSet
	 * @param value
	 * @return
	 */
	private static boolean checkSet(HashSet<String> hSet,String value)
	{
		for(String hSetVal:hSet)
		{
			if(hSetVal.equalsIgnoreCase(value))
				return true;
		}
		return false;
	}

}
