import java.io.*;
import java.util.*;

class SudokuSolver
{
  private static final int grid_size = 9;
  public static void main(String[] args) 
  {
    int[][] board = new int[][] {
      {7, 0, 2, 0, 5, 0, 6, 0, 0},
      {0, 0, 0, 0, 0, 3, 0, 0, 0},
      {1, 0, 0, 0, 0, 9, 5, 0, 0},
      {8, 0, 0, 0, 0, 0, 0, 9, 0},
      {0, 4, 3, 0, 0, 0, 7, 5, 0},
      {0, 9, 0, 0, 0, 0, 0, 0, 8},
      {0, 0, 9, 7, 0, 0, 0, 0, 5},
      {0, 0, 0, 2 ,0, 0 ,0 ,0 ,0},
      {0, 0, 7, 0, 4, 0, 2, 0, 3}
    };

    if (solveBoard(board))
      System.out.println("Solved Successfully!");
    else
      System.out.println("Unsolvable Board :(");

    printBoard(board);
  }

  private static boolean isNumberInRow(int[][] board, int number, int row)
  {
    for(int i=0; i<grid_size; i++)
    {
      if (board[row][i] == number)
      return true;
    }
    return false;
  }

  private static boolean isNumberInCol(int[][] board, int number, int col)
  {
    for(int i=0; i<grid_size; i++)
    {
      if (board[i][col] == number)
      return true;
    }
    return false;
  }

  private static boolean isNumberInBox(int[][] board, int number, int row, int col)
  {
    int local_box_row = row - (row % 3);
    int local_box_col = col - (col % 3);

    for(int i=local_box_row; i<local_box_row + 3; i++)
    {
      for(int j=local_box_col; j<local_box_col + 3; j++)
      {
        if (board[i][j] == number)
          return true;
      }
    }
    return false;
  }

  private static boolean isValidPlacement(int[][] board, int number, int row, int col)
  {
    return !isNumberInRow(board, number, row) &&
      !isNumberInCol(board, number, col) &&
      !isNumberInBox(board, number, row, col);
  }

  private static boolean solveBoard(int[][] board)
  {
    for(int row=0; row<grid_size; row++)
    {
      for(int col=0; col<grid_size; col++)
      {
        if (board[row][col] == 0)
        {
          for (int number_to_try=1; number_to_try<=grid_size; number_to_try++)
          {
            if (isValidPlacement(board, number_to_try, row, col))
            {
              board[row][col] = number_to_try;
              
              if (solveBoard(board))
                return true;
              else
              {
                board[row][col] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  private static void printBoard(int[][] board)
  {
    for(int row=0; row<grid_size; row++)
    {
      System.out.print("|");
      for(int col=0; col<grid_size; col++)
      {
        System.out.print(" " + board[row][col] + " |");
      }
      System.out.println();

      System.out.println("- - - - - - - - - - - - - - - - - - -");
    }
  }
}
