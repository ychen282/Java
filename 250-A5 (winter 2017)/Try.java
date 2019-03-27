public class Try{
  public static void main(String args[])throws Exception{ 
    //System.out.println(HtmlParsing.getContent("http://www.mcgill.ca"));
    System.out.println(HtmlParsing.getLinks("http://www.youdao.com"));
  }
}

/*
     LinkedList<String> words = HtmlParsing.getContent(url);
     Iterator<String> iterWords = words.iterator();
     while (iterWords.hasNext()){
       String w = iterWords.next();
       //LinkedList<String> initial = new LinkedList<String>;
       if(wordIndex.containsKey())
       initial.add(url);
       if(!wordIndex.containsKey(w)){
       wordIndex.put(w,initial);
     
       /*LinkedList<String> links = HtmlParsing.getLinks(url);
       Iterator<String> iterLinks = words.iterator();
       while (iterLinks.hasNext()){
         String urlNew = iterLinks.next();
         internet.addEdge(url, urlNew);
       }
       }
       else{
         wordIndex.put(w.initial);
       }

     }*/