package com.zone5.reader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.zone5.bot.ChatBotFactory;
import com.zone5.util.Mapper;
import com.zone5.writer.ActionPrinter;
 
 /**
  * 
  * The program that attaches a reader for every chat.
  * 
  * It leverages the fact that all incoming message are saved in a *.log.html file.
  * 
  * KNOWN ISSUES: Does not understand emojis.
  * 
  * @author saurabh
  *
  */
public class HtmlReader2 
{
 
   // the path were log files are present. UPDATE this!!!
	private static String parentPath = "C:/Users/<your-account>/Documents/SametimeTranscripts";
	
  
	/**
	 * Attach a reader to the given window.
	 * 
	 * @param winName
	 */
    public static void attachReader(String winName)
    {
    	String userName = Mapper.getUserMap(winName);
    	
    	String folderName = null;
        folderName = Mapper.getFolderMap(userName);
        
        // We don't want to worry about the chats that did not happen today.
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        
        String fileName = new String(folderName.substring(0, 20)+"log.html");
        
        String path = parentPath+"/"+folderName+"/"+strDate+"/"+fileName;
        
        //System.out.println(path);
        
        File file = new File(path);
        try {
            RandomAccessFile r = new RandomAccessFile(file, "r");
            //First time read
            String str = null;
            while((str = r.readLine()) != null) 
            {
             //System.out.println(str);
            }
            r.seek(r.getFilePointer());
            startTimer(r);
        } catch (FileNotFoundException e) 
        {
            //e.printStackTrace();
     	   System.out.println("Nothing today!!");
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * A poller to check if a new message has arrived.
     * 
     * @param r
     */
    private static void startTimer(final RandomAccessFile r) {
     Timer timer = new Timer();
     final ChatBotFactory chatBot = new ChatBotFactory(true);
     timer.scheduleAtFixedRate(new TimerTask() 
     {
    	 @Override
	     public void run() 
	     {
		     String str = null;
      	   	int startIndex;
      	   	int endIndex;
      	   	String temp1;
      	    String message = null;
      	    String author;
      	    
		     try 
		     {
		          while((str = r.readLine()) != null) 
		          {
		           //System.out.println(str);
		        	   author = findAuthor(str);
			           if(author!=null)
			           {
			        	   // IMPORTANT: Update this by your name.
			        	   // This is needed for program to ignore its own messages.
			        	   if(!author.equals("Nicolas Cage"))
			        	   {
				        	   startIndex = str.lastIndexOf("dir=\"ltr\" >") + 11;
					           if (startIndex>10)
					           {
					        	   temp1 = str.substring(startIndex);
					        	  //endIndex = temp1.indexOf("</span><span class=\"system\">");
					        	   endIndex = temp1.indexOf("<span class=\"system\">");
					        	   message = temp1.substring(0, endIndex);
					        	   System.out.println(author+" says: "+message);
					        	   //String reply = ChatterBotApiTest.chatBot1(message);
					        	   String reply = chatBot.getBot(message);
					        	   ActionPrinter.writeAction(author, reply);
					           }
			        	   }
			        	   else
			        	   {
			        		   startIndex = str.lastIndexOf("class=\"left\">") + 13;
			        		   if (startIndex>12)
					           {
					        	   temp1 = str.substring(startIndex);
					        	  endIndex = temp1.indexOf("</span><span class=\"system\">");
					        	   message = temp1.substring(0, endIndex);
					        	   System.out.println(author+" says: "+message);
					        	   //String reply = ChatterBotApiTest.chatBot1(message);
					        	   //String reply = chatBot.getBot(message);
					        	   //ActionPrinter.writeAction(author, reply);
					           }
			        	   }
			           }
		        	   
		          } 
		          r.seek(r.getFilePointer()); 
		     } catch (IOException e) { 
		         e.printStackTrace();
		     }
	     }
    	 
    	 public String findAuthor(String str)
    	 {
    		 String author=null;
    		 int startIndex;
       	   	 int endIndex;
       	   	 String temp1;
       	   	 
	       	  startIndex = str.indexOf("title=\"") + 7;
	          if (startIndex>6)
	          {
	       	   temp1 = str.substring(startIndex);
	       	  endIndex = temp1.indexOf("[")-1;
	       	  author = temp1.substring(0, endIndex); 
	       	  //System.out.println(message);
	          }
       	   	 
    		 return author;
    		 
    	 }
     }, 0, 1000);
    }
    
    
    /**
     * A stub created for stand-alone testing. Not used elsewhere.
     * 
     * @param args
     */
    public static void main(String[] args) 
    {
    
       String folderName = null;
       folderName = Mapper.getFolderMap("Nicolas Cage");
       
       SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
       Date now = new Date();
       String strDate = sdfDate.format(now);
       
       String fileName = new String(folderName.substring(0, 20)+"log.html");
       
       String path = parentPath+"/"+folderName+"/"+strDate+"/"+fileName;
       
       System.out.println(path);
       
       File file = new File(path);
       try {
           RandomAccessFile r = new RandomAccessFile(file, "r");
           //First time read
           String str = null;
           while((str = r.readLine()) != null) 
           {
            //System.out.println(str);
           }
           r.seek(r.getFilePointer());
           startTimer(r);
       } catch (FileNotFoundException e) 
       {
           //e.printStackTrace();
    	   System.out.println("Nothing today!!");
       } catch (IOException e) 
       {
           e.printStackTrace();
       } 
    }
}