import java.io.*; 
import java.util.Scanner;
/** 
 *Extends the board class for MineSweeper
 * @author      Daniel Pimienta 
 * */

public class MineSweeperBoard extends Board
{
    char[][] m_playerBoard;
    String file = "";
     int bombCount = 0;
	public MineSweeperBoard(int size, String file)
	{
	super(size);	
	 m_playerBoard = new char[size][size];
		clear();
		this.file += file;
		writeBoard();
	}

	/*private void syncBoards()
	{
		m_playerBoard = super.getBoard().clone();
	}*/
	 public void setPlayerCell(int f, int g, char v)
	 {
		 try
		 {
		 m_playerBoard[f][g] = v;
		 }catch(ArrayIndexOutOfBoundsException j)
			{
				System.err.println("invalid Coordinates selected");
			}
	 }
	 
	 /*
	  * Reads in the bombs from the file and places them in the board
	  * @see Board#writeBoard()
	  */
	public void writeBoard()
	{
		try
		{
			int f = 99;
			int g= 99;
			
			Scanner sc = new Scanner(new File(file));
			sc.useDelimiter("\r\n");
			while (sc.hasNextLine()) 
			{
				String token = sc.nextLine();
				//System.out.println(token);
				token = token.replaceAll("\\s+", "");
				//token.replaceAll("\\n", "");
				String[] hold = token.split(",");
				//System.out.println(hold[0]+" "+hold[1]);
		       if(hold.length == 2 && hold.length < 3 && token != "" )
		    	f =  Integer.parseInt(hold[0]);
		    	g = Integer.parseInt(hold[1]);
		    
		    if( f <size && g< size && f >=0 && g>= 0)
		    {
		    	if(getCell(f,g) != 'B' )
		    	{
		    		bombCount++;
		    		setCell(f,g, 'B');
				    increment(f,g);
		    	}
		    }else 
	    	{
		    	System.err.println("Invalid Bomb format: game will now exit");
		         System.exit(0);
		    }
		    	
		    }
		    sc.close();
			
			
			  
		}catch(FileNotFoundException e)
		{
		System.err.println("File Not found" + file);
		}
	}
	/*
	 * returns number of Bombs
	 * @return bombcount
	 */
	public int getBombs()
	{
		return bombCount;
	}
	
	/*
	 * sets board to player board cells to '-'
	 */
	public void clear()
	{
		
		  for(int x =0;x<size;x++)
		  {
			  for(int y =0;y<size;y++)
			  {
				m_playerBoard[x][y] = '-';
			  }
		  }
	}
	/*
	 * to String for the playerboard
	 * @return string representation of the player board
	 */
	public String showPlayerBoard()
	{
		 String out = new String();
		  for(int x =0;x<size;x++)
		  {
			  for(int y =0;y<size;y++)
			  {
				 out += m_playerBoard[x][y]+ " ";
			  }
			  out += "\n";
		  }
		 
		 return out;
	}
	
