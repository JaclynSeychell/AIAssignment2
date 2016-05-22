import java.util.ArrayList;

public class InferenceEngine {

	public ArrayList<Sentence> fKnowledgeBase;
	public ArrayList<Literal> fLiterals;
	
	// Constructor accepts a string containing the whole knowledgebase
	// It splits up the string by semi-colons to create sentences and put them in a Knowledgebase field as a collection of sentences
	public InferenceEngine(String aKB)
	{

		String[] lSentence = aKB.split(";");
		fKnowledgeBase = new ArrayList<>();
		
		// interpret and add each sentence into knowledgebase
		for(int i = 0; i < lSentence.length; i++)
			fKnowledgeBase.add( new Sentence(lSentence[i]) );
		
		unifyLiterals();
		
	}
	
	
	
	// make sure all literals in every sentence refer to the same object in memory
	// this is necessary as a post process because each sentence interprets the sentence string internally.
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
  
  
  
	// this takes the Query string and solves it according to the method specified
	public boolean solve(String aQ, String aMethod)
	{
		
		Method lMethod;
		boolean lSuccess = false;
		
		Literal lQuery = getQueryLiteral(aQ);
		
		
		switch(aMethod)
		{
			//case "TT":	lMethod = new TruthTable(fKnowledgeBase, fLiterals);
			//			break;
						
			case "FC":	lMethod = new ForwardChaining(fKnowledgeBase, lQuery);
						break;
						
			//case "BC":	lMethod = new BackwardChaining(fKnowledgeBase, lQuery);
			//			break;
						
			default:	System.out.println("No valid method chosen.");
						return false;	// exit here if not a valid method
		}
		
		// solve and print result
		lSuccess = lMethod.isSolvable();
		
		if(lSuccess)
			System.out.println( "YES: " + lMethod.printSolutionList() );
		else
			System.out.println( "NO: " + lMethod.printSolutionList() );

		
		
		//System.out.println("Query: " + aQ);
		//System.out.println("Method: " + aMethod);
		
		return lSuccess;
	}
	
	
	
	
	
	
	///////////////////
	// just for testing
	///////////////////
	
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