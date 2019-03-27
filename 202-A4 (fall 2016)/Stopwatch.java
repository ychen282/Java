import java.util.Scanner;

public class Stopwatch{
  private boolean running = false;
  private Time startTime;
  public void startStop(){
    if(this.running == false){
      this.running = true;
      this.startTime = getCurrentTime1();
    }
    else{
      this.running = false;
      int currentTime = getCurrentTime2().getHour()*3600 + getCurrentTime2().getMinute()*60 + getCurrentTime2().getSecond();
      int initialTime = this.startTime.getHour()*3600+this.startTime.getMinute()*60+this.startTime.getSecond();
      System.out.println(currentTime-initialTime+" seconds have elapsed.");
    }
  }
  
  
  public static void main(String[] args){
    Stopwatch watch = new Stopwatch();
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter any String to stop the Stopwatch.");
    watch.startStop();
    String s = sc.nextLine();
    sc.close();
    watch.startStop();
  }
  
    public static Time getCurrentTime1(){
    Time x = new Time(1,2,3);
    return x;
  }
  
  public static Time getCurrentTime2(){
    Time y = new Time(2,3,4);
    return y;
  }
}