package com.zone5.util;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

/**
 * 
 * This program uses the the Java JNI library to loop through all application windows 
 * open in your current desktop.
 * 
 * It looks for a particular pattern in the application's window name. 
 * If it matches the one used by IBM Sametime,it adds it to the list of our chat boxes.
 * 
 * Also, from the window name, we identify the user we are talking to.
 * 
 * @author saurabh
 *
 */
public class EnumAllWindowNames {
   static interface User32 extends StdCallLibrary {
      User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);

      interface WNDENUMPROC extends StdCallCallback {
         boolean callback(Pointer hWnd, Pointer arg);
      }

      boolean EnumWindows(WNDENUMPROC lpEnumFunc, Pointer userData);
      int GetWindowTextA(Pointer hWnd, byte[] lpString, int nMaxCount);
      Pointer GetWindow(Pointer hWnd, int uCmd);
   }

   public static List<String> getAllWindowNames() {
      final List<String> windowNames = new ArrayList<String>();
      final User32 user32 = User32.INSTANCE;
      user32 .EnumWindows(new User32.WNDENUMPROC() {

         @Override
         public boolean callback(Pointer hWnd, Pointer arg) {
            byte[] windowText = new byte[512];
            user32.GetWindowTextA(hWnd, windowText, 512);
            String wText = Native.toString(windowText).trim();
            if (!wText.isEmpty()) {
               windowNames.add(wText);
            }
            return true;
         }
      }, null);

      return windowNames;
   }
   
   public static List<String> getChatWindowList()
   {
	   List<String> winNameList = getAllWindowNames();
	   List<String> chatWinList = new ArrayList<String>();
	   
	   int endIndex;
	   String caller=null;
	   String folderName;
	      
      for (String winName : winNameList) 
      {
    	 endIndex = winName.indexOf(" [started:"); 
    	 if(endIndex>-1)
    	 {
    		 //System.out.println(winName);
    		 chatWinList.add(winName);
    	 }
       }
      return chatWinList;
   }

   /**
    * A stub added for stand-alone testing. Not used elsewhere.
    * @param args
    */
   public static void main(String[] args) 
      {
	      List<String> winNameList = getAllWindowNames();
	      
	      int endIndex;
	      String caller=null;
	      String folderName;
	      
	      for (String winName : winNameList) 
	      {
	    	 endIndex = winName.indexOf(" [started:"); 
	    	 if(endIndex>-1)
	    	 {
	    		 System.out.println(winName);
	    		 caller = winName.substring(0, endIndex);
	    		 folderName = Mapper.getFolderMap(caller);
	    		 //System.out.println(caller+" has a folder "+folderName);
	    		 
	    	 }
          }
      } 


}