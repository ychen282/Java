import java.util.Scanner;
import java.io.*;
public class ImageFileUtilities{
  
  public static Image read(String filename) throws IOException{
    Scanner sc = new Scanner(new File(filename));
    String temp = sc.nextLine();
    //create an Image with some initial values and update the Image later
    Pixel[][] readNumber = new Pixel[1][1];
    Image values = new Image(" ", 0, readNumber);
    
    if(temp.equals("P2")){
       while(sc.hasNext("#")){
      temp = sc.nextLine();//skip the comment
      }
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxRange = sc.nextInt();
      //create a 2D Pixel array to store the numbers that form colors
      Pixel[][] numbers = new Pixel[height][width];
      for(int i=0; i<numbers.length; i++){
        for(int j=0; j<numbers[i].length; j++){
          //use the constructor that takes only one input
          numbers[i][j] = new Pixel(sc.nextInt());
        }
      }
      //update Image values by copying tempStore
      values = new Image("Sample test", maxRange, numbers);
    }
    
    if(temp.equals("P3")){
      while(sc.hasNext("#")){
      temp = sc.nextLine();//skip the comment
      }
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxRange = sc.nextInt();
      //create a 2D Pixel array to store the numbers that form colors
      Pixel[][] numbers = new Pixel[height][width];
      for(int i=0; i<numbers.length; i++){
        for(int j=0; j<numbers[i].length; j++){
          //use the constructor that takes three inputs
          int red = sc.nextInt();
          int green = sc.nextInt();
          int blue = sc.nextInt();
          numbers[i][j] = new Pixel(red, green, blue);
        }
      }
      //update Image values by copying tempStore
      values = new Image("Sample test", maxRange, numbers);
    }
    sc.close();
    return values;
  }
  
  //method that writes a pnm file
  public static void writePnm(Image x, String filename) throws IOException{
    FileWriter fw = new FileWriter(filename);//creates a file writer
    BufferedWriter bw = new BufferedWriter(fw);//creates a buffered writer
    
    //write format
    bw.write("P3 \n");
    //write the comment line
    bw.write("#" + "\n");
    //write size
    int height = x.getHeight();
    int width = x.getWidth();
    bw.write(width + " " + height + "\n");
    //write maxrange
    bw.write(255 + "\n");
    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        bw.write(x.getPixel(i, j).getRed() + "\n" + x.getPixel(i, j).getGreen() + "\n" + x.getPixel(i, j).getBlue() + "\n");
      }
      bw.write("\n");
    }
    
    bw.close();
    fw.close();
  }

  //method that writes a pgm file
  public static void writePgm(Image x, String filename) throws IOException{
    FileWriter fw = new FileWriter(filename);//creates a file writer
    BufferedWriter bw = new BufferedWriter(fw);//creates a buffered writer
    
    //write format
    bw.write("P2 \n");
    //write the comment line
    bw.write("#" + "\n");
    //write size
    int height = x.getHeight();
    int width = x.getWidth();
    bw.write(width + " " + height + "\n");
    //write maxrange
    bw.write(255 + "\n");
    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        bw.write(x.getPixel(i, j).grey() + "\n");
      }
      bw.write("\n");
    }
    
    bw.close();
    fw.close();
  }
}