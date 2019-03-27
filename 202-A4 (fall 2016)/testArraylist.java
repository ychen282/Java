import java.util.ArrayList;
public class testArraylist{
  public static void main(String[] args){
    ArrayList <Integer> d = new ArrayList<Integer>();
    System.out.println(d);
    System.out.println(testMethod(Integer.parseInt(args[0])));
  }
  
  public static int testMethod(int x){
      try{
      int[] array = new int[2];
      array[0] = 0;
      if(x>2){
        throw new ArithmeticException();
      }
      System.out.println(x/array[0]);
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("You caught it!");
    }
    catch(ArithmeticException e){
      System.out.println("caught!");
    }
    return x+x;
  }
}