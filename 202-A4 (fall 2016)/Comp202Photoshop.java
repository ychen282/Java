import java.io.*;
public class Comp202Photoshop{
  public static void main(String[] args){
    String inputName = args[0];
    String outputName = args[1];
    String formatOfOutput = args[2];
    String operation = args[3];
    
    //throw an exception and exist when unexpected inputs occur
    //when inputs are incomplete
    if(args.length<4){
      throw new IllegalArgumentException("Incompete input! Numbers of arguments is less than 4.");
    }
    //when output format in invalid
    if(!(formatOfOutput.equals("pgm")||args[2].equals("pnm"))){
      throw new IllegalArgumentException("Invalid input! The output format is invalid.");
    }
    //when operations does not exist
    if(!(operation.equals("-fh")||operation.equals("-fv")||operation.equals("-gs")||operation.equals("-cr"))){
      throw new IllegalArgumentException("Invalid input! The operation does not exist.");
    }
    
    try{
      //load an image
      Image x = ImageFileUtilities.read(inputName);
      //flip an image horizontally
      if(operation.equals("-fh")){
        x.flip(true);
      }
      //flip an image vertically
      if(operation.equals("-fv")){
        x.flip(false);
      }
      //for a greyscale conversion
      if(operation.equals("-gs")){
        x.toGrey();
      }
      //crop an image
      if(operation.equals("-cr")){
        int startX = Integer.parseInt(args[4]);
        int startY = Integer.parseInt(args[5]);
        int endX = Integer.parseInt(args[6]);
        int endY = Integer.parseInt(args[7]);
    
        x.crop(startX, startY, endX, endY);
      }
      
      //choose the correspongding write method
      if(formatOfOutput.equals("pnm")){
        ImageFileUtilities.writePnm(x,outputName);
        }
      if(formatOfOutput.equals("pgm")){
        ImageFileUtilities.writePgm(x,outputName);
        }
    }catch(IOException e){
      e.printStackTrace();
    }
    
  }
}
  
  

