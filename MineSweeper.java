import java.io.*; 

public class MineSweeper {
	static int bombHit,movesMade;
    static int size;
    static boolean keepGoing = true;
    
    char hold;
    static MineSweeperBoard m_game;
    static MineSweep m_gui;
    public static void printMenu()
    {
    	System.out.println("B - Show Game Board\n");
    	System.out.println("C - Show Cheat Board \n");
    	System.out.println("F x, y     - Fire on coordinates [x,y] \n");
    	System.out.println("S - Display Stats \n");
    	System.out.println("M - Display this Menu \n");
    	System.out.println("E - Exit \n");
    	
    }
    
    public static void processCommand(String incoming, int x,int y)
    {
    /*  char out = ' ';
    switch(incoming)
    {
    case "B": 
    	System.out.println(m_game.showPlayerBoard());
    	break;
    case "C":	
    	System.out.println(m_game.toString());
    	break;
    case "F":
    	out = m_game.reveal(x, y);
    	if(out != 'f')
    	{
    		if(out == 'X')
    		{
    			bombHit++;
    		}
    		movesMade++;
    		
    	}
    	System.out.println(m_game.showPlayerBoard());
    	break;
    case "S":
    	printStats();
    	break;
    case "M":
    //	printMenu();
    	break;
    case "E":
    	keepGoing = false;
    	break;
    default:
    	System.out.println("Invalid input please try again");
    	break;
    }*/
    
   
    }
    public static void printStats()
    {
    	System.out.println("Number of Game Moves: "+movesMade+"\n" );
    	System.out.println("Number of Bombs Remaining on the Board: "+(m_game.getBombs()-bombHit)+"\n");
    	System.out.println("Number of Bombs Exposed: " +bombHit+"\n");
    	    	
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	/*	// TODO Auto-generated method stub
		System.out.println("Please Enter the Board Size(between 6 and 30)");
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 try {
			 String data = br.readLine();
			 size  = Integer.parseInt(data);
			 System.out.println("Please Enter the filename of the bombfile");
			 data = br.readLine();
			 if(size >=6 && size <=30 )
			 {
				 m_game = new MineSweeperBoard(size,data);
	            			 
  			 }else
  			 {
  				 m_game = new MineSweeperBoard(6,data);
  				 size = 6;
  			 }
			
			 
			 
	      } catch (IOException ioe) {
	         System.err.println("IO error not valid input!");
	         keepGoing = false;
	         } 
		 
		   
		 String command;
		 int x = 99;
		 int y = 99;
		 if(keepGoing && m_game != null)
		 {
		 m_gui=new MineSweep(m_game);
		 m_gui.makeGui();
	     }
		 while(keepGoing && m_game != null)
		 {
			
			 printMenu();
			 try {
				if(bombHit <= 3)
				{
      			 System.out.println("Please enter a command");
                 command = br.readLine();
				 String hold = command.substring(1);
				 hold = hold.replaceAll("\\s+", "");
			     String[] coord = hold.split(",");
			    command = command.substring(0,1);
			    if(hold != null && hold.length() != 0)
			    {
			    x = Integer.parseInt(coord[0]);
				y = Integer.parseInt(coord[1]);
			    }
			    processCommand(command,x,y);
			  /*  switch(command)
			    {
			    case "B": 
			    	System.out.println(m_game.showPlayerBoard());
			    	break;
			    case "C":	
			    	System.out.println(m_game.toString());
			    	break;
			    case "F":
			    	char out = m_game.reveal(x, y);
			    	if(out != 'f')
			    	{
			    		if(out == 'X')
			    		{
			    			bombHit++;
			    		}
			    		movesMade++;
			    		
			    	}
			    	System.out.println(m_game.showPlayerBoard());
			    	break;
			    case "S":
			    	printStats();
			    	break;
			    case "M":
			    	printMenu();
			    	break;
			    case "E":
			    	keepGoing = false;
			    	break;
			    default:
			    	System.out.println("Invalid input please try again");
			    	break;
			    }*/
			/*	}else
				{
					keepGoing = false;
					System.out.println("Game Over");
					printStats();
				}
			   
				
				 
		      } catch (IOException ioe) {
		         System.err.println("IO error not valid input!");
		         }
			    catch( NumberFormatException f)
			    {
			    	 System.err.println("Coordinates are not integers");
			    }
			 
			 
		 }*/
		 
		 
	       
         
	}

}
