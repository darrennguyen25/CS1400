public class test {
    public static int[][] exampleVariableOne = new int[3][4];
    // returns the length of the rows in the array
    public static int lengthOne = exampleVariableOne.length;
    // returns the length of the columns in the array
    public static int lengthTwo = exampleVariableOne[0].length;
  
    public static void main(String[] args) {
      System.out.println(lengthOne);
      System.out.println(lengthTwo);
    }
}
