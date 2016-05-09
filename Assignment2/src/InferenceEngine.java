import java.util.ArrayList;

public class InferenceEngine {

  private ArrayList<Sentence> fKnowledgeBase;
  
  // Constructor accepts a string containing the whole knowledgebase
  // It splits up the string by semi-colons to create sentences and put them in a Knowledgebase field as a collection of sentences
  // JACQUELINE TO WRITE
  public InferenceEngine(String aKB)
    {
       String[] lSentence = aKB.split(aKB, ';');
       
       fKnowledgeBase = new ArrayList<>();
       
       for(int i = 0; i < aKB.length(); i++)
       {
         fKnowledgeBase.add( new Sentence(lSentence[i]) );
       }
       
       System.out.println("Knowledge base: " + fKnowledgeBase);
    }
  
  

  
  
  
  // this takes the Query string and solves it according to the method specified
  public boolean solve(String aQ, String aMethod)
    {
    boolean lSuccess = false;
        
    System.out.println("Query: " + aQ);
    System.out.println("Method: " + aMethod);
    lSuccess = true;
    
    return lSuccess;
    }
  
  
  
}