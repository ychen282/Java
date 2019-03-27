public class Image{
  private String metadata;
  private int maxRange;
  private Pixel[][] data;
  
  //constructor that takes values for all of the attributes
  public Image(String metadata, int maxRange, Pixel[][] data){
    //throw an exception if the maxRange is negative
    if(maxRange<0){
      throw new IllegalArgumentException("Invalid input! maxRange can not be negative.");
    }
    this.metadata = metadata;
    this.maxRange = maxRange;
    //copy the input Pixel[][] array and its sub-arrays
    Pixel[][] arrayCopy = new Pixel[data.length][data[0].length];
    for(int i=0; i<data.length; i++){
      for(int j=0; j<data[i].length; j++){
        arrayCopy[i][j] = data[i][j];
      }
    }
    this.data = arrayCopy;
  }
  
  //get methods for properties 
  public String getMetadata(){
    return this.metadata;
  }
  
  public int getMaxRange(){
    return this.maxRange;
  }
  
  //get methods for width and height
  public int getWidth(){
    return this.data[0].length;
  }
  
  public int getHeight(){
    return this.data.length;
  }
  
  //get method for Pixel in the array
  public Pixel getPixel(int i, int j){
    return this.data[i][j];
  }
  
  //method that flips an image either vertically or horizontally
  public void flip(boolean horizontal){
    //create a new 2D array, populate it with the flipped values
    Pixel[][] afterFlip = new Pixel[this.data.length][this.data[0].length];
    if(horizontal){
      for(int i=0; i<this.data.length; i++){
        for(int j=0; j<this.data[0].length; j++){
          afterFlip[i][j] = this.data[i][this.data[0].length-1-j];
        }
      }
      //update the 2D Pixel of the calling image
      this.data = afterFlip;
    }
    else{
      for(int j=0; j<this.data[0].length; j++){
        for(int i=0; i<this.data.length; i++){
         afterFlip[i][j] = this.data[data.length-1-i][j];
        }
      }
      //update the 2D Pixel of the calling image
      this.data = afterFlip;
    }
  }
  
  //method that turns a color Image into a grey scale one
  public void toGrey(){
    for(int i=0; i<data.length; i++){
      for(int j=0; j<data[0].length; j++){
        //updates the pixels of the calling image
        this.data[i][j].grey();
      }
    }
  }
  
  //method that crops a rectangular section of the original Image
  public void crop(int startX, int startY, int endX, int endY){
    //throw an exception if the input arguments are invalid
    if(startX<0||startX>this.data[0].length||startY<0||startY>this.data.length||
      endX<0||endX>data[0].length||endY<0||endY>this.data.length||startX>endX||startY>endY){
      throw new IllegalArgumentException("Invalid input!");
    }
    Pixel[][] cropImage = new Pixel[endY-startY][endX-startX];
    for(int i=0; i<(endY-startY); i++){
      for(int j=0; j<(endX-startX); j++){
        cropImage[i][j] = this.data[startY+i][startX+j];
      }
    }
    //updates the Pixels array of the calling image
    this.data = cropImage;
    
  }
}