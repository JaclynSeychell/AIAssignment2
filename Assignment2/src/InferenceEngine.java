import java.util.ArrayList;

public class InferenceEngine {

	private ArrayList<String> aKnowledgeBase;
	
	// Constructor accepts a string containing the whole knowledgebase
	// It splits up the string by semi-colons to create sentences and put them in a Knowledgebase field as a collection of sentences
	// JACQUELINE TO WRITE
	public InferenceEngine(String aKB)
    {
       String[] aSentence = aKB.split(aKB, ';');
       
       aKnowledgeBase = new ArrayList<>();
       
       for(int i = 0; i < aKB.length(); i++)
       {
    	   aKnowledgeBase.add(aSentence[i]);
       }
       
	System.out.println("Knowledge base: " + aKnowledgeBase);
    }
	
	
	// converts a string of a sentence into an Reverse Polish Notation (RPN) array containing variables, operators, and bracket objects
	// DALE TO WRITE
	private Sentence makeSentence(String aSentence)
	{
		
		return new Sentence("Sentence: " + aSentence);
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
