public class Pixel{
  private int red;
  private int green;
  private int blue;
  
  //constructor that initialize red, green and blue respectively
  public Pixel(int red, int green, int blue){
    if(red>255||red<0||green>255||green<0||blue>255||blue<0){
      throw new IllegalArgumentException("Invalid input!");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  //constructor that input 1 value intensity
  public Pixel(int intensity){
    if(red>255||red<0||green>255||green<0||blue>255||blue<0){
      throw new IllegalArgumentException("Invalid input!");
    }
    this.red = intensity;
    this.green = intensity;
    this.blue = intensity;
  }
  
  //get methods for the values of the 3 properties
  public int getRed(){
    return this.red;
  }
  
  public int getGreen(){
    return this.green;
  }
  
  public int getBlue(){
    return this.blue;
  }
  
  //method that return the average of the 3 properties
  public int grey(){
      return (int)(0.3*this.red + 0.59*this.green + 0.11*this.blue);
  }
}