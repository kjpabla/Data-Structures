import java.util.*;
import java.io.*;

/* 
 * Name: (Karanjot Pabla)
 *
 * This program applies the conditions present in 
 * Conway's Game of Life and makes it possible to
 * load an array of cells (integers) via text file
 * either dead or alive (0 or 1) and iterate them based
 * on the rules apart of Conway's Game of Life depending upon number
 * of iterations user demands.
 */
public class LifeGame {
  private String filename; // Text file to be loaded containing the data
  private int [][] position; // Initial array
  
  private int input; // User input to indicate number of iterations to be done
  private int countNeighbors; // Counts number of live neighbors around a cell
  private int getCell; // Retrieves value of array cell (either 1 or 0)
 
  public LifeGame(){ 
  Scanner console; 
  print("Enter game filename: ");
  console = new Scanner(System.in); //The scanner will now take in anything using System.in
  String filename = console.next(); //Set file to be read by scaner
  loadState(filename); //Loads the file using method loadState()
  //System.out.println("# of iterations (<1 to stop):");
  /* The following for-loop takes in the value the user inputs and if it is greater 
   * than or equal to 1, it iterates the array based upon the value the user inputs,
   * if input is less than 1, it returns the most recent array
  */
  for(;;){
    System.out.println("# of iterations (<1 to stop):");
    input = console.nextInt();
    if(input >= 1){
      print("Final Game State:");
      iterate(input);
    }
    if(input < 1){
    print("Final Game State:");
    System.out.println();
    display(position);
    break; 
    }// display most recent array
   }
  console.close();
 }
 
  /* The following method, using rows and columns of the initial array
   * counts how many live neighbors are present around the cell
   * being looked (getCell).
   */
  private int countNeighbors(int row, int col){
  int count=0;
  /* if cell (at position row, col) equals 1, then add to overall count,
   * this happens at every if statement in this method
   */
    if(getCell(row-1,col-1) == 1){ 
      count++; //add 1 to count
    }
    if(getCell(row-1,col) == 1){
      count++;
    }
    if(getCell(row-1,col+1) == 1){
      count++;
    }
   if(getCell(row,col-1) == 1){
     count++;
       }
   if(getCell(row,col+1) == 1){
     count++;
       }
   if(getCell(row+1,col-1) == 1){
     count++;
       }
   if(getCell(row+1,col) == 1){
     count++;
       }
   if(getCell(row+1,col+1) == 1){
      count++;
       }
     return count; //return count of neighbors that are alive (equal to 1)
  }
  
  
  /* The following method retrieves value of the cell at certain position in array*/
  private int getCell(int row, int col){
    if(row < 0){
     row = position.length+row; /* If row number is less than zero, it adds it to
                                * length of array and sets it equal to such, 
                                * understanding that we are using the wrapping concept */
    }
    else if(row >=position.length){
      row = row%position.length; /*Otherwise, if  row is greater than or equal to length of array, row is equal to
                                  *modulus (remainder of) array length*/
    }
    if(col < 0){
     col = position[row].length+col; /* If col number is less than zero, it adds it to
                                      * length of array and sets it equal to such, 
                                      * understanding that we are using the wrapping concept */
    }
    else if(col >=position[row].length){
      col = col%position[row].length; /*Otherwise, if  col is greater than or equal to length of array's row,
                                       *row is equal to modulus (remainder of) array's row length*/
    }
     int val = position[row][col]; //sets value equal to the position in the array
     return val;                   //Returns the value of the cell
  }
  
  
  // The following method loads the initial array of the file being read
 private void loadState(String filename) {
  Scanner file;
  // The following try and catch sequence loads file, if not found, presents "FileNotFoundException"
  try {
      file = new Scanner(new File(filename));
      int row = file.nextInt(); //Next integer in file will indicate value for row
      int col = file.nextInt(); //Next integer in file will indicate value for col
      print("Dimensions: " +row+ " rows x " +col+ " columns ");
      System.out.println();
      System.out.println("Initial Game State: ");
      file.nextLine();
      System.out.println();
      position = new int[row][col];
      /*The following nested for loops goes through every cell for length of array and prints them,
      then it goes to next row and does the same until all is read*/
      for( int m=0; m<position.length; m++){
      for(int n=0; n<position[m].length; n++){
       position[m][n] = file.nextInt();
       System.out.print(position[m][n]);
       }
      System.out.println();
     file.nextLine();
     }
    file.close();
   }
  catch (FileNotFoundException exn) {
    System.out.println("Error: File unable to be opened" +exn.getMessage());
  }
 }
 
 
 private void print(String msg) {
  System.out.print(msg);
 }
 
 
 /*The following method takes the initial array (position[][]) and iterates it accordingly
  * to the rules present in Conway's Game of Life and the number of iterations the user requests (from input),
  * and then displays it on a new array it creates called array
  */
 private void iterate (int input2) {
   int[][] array = new int[position.length][position[0].length]; //Creates new array using initial array
   
   for(int j=0; j<input2; j++){ /*This for loop tells how many times the proceeding for loop (containing rules) 
                                * occurs based upon input from user*/
  for( int m=0; m<position.length; m++){
      for(int n=0; n<position[m].length; n++){
        if(getCell(m,n) == 0 && countNeighbors(m,n) == 3 ){ //If cell is dead and amount of neighbors is 3;
       array[m][n] = 1; //cell becomes alive
   }
       else if(getCell(m,n) == 1 && countNeighbors(m,n) == 2 || countNeighbors(m,n) == 3){ //If cell is living and amount of neighbors is 2;
       array[m][n] = position[m][n]; //cell stays the same
   }
       else if(getCell(m,n) == 1 && countNeighbors(m,n) == 1 || countNeighbors(m,n) == 0){ //If cell is living and amount of neighbors is 1;
       array[m][n] = 0; //cell dies
   }
       else if(getCell(m,n) == 1 && countNeighbors(m,n) >= 4){ //If cell is living and amount of neighbors is 4;
       array[m][n] = 0; //cell dies
   }
       else{
         array[m][n] = position[m][n];
       }
      }
  }
      for(int m=0; m<array.length; m++){
      for(int n=0; n<array[m].length; n++){
       position[m][n] = array[m][n];
       }
     }
   }
           System.out.println();
           display(array);
 
    //The following nested for loops take in the new array and sets it equal to the old array
 /* for(int m=0; m<array.length; m++){
      for(int n=0; n<array[m].length; n++){
       position[m][n] = array[m][n];
       }
     }*/
 }
 
 private void display(int[][] array){
   //The following nested for loops take in the new array and prints the iterated version 
     for( int m=0; m<array.length; m++){
      for(int n=0; n<array[m].length; n++){
       System.out.print(array[m][n]);
       }
      System.out.println();
     }
 }
 
 
 public static void main(String args[]) {new LifeGame();}
}
