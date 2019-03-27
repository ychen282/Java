public class testA6{

  public static void main(String[] args){
    int C[] = {2,6,8,10};
    int U[] = {1,5,8,9};
    int utility[] = new int[38]; 
    int first[] = new int[38]; //to store the first object chosen    
    for (int i=0; i < 38; i++){
      utility[i] = 0;
      first[i] = -1;
      for (int j=0; j<4; j++){ //first choice has only k type        
        if (i+1>=C[j]&&U[j]+utility[i-(C[j]-1)]>=utility[i]){   
          utility[i] = U[j] + utility[i-(C[j]-1)];     
          first[i] = j;    
        }
      }
    }
//once we find the first object for all the index of utility[], we can then use it to  find Q[0¡­k-1]  
    int Q[] = new int[4];
    int n = 37; 
    while (n>=0){
      int temp = first[n];   //temp should be within 0 to k-1   
      Q[temp] = Q[temp] + 1;   //then we look for what is the first object of utility[n-C[temp]] chose  
      n = n-C[temp];   //continue the loop return Q; 
 
    }
    System.out.print(utility[37]+" ");
    System.out.println();
    for(int i=0; i<4; i++){
      System.out.print(Q[i]+" ");
    }
  }
}