	/*
	 * Any cells touching a bomb display how many bombs are adjacent to the cell
	 */
	private void increment(int f, int g)
	{
		int a =f+1;
    	int b= f-1;
    	int c= g-1;
    	int d = g+1;
		try
		{
	if(a<size)
	{
		if(getCell(f+1,g) == '0')
		{
			setCell(f+1,g,'1');
			
		}else if((int) getCell(f+1,g) >= 1 && getCell(f+1,g)!= 'B' )
		{
			int num =  Character.getNumericValue(getCell(f+1,g));
			num =num+48;
			char hold = (char)(++num );
			setCell(f+1,g,hold);
		}//one right
		
	}
	if(b>=0)
	{
		if(getCell(f-1,g) == '0')
		{
			setCell(f-1,g,'1');
			
		}else if((int) getCell(f-1,g) >= 1  && getCell(f-1,g)!= 'B')
		{
			int num =  Character.getNumericValue(getCell(f-1,g));
			num =num+48;
			char hold = (char)(++num );
			setCell(f-1,g,hold);
		}//one left
	}
	if(d<size)
	{
		if(getCell(f,g+1) == '0')
		{
			setCell(f,g+1,'1');
			
		}else if((int) getCell(f,g+1) >= 1  && getCell(f,g+1)!= 'B' )
		{
			int num =  Character.getNumericValue(getCell(f,g+1));
			num =num+48;
			char hold = (char)(++num );
			setCell(f,g+1,hold);
		}//one up
	}
	if(c>=0)
	{
		if(getCell(f,g-1) == '0')
		{
			setCell(f,g-1,'1');
			
		}else if((int) getCell(f,g-1) >= 1  && getCell(f,g-1)!= 'B')
		{
			int num =  Character.getNumericValue(getCell(f,g-1));
			num =num+48;
			char hold = (char)(++num );
			setCell(f,g-1,hold);
		}//one down
	}
	if(a<size && d<size)
	{
		if(getCell(f+1,g+1) == '0')
		{
			setCell(f+1,g+1,'1');
			
		}else if((int) getCell(f+1,g+1) >= 1  && getCell(f+1,g+1)!= 'B')
		{
			int num =  Character.getNumericValue(getCell(f+1,g+1));
			num =num+48;
			char hold = (char)(++num );
			setCell(f+1,g+1,hold);
		}//top right corner
	}
	if(b >= 0 && c>= 0)
	{
		if(getCell(f-1,g-1) == '0')
		{
			setCell(f-1,g-1,'1');
			
		}else if((int) getCell(f-1,g-1) >= 1 && getCell(f-1,g-1)!= 'B')
		{
			int num =  Character.getNumericValue(getCell(f-1,g-1));
			num =num+48;
			char hold = (char)(++num );
			setCell(f-1,g-1,hold);
		}//bottom left corner
	}
	if(a<size && c>=0)
	{
		if(getCell(f+1,g-1) == '0')
		{
			setCell(f+1,g-1,'1');
			
		}else if((int) getCell(f+1,g-1) >= 1 && getCell(f+1,g-1)!= 'B')
		{
			int num =  Character.getNumericValue(getCell(f+1,g-1));
			num =num+48;
			char hold = (char)(++num );
			setCell(f+1,g-1,hold);
		}//bottom right corner
	}
	if(b>= 0 && d<size)
	{
		if(getCell(f-1,g+1) == '0')
		{
			setCell(f-1,g+1,'1');
			
		}else if((int) getCell(f-1,g+1) >= 1 && getCell(f-1,g+1)!= 'B')
		{
			int num =  Character.getNumericValue(getCell(f-1,g+1));
			num =num+48;
			char hold = (char)(++num );
			setCell(f-1,g+1,hold);
		}//top left corner
	}
		}catch(ArrayIndexOutOfBoundsException e)
		{
			System.err.println("array out of bounds for increment");
		}
		
	}
	/*
	 * This implemented the fire method for the text GUI
	 * @param f x coordinate
	 * @param g y coordinate
	 */
	public char reveal(int f,int g)
	{ 
	  
		char out = getCell(f,g);
		
		if(out == '0')
		{
			
			
			m_playerBoard[f][g] = out;
		 // openAdjacent(f,g);
			
			//lineCheck(f,g,x);
			
		}else if(out == 'B')
		{
			out = 'X';
			setCell(f,g,out);
			m_playerBoard[f][g] = getCell(f,g);
		}else
		{
			m_playerBoard[f][g] = out;
		}
		
		return  out;
	
	 
	}
	/*
	 * returns the character in the player board at cell f,g
	 */
	public char getPlayerCell(int f,int g)
	{ 
		char out ='@';
		try
	 {
		out= m_playerBoard[f][g];

	 }catch(ArrayIndexOutOfBoundsException j)
		{
			System.err.println("invalid Coordinates selected");
		}
		return out;
	}
	
	/*
	 * @returns true if the player board equals '-' @ f,g
	 */
	
