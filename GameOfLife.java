/***************************************************************  
*  file: GameOfLife.java  
*  author: D. Nguyen 
*  class: CS 1400 – Intro Programming and Problem Solving  
*  
*  assignment: program 6  
*  date last modified: 5/21/2022
*  
*  purpose: This program is recreating Conway's Game of Life by prompting the user for
*           a filename and how many generations to compute. It then prints them out in
*           form of ones and zeros. 
*  
****************************************************************/  
 
import java.util.*;
import java.lang.*;
import java.io.*;

public class GameOfLife {
    private int rows;
    private int columns;
    private char[][] gameBoard;
    private int[][] gameBoard_int;
    private static int generations;
    
    //The getRows method returns the number of rows in the game board.
    public int getRows() {
        return rows;
    }
    
    //purpose: The getColumns method returns the number of columns in the game board.
    public int getColumns() {
        return columns;
    }

    //purpose: The default constructor GameOfLife initializes a new game board by prompting the user for the file name and loading the game board data from the file.
    public GameOfLife() {
        String fileName;
        File file;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter input file name: ");
        fileName = in.nextLine();
        System.out.print("Enter how many generations to compute: ");
        generations = in.nextInt();
        System.out.println();
        file = new File(fileName);
        try {
            in = new Scanner(file);
            this.rows = in.nextInt();
            this.columns = in.nextInt();
            this.gameBoard = new char[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    this.gameBoard[i][j] = in.next().charAt(0);
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            System.exit(0);
        }
    }

    //The getCell method gets the value of the cell at a given column and row, returning 0 if either the column or the row is outside the bounds of the game board. 
    public int getCell(int row, int column) {
        if (row < 0 || row > (this.rows - 1) || column < 0 || column > (this.columns - 1)) {
            return '0';
        }
        return this.gameBoard[row][column];
    }

    //The setCell method sets the value of the cell at a given column and row.
    public void setCell(int column, int row, int value) {
        this.gameBoard[row][column] = (char) value;
    }

    //purpose: The print method prints out the board to the console.
    public void print() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * purpose: The computeNextGeneration method creates a temporary 2D array to compute the next iteration of the baord containing the next gernation of organisms. 
     * It then updates the board to represent the next generation. The argument passed in represents the number of generations the user wants to compute. The method 
     * recursively calls tiself and decrements the integer until it terminates when there are no more generations left to compute. 
     * */
    public void computeNextGeneration(int generation) {
        char[][] tempBoard = new char[this.rows][this.columns];
        int neighbourCount;
        if (generation == 1) {
            System.out.println("Generation 1\n");
            print();
            System.out.println();
            return;
        }
        computeNextGeneration(generation - 1);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                neighbourCount = countNeighbours(i, j);
                if (gameBoard[i][j] == '0') {
                    if (neighbourCount == 3) {
                        tempBoard[i][j] = '1';
                    } else {
                        tempBoard[i][j] = gameBoard[i][j];
                    }
                } else {
                    if (neighbourCount < 2 || neighbourCount > 3) {
                        tempBoard[i][j] = '0';
                    } else {
                        tempBoard[i][j] = gameBoard[i][j];
                    }
                }
            }
        }
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                gameBoard[i][j] = tempBoard[i][j];
            }
        }
        System.out.println("Generation " + generation + "\n");
        print();
        System.out.println();

    }

    /*method (helper)
    *purpose: The countNeigbors helper method returns the number of neighbors on the game board that are diagonally, vertically, or horizontally adjacent.
    */ 
    public int countNeighbours(int row, int column) {
        int count = 0;
        if (getCell(row - 1, column - 1) != '0') {
            count++;
        }
        if (getCell(row - 1, column) != '0') {
            count++;
        }
        if (getCell(row - 1, column + 1) != '0') {
            count++;
        }
        if (getCell(row, column - 1) != '0') {
            count++;
        }
        if (getCell(row, column + 1) != '0') {
            count++;
        }
        if (getCell(row + 1, column - 1) != '0') {
            count++;
        }
        if (getCell(row + 1, column) != '0') {
            count++;
        }
        if (getCell(row + 1, column + 1) != '0') {
            count++;
        }
        return count;
    }

    //purpose: The main method creates a GameOfLife object. It prompts the user for how many generations to compute and uses that number to call on computeNextGenerations method.
    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife();
        gol.computeNextGeneration(generations);
    }
}