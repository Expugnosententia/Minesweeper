import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;
/** 
 *Creates Gui/plays MineSweeper
 * @author      Daniel Pimienta 
 * */

public class MineSweep implements ActionListener {
	MineSweeperBoard board;
	int size,bombHit,turns,morebombs,wait,coord1,coord2,left;
	JFrame hold;
	JPanel game,stats,options;
	JButton[][] button;
	JButton normal,cheat;
	JLabel moves,bombs,bombsleft;
	/*
	 * @param in the Minesweeper board object
	 */
    public MineSweep(MineSweeperBoard in)
    {
    	board = in;
    	size = in.getSize();
    	
    	turns = 0;
    	bombHit = 0;
    	morebombs = board.getBombs();
    	left = ((size*size)-morebombs);
    	button = new JButton[size][size];
    	hold = new JFrame();
        hold.setLayout(new BorderLayout());
    	game = new JPanel(new GridLayout(size,size));
    	options = new JPanel();
    	
    	stats = new JPanel();
    	moves = new JLabel("Moves Made: 0");
    	bombs = new JLabel("Bombs Hit: 0");
    	normal = new JButton("Normal");
    	normal.addActionListener(this);
    	//normal.setActionCommand("Normal");
    	cheat = new JButton("Cheat");
    	cheat.addActionListener(this);
    	//cheat.setActionCommand("Cheat");
    	bombsleft = new JLabel("Bombs left "+String.format("%d",morebombs));
    	hold.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {

         	   hold.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            }
 		});
  
    	for(int i =0;i<size;i++)
    	{
    		for(int x =0;x<size;x++)
    		{
    			String label =Character.toString(board.getPlayerCell(i, x));
    		button[i][x] = new JButton(label);
    		button[i][x].setActionCommand(i+","+x);
    		 button[i][x].addActionListener(this);
        	}
    	}
    }
    /*
     * makes the GUI
     */
    public void makeGui()
    {
       	for(int i =0;i<size;i++)
    	{
    		for(int x =0;x<size;x++)
    		{
    			game.add(button[i][x]);
        	}
    	}
       	options.add(normal);
       	options.add(cheat);
       	stats.add(moves);
       	stats.add(bombs);
       	stats.add(bombsleft);
       	hold.add(stats,BorderLayout.NORTH);
       	hold.add(game,BorderLayout.CENTER);
       	hold.add(options,BorderLayout.SOUTH);
       	hold.pack();
       	hold.setSize(1300,1000);
       	hold.setVisible(true);
     
       	
    }
    
   /* @Override Actionperformed
    * (non-Javadoc)
    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
    */
	public void actionPerformed(ActionEvent in) {
		// TODO Auto-generated method stub
        String comm = in.getActionCommand();	
    
         if(comm.contains(","))
         {
        	 String temp[] = comm.split(",");
        	 int x=Integer.parseInt(temp[0]);
        	 int y=Integer.parseInt(temp[1]);
        	 board.reveal(x, y);
        	 String tempy = Character.toString(board.getCell(x, y)); 
        	 
        	 button[x][y].setText(tempy);
        	 button[x][y].setEnabled(false);
        	
        	 if(board.getCell(x, y) == 'X')
        	 {
        		 bombHit++;
        		 morebombs--;
        	 }else
        	 {
        		 floodfill(x,y);
        	 }
        	 turns++;
        	 syncGui();
        	 
        	 
         }else if(comm == "Normal")
         {
        	 syncGui();
         }
         else if(comm == "Cheat")
         {
        	 cheatBoard();
         }
    	/*if(Integer.parseInt(in.getActionCommand()) >= 0 )
    	{
    	
    	}*/
		
	}
   
	/*
	 * Syncs the GUI to board object
	 */
	public void syncGui()
    {
    	left = ((size*size)-board.getBombs());
    	for(int i =0;i<size;i++)
    	{
    		for(int x =0;x<size;x++)
    		{
    			String label =Character.toString(board.getPlayerCell(i, x));
    			    			button[i][x].setText(label);
    			if(board.getPlayerCell(i, x) != '-')
    			{
    				button[i][x].setEnabled(false);
    				left--;
    			}else
    			{
    				button[i][x].setEnabled(true);
    			}
        	}
    	}
    	moves.setText("Moves Made: "+String.format("%d",turns));
    	bombs.setText("Bombs Hit: "+String.format("%d",bombHit));
    	bombsleft.setText("Bombs left: "+String.format("%d",morebombs));
    	if(bombHit == 3)
    	{
    		gameOver();
    	}
    	if(left == 0)
    	{
    		win();
    	}
    	
    	
    }