	private boolean isEmpty(int f, int g)
	{
		boolean out = false;
		char cheat = getCell(f,g);
		char player = getPlayerCell(f,g);
		if((cheat != '0' && player != '-')|| cheat == 'f'||cheat == '@' || player == '@')
		{
			out = false; 
		}else
		{
			out = true;
		}
		return out;
	}

/*
 * flowfill algorithm for the text gui
 */
public void openAdjacent(int f,int g)
{ 
	if(f>=size || g>=size || f<0 ||g<0)
	{
		return;
	}else
	{

if(isEmpty(f,g)&& f < size && g<size)
{
	setPlayerCell(f,g,getCell(f,g));


	if(isEmpty(f+1,g) && (f+1) < size )
	{
		openAdjacent(f+1,g);
	}

	if(isEmpty(f,g+1) && g+1 < size)
	{
		openAdjacent(f,g+1);
	}
	
	if(isEmpty(f-1,g) && f-1 >= 0)
	{
		openAdjacent(f-1,g);
	}
	if(isEmpty(f,g-1) && g-1 >=0)
	{
		openAdjacent(f,g-1);
	}
	/*if(isEmpty(f+1,g+1))
	{
		openAdjacent(f+1,g+1);
	}
	if(isEmpty(f+1,g-1))
	{
		openAdjacent(f+1,g-1);
	}
	if(isEmpty(f-1,g+1))
	{
		openAdjacent(f-1,g+1);
	}
	if(isEmpty(f-1,g-1))
	{
		openAdjacent(f-1,g-1);
	}*/
	
}
	}
	
	
		     
	           
	  
	
	/*if(getCell(f+1,g) != 'B' && getCell(f+1,g) != 'f' && getCell(f+1,g) != '@'&& reveal(f+1,g) != '0' )
	{
		if(f+1 < size  && g < size && g >=0 )
		{
			x++;
		setPlayerCell(x,g,getCell(x,g));
		openAdjacent(x,g);
		}
		
	}//one right
	
	if(getCell(f-1,g) != 'B' && getCell(f-1,g) != 'f' && getCell(f-1,g) != '@'  )
	{
		if(f-1 > 0 && g >=0 && g<size )
		{
		setPlayerCell(f-1,g,getCell(f-1,g));
		
		openAdjacent(f-1,g);
		}
	}//one left
	
	if(getCell(f,g+1) != 'B')
	{
		if(f+1 < size -1 && g+1 < size -1 && f-1 > 0 && g-1 >=0 )
		{
		setPlayerCell(f,g+1,getCell(f,g+1) );
		
		openAdjacent(f,g+1);
		}
	}//one up
	
	if(getCell(f,g-1) != 'B')
	{
		if(f+1 < size -1 && g+1 < size -1 && f-1 > 0 && g-1 >=0 )
		{
		setPlayerCell(f,g-1,getCell(f,g-1));
		
		openAdjacent(f,g-1);
		}
	}//one down
	
	if(getCell(f+1,g+1) != 'B')
	{
		if(f+1 < size -1 && g+1 < size -1 && f-1 > 0 && g-1 >=0 )
		{
		setPlayerCell(f+1,g+1,getCell(f+1,g+1));
		
		openAdjacent(f+1,g+1);
		}
	}//top right corner
	
	if(f+1 < size -1 && g+1 < size -1 && f-1 > 0 && g-1 >=0 )
	{
		if(f-1 > 0 && g-1 > 0 )
		{
		setPlayerCell(f-1,g-1,getCell(f-1,g-1));
		
		openAdjacent(f-1,g-1);
		}
	}//bottom left corner
	
	if(getCell(f+1,g-1) != 'B')
	{
		if(f+1 < size -1 && g+1 < size -1 && f-1 >= 0 && g-1 >=0 )
		{
		setPlayerCell(f+1,g-1,getCell(f+1,g-1));
		
		openAdjacent(f+1,g-1);
		}
	}//bottom right corner
	
	if(getCell(f-1,g+1) != 'B')
	{
		if(f+1 < size -1 && g+1 < size -1 && f-1 >= 0 && g-1 >=0 )
		{
		setPlayerCell(f-1,g+1,getCell(f-1,g+1));
		
		openAdjacent(f-1,g+1);
		}
	}*/ //top left corner
}

}