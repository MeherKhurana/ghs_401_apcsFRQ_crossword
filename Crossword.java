public Crossword(boolean[][] blackSquares)
{
   int rows = blackSquares.length;
   int cols = blackSquares[0].length;
   puzzle = new Square[rows][cols];
   int label = 1;  // clue numbers start at 1
   
   for(int r = 0; r < rows; r++ )
   {
      for( int c = 0; c < cols; c++ )
      {
         if( blackSquares[r][c] )
         {
            // If the value is true, this square is black.
            puzzle[r][c] = new Square(true, 0);
         }
         else
         {
            // White square: check if it should be labeled.
            if(toBeLabeled(r, c, blackSquares))
            {
               puzzle[r][c] = new Square(false, label);
               label++;   // increment for the next clue
            }
            else
            {
               puzzle[r][c] = new Square(false, 0);
            }
         }
      }
   }
}

private boolean toBeLabeled(int r, int c, boolean[][] blackSquares)
{
   // If the square itself is black, it won't get a label.
   if(blackSquares[r][c])
      return false;
      
   // A white square gets a label if:
   //   - It is in the first row (no square above), or
   //   - It is in the first column (no square to the left), or
   //   - The square immediately above is black, or
   //   - The square immediately to the left is black.
   if(r == 0 || c == 0)
      return true;
      
   if(blackSquares[r-1][c] || blackSquares[r][c-1])
      return true;
      
   return false;
}