/*
 * Replaces all unset cells with D for disarmed   
 */
 private void win()
    {
    	for(int i =0;i<size;i++)
    	{
    		for(int x =0;x<size;x++)
    		{
    			if(button[i][x].isEnabled()==true)
    			{
    				button[i][x].setText("D");
    			}
    			
        	}
    	}
    	moves.setText("You");
    	bombs.setText("WIN");
    	bombsleft.setText("!");
    }
/*
 * displays the full game board   
 */
 private void cheatBoard()
    {
    	for(int i =0;i<size;i++)
    	{
    		for(int x =0;x<size;x++)
    		{
    			String label =Character.toString(board.getCell(i, x));
    			button[i][x].setText(label);
    			button[i][x].setEnabled(false);
    			
    			
        	}
    	}
    }
/*
 * Disables all the cells and Displays "Game Over Meatbag"   
 */
 private void gameOver()

    {
    	for(int i =0;i<size;i++)
    	{
    		for(int x =0;x<size;x++)
    		{
    			 button[i][x].setEnabled(false);
    			
        	}
    	}
    	moves.setText("Game");
    	bombs.setText("Over");
    	bombsleft.setText("Meatbag");
    }
	
 /*
  * Flood fill algorithm
  * @param int x x starting coordinate
  * @param int y y starting coordinate
  */
    private void floodfill(int x, int y)
    {
    	
    	
    	//syncGui();
    	/*if(!button[x][y].isEnabled())
    	{
    		return;
    	}*/
    	
    	int a =x+1;
    	int b= x-1;
    	int c= y-1;
    	int d = y+1;
    	
    if(b >=0 && a < size)
    	    {
    		 	if(board.getCell(a, y) != 'X' && board.getCell(a, y) != 'B' )
    	       	{
    	        board.reveal(a,y);
    	        String tempy = Character.toString(board.getCell(a, y)); 
    	   	 button[a][y].setText(tempy);
    	   	
    	       	}
    	    }
    if(b >=0 && a < size)
    {
    	if(board.getCell(b, y) != 'X' && board.getCell(b, y) != 'B' )
    	{
     board.reveal(b,y);
     String tempy = Character.toString(board.getCell(b, y)); 
	 button[b][y].setText(tempy);
	
     
    	}
    }
    if(d < size && c>= 0)
    {
    	if(board.getCell(x,c) != 'X' && board.getCell(x, c) != 'B' )
    	{
     board.reveal(x,c);
     String tempy = Character.toString(board.getCell(x, c)); 
	 button[x][c].setText(tempy);

    	}
    }
    if(d < size && c>= 0)
    {
    	if(board.getCell(x, d) != 'X' && board.getCell(x, d) != 'B' )
    	{
     board.reveal(x,d);
     String tempy = Character.toString(board.getCell(x, d)); 
	 button[x][d].setText(tempy);
	
    	}
    }
       // if(x < size && x>=0 && y<size && y>= 0 && a < size && b >= 0 && c>= 0 && d <size)
       // {
   if(a < size)
    {
    	
    	//floodfill recursion
    	if(board.getCell(a, y) != 'X' && board.getCell(a, y) != 'B' && button[a][y].isEnabled())
    	{
    		button[a][y].setEnabled(false);
    		floodfill(a,y);
    		
    	}
    }
    //syncGui();
    if(b >=0 )
    {
    	if(board.getCell(b, y) != 'X' && board.getCell(b, y) != 'B'&& button[b][y].isEnabled()  )
    	{
    		button[b][y].setEnabled(false);
    		floodfill(b,y);
    		 
    		 
    	}
    }// syncGui();
    if( c>= 0)
    {
    	if(board.getCell(x,c) != 'X' && board.getCell(x,c) != 'B' && button[x][c].isEnabled())
    	{
    		button[x][c].setEnabled(false);
    		 floodfill(x,c);
    		
    	}
    }  // syncGui();
    if(d < size)
    {
    	if(board.getCell(x, d) != 'X' && board.getCell(x, d) != 'B'&& button[x][d].isEnabled()  )
    	{
    		button[x][d].setEnabled(false);
    		 floodfill(x,d);
    		  
    		 
    	}
    }
    
    
   
    }
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int bombHit,movesMade;
	     int size;
	     boolean keepGoing = true;
	      char hold;
	     MineSweeperBoard m_game = null;
	     MineSweep m_gui;
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
  				 System.out.println("invalid Board size: Game will now exit");
  				 keepGoing = false;
  			 }
			
			 
			 
	      } catch (IOException ioe) {
	         System.err.println("IO error not valid input!");
	         keepGoing = false;
	         } catch(ArrayIndexOutOfBoundsException e)
	         {
	        	 System.err.println("Array index");
	         }
		 
		   		
		 if(keepGoing && m_game != null)
		 {
		 m_gui=new MineSweep(m_game);
		 m_gui.makeGui();
	     }
	     



	}

}
