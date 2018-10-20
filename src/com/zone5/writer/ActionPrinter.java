package com.zone5.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * A class the provided a mechanism to talk to Auto-It.
 * It simply writes a command in a local file that is read by Auto-It and vice versa.
 * 
 * @author saurabh
 *
 */
public class ActionPrinter 
{
	// Updated as per convenient
	private static File file = new File("C:/Users/TestFile.txt");
	
	private static FileWriter fw = null; 
	private static BufferedWriter bw = null;
   
	/**
	 * Write the desired action the file.
	 * 
	 * @param winName
	 * @param message
	 */
	public static void writeAction(String  winName, String message)
	{
		try 
		{
			if (!file.exists()) 
			{
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			String content = winName+"{"+message;
 
			bw.newLine();
			bw.write(content);
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * A stub for stand-alone testing. Not used elsewhere.
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		for(int i=0;i<10;i++)
		{
			writeAction("Nicolas Cage", "test"+i);
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}