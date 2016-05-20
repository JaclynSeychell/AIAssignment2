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
		
		fLiterals = new ArrayList<>();
		// collate all literals refered to in all sentences into list
		for(Sentence s : fKnowledgeBase)
		{
			NextLiteral:
			for(int k=0; k<s.literalsLength(); k++)
			{
				// can't use this because the object references are different
				//if( !fLiterals.contains(s.getLiteral(k)) )	
				//		fLiterals.add(s.getLiteral(k));
				
				// have to test the names
				for(Literal lSavedLiteral : fLiterals)
				{
					if(s.getLiteral(k).getName().equals(lSavedLiteral.getName()) )	// already saved
						continue NextLiteral;
				}
				// wasn't found in already saved list so add it now
				fLiterals.add(s.getLiteral(k));
				
			}
		}
		
	}
  
  
  
	// this takes the Query string and solves it according to the method specified
	public boolean solve(String aQ, String aMethod)
	{
		
		Method lMethod;
		boolean lSuccess = false;
		
		
		
		switch(aMethod)
		{
			case "TT":	lMethod = new TruthTable(fKnowledgeBase, fLiterals);
						break;
						
			case "FC":	lMethod = new ForwardChaining(fKnowledgeBase, aQ);
						break;
						
			case "BC":	lMethod = new BackwardChaining(fKnowledgeBase, aQ);
						break;
						
			default:	System.out.println("No valid method chosen.");
		}
		
		// solve and print result
		lSuccess = lMethod.solve();
		if(lSuccess)
			System.out.println(lMethod.getResult());

		
		
		System.out.println("Query: " + aQ);
		System.out.println("Method: " + aMethod);
		
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