import java.lang.Math.*;

class ExpressionTree {
    private String value;
    private ExpressionTree leftChild, rightChild, parent;
    
    ExpressionTree() {
        value = null; 
        leftChild = rightChild = parent = null;
    }
    
    // Constructor
    /* Arguments: String s: Value to be stored in the node
                  ExpressionTree l, r, p: the left child, right child, and parent of the node to created      
       Returns: the newly created ExpressionTree               
    */
    ExpressionTree(String s, ExpressionTree l, ExpressionTree r, ExpressionTree p) {
        value = s; 
        leftChild = l; 
        rightChild = r;
        parent = p;
    }
    
    /* Basic access methods */
    String getValue() { return value; }

    ExpressionTree getLeftChild() { return leftChild; }

    ExpressionTree getRightChild() { return rightChild; }

    ExpressionTree getParent() { return parent; }


    /* Basic setting methods */ 
    void setValue(String o) { value = o; }
    
    // sets the left child of this node to n
    void setLeftChild(ExpressionTree n) { 
        leftChild = n; 
        n.parent = this; 
    }
    
    // sets the right child of this node to n
    void setRightChild(ExpressionTree n) { 
        rightChild = n; 
        n.parent=this; 
    }
    

    // Returns the root of the tree describing the expression s
    // Watch out: it makes no validity checks whatsoever!
    ExpressionTree(String s) {
        // check if s contains parentheses. If it doesn't, then it's a leaf
        if (s.indexOf("(")==-1) setValue(s);
        else {  // it's not a leaf

            /* break the string into three parts: the operator, the left operand,
               and the right operand. ***/
            setValue( s.substring( 0 , s.indexOf( "(" ) ) );
            // delimit the left operand 2008
            int left = s.indexOf("(")+1;
            int i = left;
            int parCount = 0;
            // find the comma separating the two operands
            while (parCount>=0 && !(s.charAt(i)==',' && parCount==0)) {
                if ( s.charAt(i) == '(' ) parCount++;
                if ( s.charAt(i) == ')' ) parCount--;
                i++;
            }
            int mid=i;
            if (parCount<0) mid--;

        // recursively build the left subtree
            setLeftChild(new ExpressionTree(s.substring(left,mid)));
    
            if (parCount==0) {
                // it is a binary operator
                // find the end of the second operand.F13
                while ( ! (s.charAt(i) == ')' && parCount == 0 ) )  {
                    if ( s.charAt(i) == '(' ) parCount++;
                    if ( s.charAt(i) == ')' ) parCount--;
                    i++;
                }
                int right=i;
                setRightChild( new ExpressionTree( s.substring( mid + 1, right)));
        }
    }
    }


    // Returns a copy of the subtree rooted at this node... 2014
    ExpressionTree deepCopy() {
        ExpressionTree n = new ExpressionTree();
        n.setValue( getValue() );
        if ( getLeftChild()!=null ) n.setLeftChild( getLeftChild().deepCopy() );
        if ( getRightChild()!=null ) n.setRightChild( getRightChild().deepCopy() );
        return n;
    }
    
    // Returns a String describing the subtree rooted at a certain node.
    public String toString() {
        String ret = value;
        if ( getLeftChild() == null ) return ret;
        else ret = ret + "(" + getLeftChild().toString();
        if ( getRightChild() == null ) return ret + ")";
        else ret = ret + "," + getRightChild().toString();
        ret = ret + ")";
        return ret;
    } 


    // Returns the value of the expression rooted at a given node
    // when x has a certain value
    double evaluate(double x) {
 // WRITE YOUR CODE HERE
      
      //base case is when the node reaches the last layer 
      //base case 1: the node value is the variable x
      if(value.equals("x"))
        return x;
      //base case 2: the node value is a number
      if(!(value.equals("add")||value.equals("minus")||value.equals("mult")||value.equals("sin")||value.equals("cos")||value.equals("exp")))
        return Double.parseDouble(value);
      //otherwise the node value must be an operator
      //do the calculation as well as recursion
      if(value.equals("add"))
        return leftChild.evaluate(x) + rightChild.evaluate(x);
      if(value.equals("minus"))
        return leftChild.evaluate(x) - rightChild.evaluate(x);
      if(value.equals("mult"))
        return leftChild.evaluate(x) * rightChild.evaluate(x);
      if(value.equals("sin"))
        return leftChild.evaluate(Math.sin(x));
      if(value.equals("cos"))
        return Math.cos(leftChild.evaluate(x));
      if(value.equals("exp"))
        return leftChild.evaluate(Math.exp(x));
      
      return 0;
    }                                                 

    /* returns the root of a new expression tree representing the derivative of the
       original expression */
    ExpressionTree differentiate() {
 // WRITE YOUR CODE HERE
      
      //create a new tree representing the differentiate tree that is to be returned
      ExpressionTree diffTree = new ExpressionTree();
    
      //base case 1: the node value is the variable x
      if(value.equals("x"))
        return new ExpressionTree("1");
      //base case 2: the node value is a number
      if(!(value.equals("add")||value.equals("minus")||value.equals("mult")||value.equals("sin")||value.equals("cos")||value.equals("exp")))
        return new ExpressionTree("0");
      
      //otherwise, the node value must be an operator
      //construct the differentiate tree by applying the differentiate rules and doing the recursion call
      if(value.equals("add")){
        diffTree.setValue("add");
        diffTree.setLeftChild(this.leftChild.differentiate());
        diffTree.setRightChild(this.rightChild.differentiate());     
      }
      
      if(value.equals("minus")){
        diffTree.setValue("minus");
        diffTree.setLeftChild(this.leftChild.differentiate());
        diffTree.setRightChild(this.rightChild.differentiate());
      }
  
      if(value.equals("mult")){
        diffTree.setValue("add");
        diffTree.setLeftChild(new ExpressionTree("mult"));
        diffTree.setRightChild(new ExpressionTree("mult"));
        diffTree.leftChild.setLeftChild(this.leftChild.differentiate());
        diffTree.leftChild.setRightChild(this.rightChild);
        diffTree.rightChild.setLeftChild(this.leftChild);
        diffTree.rightChild.setRightChild(this.rightChild.differentiate());
      }
        
      if(value.equals("sin")){
        diffTree.setValue("mult");
        diffTree.setLeftChild(new ExpressionTree("cos"));
        diffTree.setRightChild(this.leftChild.differentiate());
        diffTree.leftChild.setLeftChild(this.leftChild);
      }
      
      if(value.equals("cos")){
        diffTree.setValue("minus");
        diffTree.setLeftChild(new ExpressionTree("0"));
        diffTree.setRightChild(new ExpressionTree("mult"));
        diffTree.rightChild.setLeftChild(new ExpressionTree("sin"));
        diffTree.rightChild.setRightChild(this.leftChild.differentiate());
        diffTree.rightChild.leftChild.setLeftChild(this.leftChild);
      }
      
      if(value.equals("exp")){
        diffTree.setValue("mult");
        diffTree.setLeftChild(new ExpressionTree("exp"));
        diffTree.leftChild.setLeftChild(this.getLeftChild());
        diffTree.setRightChild(this.getLeftChild().differentiate());
      }
      //after doing the construction of the differentiate tree for each step, 
      //return the temporary differentiate tree until we get the whole tree
      return diffTree;
    }
        

    public static void main(String args[]) {
        ExpressionTree e = new ExpressionTree("mult(add(2,x),cos(x))");
        System.out.println(e);
        System.out.println(e.evaluate(1));
        System.out.println(e.differentiate());
 
 }
     
}
