package com.zone5.bot;

import com.google.code.chatterbotapi.*;

/**
 * 
 * A chat-bot factory that generates different bots.
 * 
 * @author saurabh
 *
 */
public class ChatBotFactory 
{

	private ChatterBotFactory classFactory=null;
	private ChatterBot classBot=null;
	private ChatterBotSession classBotSession = null;
	
	public ChatBotFactory(boolean botType)
	{
		try 
		{
			classFactory = new ChatterBotFactory();
			if(botType)
			{
				
					classBot = classFactory.create(ChatterBotType.CLEVERBOT);
					classBotSession = classBot.createSession();
					System.out.println("Bot1 Activated");
			}
			else
			{
				classBot = classFactory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477");
				classBotSession = classBot.createSession();
				System.out.println("Bot1 Activated");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * A stub only for testing. Not used elsewhere.
	 * 
	 * @param args
	 * @throws Exception
	 */
    public static void main(String[] args) throws Exception 
    {
        ChatterBotFactory factory = new ChatterBotFactory();

        ChatterBot bot1 = factory.create(ChatterBotType.CLEVERBOT);
        ChatterBotSession bot1session = bot1.createSession();

        ChatterBot bot2 = factory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477");
        ChatterBotSession bot2session = bot2.createSession();

        String s = "Hi";
        while (true) {

            System.out.println("bot1> " + s);

            s = bot2session.think(s);
            System.out.println("bot2> " + s);

            s = bot1session.think(s);
        }
    }
    
    public static String chatBot1(String message)
    {
    	String reply=null;
    	
    	ChatterBotFactory factory = new ChatterBotFactory();

        ChatterBot bot1;
		try {
			bot1 = factory.create(ChatterBotType.CLEVERBOT);
		
			ChatterBotSession bot1session = bot1.createSession();
    	
        
			reply = bot1session.think(message);
		} 
        catch (Exception e) 
		{
			
			e.printStackTrace();
		}
        
    	return reply;
    }
    
    public static String chatBot2(String message)
    {
    	String reply=null;
    	
    	ChatterBotFactory factory = new ChatterBotFactory();
    	
        ChatterBot bot2;
		try {
			bot2 = factory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477");
		
			ChatterBotSession bot2session = bot2.createSession();
        
			reply = bot2session.think(message);
		} 
		catch (Exception e) 
		{
		
			e.printStackTrace();
		}
    	
    	return reply;
    }

    public String getBot(String message)
    {
    	String reply=null;
    	
    	try 
    	{
			reply = classBotSession.think(message);
		} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
		}
    	
    	return reply;
    }
}