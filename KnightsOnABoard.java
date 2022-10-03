import java.util.Scanner;
import java.io.*;
class KnightsOnABoard{

/***************************************************************  
*  file: KnightsOnABoard.java
*  author: D Nguyen  
*  class: CS 1400 – Programming and Problem Solving  
*  
*  assignment: program 5
*  date last modified: 5/21/2022
*  
*  purpose: This program takes in a file with a game board of 1s and 0s and checks whether it exists or not. 
*  Then, create methods to make a 2D array to represent knights on a chessboard where 1’s represent where a 
*  knight is placed and 0’s represent an empty square. It determines whether a knight can capture another. 
*  
****************************************************************/  

// purpose: this method check whether the file exists or not. If so return the file. 
  public File validateFile(File inputFile){ 
    Scanner scan = new Scanner (System.in);
    String fileName = "";
    while(!inputFile.exists()){
      System.out.println("File does not exist!");
      System.out.println("Please enter the name of a valid file: ");
      fileName = scan.nextLine();
      inputFile = new File (fileName);
    }
    return inputFile;
  }

  /* purpose: checks to see if the given file exists, if the input file does 
     not exist it will give an appropriate error message and ask the user for another 
     name until a valid one is given. It will then return the valid file
  */
  public boolean validateData(File inputFile) throws IOException { 
    Scanner openFile = new Scanner (inputFile);
    Scanner openFile2 = new Scanner (inputFile);
    Scanner openFile3 = new Scanner (inputFile);
    int integers = 0;
    int value = 0;
    String test = "";
    String test2 ="";
    int rows = 0;;
    String [] rowsArray = new String[0];
    int squares = 0;
    while(openFile3.hasNextInt())
    {
        squares++;
        value = openFile3.nextInt();
    }
    while(openFile.hasNextLine()){
      integers++;
      test = openFile.nextLine();
    }
    
    for(int i = 0; i < integers; i++){
      test2 = openFile2.nextLine();
      rowsArray = test2.split("\\s+");
      if(rowsArray.length==8){
        rows++;
      }
    }
    
    if( rows !=8 || integers != 8 || squares != 64){
      return false;
    }
    return true;
  }

  /* purpose: reads through the file that has been validated, then create and 
     populate an 8x8 2D array with the information from the file. Remember to 
     correct any lines of data which are integers but not 1’s or 0’s and print 
     an appropriate message if this is needed. It will then return the created array. 
  */
  public int[][] populateBoard(File inputFile) throws IOException{
    Scanner openFile = new Scanner (inputFile);
    String test ="";
    String test2 ="";
    int pos = 0;
    int neg =0;
    String [][] size = new String [8][8];
    int [][] val = new int [8][8];
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        test = openFile.next();
        test2 = test.replaceAll("\\s", "");
        size[i][j]=test2;
        val[i][j]=Integer.parseInt(size[i][j]);
        if(val[i][j]<0){
          pos++;
          val[i][j]=0;
        }else if( val[i][j]>1){
          neg++;
          val[i][j]=1;
        }
      }
    }
    if(pos > 0){
      System.out.println("Positive values greater than 1 were detected and will be converted to 1");
    }
   if(neg >0  ){
      System.out.println("Negative values smaller than 0 were detected and will be converted to 0");
    }
    return val;
  }

/* purpose: computes if the knights are placed on the chessboard so that no knight
   can capture another knight. It will then return true if no knight can capture any 
   other knight, or false if even one knight can capture another. 
*/ 
   public boolean cannotCapture(int[][] chessBoard){ 
    int [] horizontal = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int [] vertical = { 1, 2, 2, 1, -1, -2, -2, -1 };
    int count = 0;
    int x = 0;
    int y = 0;
    for(int i = 0; i < chessBoard.length; i++){
      for (int j = 0; j < chessBoard[0].length; j++){
        if(chessBoard[i][j]==1){
          for(int k =0; k < 8; k++){
           x = i + horizontal[k];
           y = j + vertical[k];
           if (x >= 0 && y >= 0 && x < 8 && y < 8 && chessBoard[x][y]== 1){
              count++;
          }
          }
        }
      }
    }
      if (count > 1){
        return false;
      }else{
        return true;
      }
  }

// purpose: this method will display the 2D array to the screen. 
  public void printBoard(int[][] chessBoard){  
    System.out.println("The board looks as follows:");
    for(int i = 0; i < 8; i++)
   {
      for(int j = 0; j < 8; j++)
      {
         System.out.print(chessBoard[i][j]);
      }
      System.out.println();
   }
}

/* purpose:Create an instance of our class then it will prompt the user for a file name and loop through 
validateFile and validateData until we can read in and create a valid 2D chess board array with 
populateBoard. It will then use printBoard to display the array and call cannotCapture and print an 
appropriate message to the screen depending on the true or false response. 
*/ 
public static void main(String[] args) throws IOException { 
    KnightsOnABoard kob = new KnightsOnABoard();
    Scanner scan = new Scanner (System.in);
    System.out.println("Please enter the name of a valid file: ");
    String filename = scan.nextLine();
    File input = new File (filename);
    input = kob.validateFile(input);
    boolean boo = kob.validateData(input);
    while(boo==false){
      System.out.println("File has invalid data.");
      System.out.println("Please enter the name of a valid file: ");
      filename = scan.nextLine();
      input = new File (filename);
      input = kob.validateFile(input);
      boo = kob.validateData(input);
    }
    if(boo == true){
      int [][] arr = kob.populateBoard(input);
      kob.printBoard(arr);
      boolean bool = kob.cannotCapture(arr);
      if(bool==false){
        System.out.println("There is at least one knight which can capture another knight.");
      }else{
        System.out.println("No knights can capture any other knight.");
      }
    }
    
  }
}