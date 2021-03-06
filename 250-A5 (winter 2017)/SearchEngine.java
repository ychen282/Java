import java.util.*;
import java.io.*;

// This class implements a google-like search engine
public class SearchEngine {

    public HashMap<String, LinkedList<String> > wordIndex;                  // this will contain a set of pairs (String, LinkedList of Strings) 
    public DirectedGraph internet;             // this is our internet graph
    
    
    
    // Constructor initializes everything to empty data structures
    // It also sets the location of the internet files
    SearchEngine() {
 // Below is the directory that contains all the internet files
 HtmlParsing.internetFilesLocation = "internetFiles";
 wordIndex = new HashMap<String, LinkedList<String> > ();  
 internet = new DirectedGraph();    
    } // end of constructor//2017
    
    
    // Returns a String description of a searchEngine
    public String toString () {
 return "wordIndex:\n" + wordIndex + "\ninternet:\n" + internet ;
    }
    
    
    // This does a graph traversal of the internet, starting at the given url.
    // For each new vertex seen, it updates the wordIndex, the internet graph,
    // and the set of visited vertices.
    
    void traverseInternet(String url) throws Exception {
 /* WRITE SOME CODE HERE */
 
 /* Hints
    0) This should take about 50-70 lines of code (or less)
    1) To parse the content of the url, call
    htmlParsing.getContent(url), which returns a LinkedList of Strings 
    containing all the words at the given url. Also call htmlParsing.getLinks(url).
    and assign their results to a LinkedList of Strings.
    2) To iterate over all elements of a LinkedList, use an Iterator,
    as described in the text of the assignment
    3) Refer to the description of the LinkedList methods at
    http://docs.oracle.com/javase/6/docs/api/ .
    You will most likely need to use the methods contains(String s), 
    addLast(String s), iterator()
    4) Refer to the description of the HashMap methods at
    http://docs.oracle.com/javase/6/docs/api/ .
    You will most likely need to use the methods containsKey(String s), 
    get(String s), put(String s, LinkedList l).  
 */
      internet.setVisited(url,true);

      //initialize two linkedlist for getting urls and words respectively
      LinkedList<String> linksList = HtmlParsing.getLinks(url);
      LinkedList<String> wordsList = HtmlParsing.getContent(url);

      //first get all the words in the current url and update wordIndex HashMap
      Iterator<String> iterWords = wordsList.iterator();
      while (iterWords.hasNext()){
        String word = iterWords.next();
        if(!wordIndex.containsKey(word))//only added to wordIndex when this word is not in the wordIndex HashMap
          wordIndex.put(word, new LinkedList<String>());
          if(!wordIndex.get(word).contains(url))//only added when this url is not in the HashMap
            wordIndex.get(word).add(url);
        } 
            
      //then get links in the current url and update the internet DirectedGraph
       Iterator<String> iterLinks = linksList.iterator();
       while (iterLinks.hasNext()){
         String link = iterLinks.next();
         internet.addEdge(url,link);
         if(!internet.getVisited(link)){//only call the recursion by the link when the link has not been visited
           traverseInternet(link);
         }
         System.out.print(link);
       }
       
    } // end of traverseInternet
    
    
    /* This computes the pageRanks for every vertex in the internet graph.
       It will only be called after the internet graph has been constructed using 
       traverseInternet.
       Use the iterative procedure described in the text of the assignment to
       compute the pageRanks for every vertices in the graph. 
       
       This method will probably fit in about 30 lines.
    */
    void computePageRanks() {
 /* WRITE YOUR CODE HERE */
      
      //iterate through all the vertices in the directed graph and initialize hashMap of pageRank
      Iterator<String> iterVer = internet.getVertices().iterator();
  
      while (iterVer.hasNext()){
        String ver = iterVer.next();
        //initialize all the pageranks to 1
        internet.setPageRank(ver,1);
      }
  
      int i=1;
      while(i<=100){
        //iterate through hashMap of pagerank and update it
        Iterator<String> iterPage = internet.getVertices().iterator();
        while (iterPage.hasNext()){
          String page = iterPage.next();
          //get a list of vertices that have links to the k
          LinkedList<String> getInto = internet.getEdgesInto(page);
          //apply the formula to calculate current pageRank value 
          Iterator<String> iterInto = getInto.iterator();
          double rank = 0.5;
          //iterate through all vertices linking to it
          while (iterInto.hasNext()){
            String current =iterInto.next();
            rank = rank + 0.5*internet.getPageRank(current)/internet.getOutDegree(current);
          }
          internet.setPageRank(page, rank);
        } 
        i++;
      }
      
      //for checking the value of each pageRank
      Iterator<String> iterList = internet.getVertices().iterator();
      while (iterList.hasNext()){
        String url = iterList.next();
        System.out.println(url + "=" + internet.pageRank.get(url));
      }
   
    } // end of computePageRanks
    
 
    /* Returns the URL of the page with the high page-rank containing the query word
       Returns the String "" if no web site contains the query.
       This method can only be called after the computePageRanks method has been executed.
       Start by obtaining the list of URLs containing the query word. Then return the URL 
       with the highest pageRank.
       This method should take about 25 lines of code.
    */
    String getBestURL(String query) {
 /* WRITE YOUR CODE HERE */
      if(!wordIndex.containsKey(query)) return "";
      
      LinkedList<String> list = wordIndex.get(query);
      
      double tempRank = 0;//tempRank for keeping the temporary largest pageRank value
      String tempURL = "";//get the url string with the temporary largest pageRank
      
      Iterator<String> iterList = list.iterator();
      while (iterList.hasNext()){
        String url = iterList.next();
        if(internet.getPageRank(url)>tempRank){
          tempRank = internet.getPageRank(url);
          tempURL = url;
        }
      }
      return tempURL;
    } // end of getBestURL
    
    
 
    public static void main(String args[]) throws Exception{  
 SearchEngine mySearchEngine = new SearchEngine();
 // to debug your program, start with.
 mySearchEngine.traverseInternet("http://www.cs.mcgill.ca/~blanchem/250/a.html");
 
 // When your program is working on the small example, move on to
 mySearchEngine.traverseInternet("http://www.cs.mcgill.ca");
 
 // this is just for debugging purposes. REMOVE THIS BEFORE SUBMITTING
 System.out.println(mySearchEngine);
 
 mySearchEngine.computePageRanks();
 
 BufferedReader stndin = new BufferedReader(new InputStreamReader(System.in));
 String query;
 do {
     System.out.print("Enter query: ");
     query = stndin.readLine();
     if ( query != null && query.length() > 0 ) {
  System.out.println("Best site = " + mySearchEngine.getBestURL(query));
     }
 } while (query!=null && query.length()>0);    
    } // end of main
}
