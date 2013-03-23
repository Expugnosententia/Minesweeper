/** 
 * board super class 
 * @author      Daniel Pimienta 
 * */
public abstract class Board {
   char[][] m_Array;
   int size;
   /*
    * Board constructor
    * @param size the size of the  board
    */
	public Board(int size)
	{
	  m_Array = new char[size][size];
	  this.size = size;
	  reset();
	}
	/*
	 * sets the entire board back to 0s
	 */
public void reset()
{
  for(int x =0;x<size;x++)
  {
	  for(int y =0;y<size;y++)
	  {
		  m_Array[x][y] = '0';
	  }
  }

}
/*
 * returns the character representation of the cell at f,g
 */
public char getCell(int x, int y)
{
	//if(x < size && y<size)
	//{
	char out = '@';
	try
	{
		out =  m_Array[x][y];

	}catch(ArrayIndexOutOfBoundsException f)
	{
	System.err.println("invalid Coordinates selected "+x+","+y);
	out = 'f';
	}
  return out;
	//}
}
/*
 * sets the cell with the given character c @ f,g 
 */
public void setCell(int x, int y, char c)
 {
	 m_Array[x][y] = c;
 }
 public abstract void writeBoard();
 
 /*
  * returns a toString of the Board
  * (non-Javadoc)
  * @see java.lang.Object#toString()
  */
 public String toString()
 {
	 String out = new String();
	  for(int x =0;x<size;x++)
	  {
		  for(int y =0;y<size;y++)
		  {
			 out += m_Array[x][y]+ " ";
		  }
		  out += "\n";
	  }
	 
	 return out;
 }
 /*
  * returns a 2d character array of the board
  */
 public char[][] getBoard()
 {
	 char[][] out = new char[size][size];
	  for(int x =0;x<size;x++)
	  {
		  for(int y =0;y<size;y++)
		  {
			out[x][y] = m_Array[x][y];
		  }
	  }
     return out; 
 }
 /*
  * returns the size of the board
  */
 public int getSize()
 {
 return size;
 }
 
 
 

}
