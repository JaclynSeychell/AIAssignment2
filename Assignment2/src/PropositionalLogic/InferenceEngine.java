package PropositionalLogic;
import java.util.ArrayList;

/**
 * InferenceEngine is the main class used to initialise an Inference Engine for calculating solutions
 * to Propositional Logic Knowledgebases.
 * 
 * @author Jaclyn & Dale
 */
public class InferenceEngine {

	public ArrayList<Sentence> fKnowledgeBase;	// only public for testing purposes
	public ArrayList<Literal> fLiterals;		// only public for testing purposes
	private Method fMethod;						// the method initialised for solving the query
	private String fSolveResult;				// the human readable result found after solving the method
	
	
	
	/**
	 * Constructor initialises a Knowledgebase of passed in sentences and all literals contained in those sentences
	 * @param aKB A String of the format "sentence1;sentence2;sentence3;etc" ie "a&b=>c;a"
	 */
	public InferenceEngine(String aKB)
	{

		String[] lSentence = aKB.split(";");
		fKnowledgeBase = new ArrayList<>();
		
		// interpret and add each sentence into knowledgebase
		for(int i = 0; i < lSentence.length; i++)
			fKnowledgeBase.add( new Sentence(lSentence[i]) );
		
		unifyLiterals();
		
	}
	
	
	

	/**
	 * Unifys all Literals in fLiterals and fKnowledgeBase so that each Literal with an equivalent String name points to the same object.
	 * This is necessary as a post process because each sentence interprets the sentence string internally.
	 */
	private void unifyLiterals()
	{
		
		fLiterals = new ArrayList<>();
		// collate all literals refered to in all sentences into list
		for(Sentence s : fKnowledgeBase)
		{
			NextLiteral:
			for(int k=0; k<s.literalsLength(); k++)
			{
				
				// have to test the names as the same literal in different sentences is in memory as a different object
				for(Literal lSavedLiteral : fLiterals)
				{
					if(s.getLiteral(k).getName().equals(lSavedLiteral.getName()) )	// already saved
					{
						// replace the literal in the SENTENCE with the one in the fLiterals list so they reference the same object
						s.replaceLiteral(s.getLiteral(k), lSavedLiteral);
						
						// don't add in fLiterals again, just go to the next literal
						continue NextLiteral;
					}
				}
				
				
				// wasn't found in already saved list so add it now
				fLiterals.add(s.getLiteral(k));
				
			}
		}
	}
	
	
	
	
	
	/**
	 * Takes a human readable string representation of the Literal and returns the corresponding Literal object
	 * @param lLiteralName A String representation of the Literal
	 * @return The Literal object corresponding to that String name
	 */
	private Literal getQueryLiteral(String lLiteralName)
	{
		Literal result = new Literal(lLiteralName);	// TODO: if this gets returned, the problem is actually unsolvable as the query literal doesn't exist in the sentences
		
		for(Literal lLiteral : fLiterals)
		{
			if( lLiteralName.equals(lLiteral.getName()) )
			{
				result = lLiteral;
				break;
			}
		}
		
		return result;
	}
  
  
  
	
	
	/**
	 * Sets up and solves using a specified method.
	 * TT = TruthTable
	 * FC = Forward Chaining
	 * BC = Backward Chaining
	 * Saves a human readable result in fSolveResult - Accessible through getSolveResult
	 * 
	 * @param aQ A String representing the name of the literal to solve for in FC or BC
	 * @param aMethod a String representing the name of the method to use.
	 * @return A boolean indicating whether a solution was successfully found.
	 */
	public boolean solve(String aQ, String aMethod)
	{
		
		boolean lSuccess = false;
		
		Literal lQuery = getQueryLiteral(aQ);
		
		
		switch(aMethod)
		{
			case "TT":	fMethod = new TruthTable(fKnowledgeBase, fLiterals);
						break;
						
			case "FC":	fMethod = new ForwardChaining(fKnowledgeBase, lQuery);
						break;
						
			case "BC":	fMethod = new BackwardChaining(fKnowledgeBase, lQuery);
						break;
						
			default:	System.out.println("No valid method chosen.");
						return false;	// exit here if not a valid method
		}
		
		// solve and print result
		lSuccess = fMethod.solve();
		
		if(lSuccess)
			fSolveResult = "YES: " + fMethod.getSolution();
		else
			fSolveResult = "NO";

		
		
		return lSuccess;
	}
	
	
	
	/**
	 * Accesses the human readable result created by the solve method
	 * 
	 * @return A String of the human readable result
	 */
	public String getSolveResult()
	{
		return fSolveResult;
	}
	
	
	
	
	
	
	/**
	 * Creates a human readable string of the Sentences in fKnowledgeBase as per their originally inputted format
	 * Only used for testing.
	 * 
	 * @return A String of human readable sentences
	 */
	public String getKnowledgeBaseString()
	{
		String result = "";
		
		for(Sentence lSentence : fKnowledgeBase)
		{
			for(int k=0; k<lSentence.sentenceLength(); k++)
				result += lSentence.getFromSentence(k).getName();
			result+=";";
		}
		
		return result;
	}
	
	
	
	/**
	 * Creates a human readable string of the Literals in fLiterals.
	 * Only used for testing.
	 * 
	 * @return A String of human readable Literals separated by colons
	 */
	public String getLiteralsString()
	{
		String result = "";
		
		for(Literal lLiteral : fLiterals)
		{
			result += lLiteral.getName();
			result+=";";
		}
		
		return result;
	}
  
  
  
}