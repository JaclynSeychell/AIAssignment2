import java.util.ArrayList;

public class ForwardChaining extends Method {
	
	Literal fQuery;
	ArrayList<Sentence> fChainSteps;
	
	public ForwardChaining(ArrayList<Sentence> aKB, Literal aQuery)
	{
		super(aKB);		// save the knowledgebase using the superclasses constructor
		fQuery = aQuery;
	}
	
	
	
	// Finds steps in the chain that can be used to solve the query
	// Saves those steps in fChainSteps
	// returns true if the query can be solves or false if not 
	@Override
	public boolean prepareSolution()
	{
		// fReadyToSolve is initialised to false in parent class;
		
		
		// check everything in aKB is a horn sentence
		for( Sentence lSentence : fKB )
		{
			if(!lSentence.isHornClause())
			{
				// TODO: throw an error here
				//"Not all sentences are Horn Clauses - Cannot use Forward Chaining";
				return false;	// abort the process
			}
		}
		
		
		
		
		ArrayList<Sentence> lUnknownSentences = fKB;
		fChainSteps = new ArrayList<>();
		
		// create knownLiterals array;
		ArrayList<Literal> knownLiterals = new ArrayList<>();
		
		
		SearchLoop:
		while(!lUnknownSentences.isEmpty())	// keep looping while there are sentences unknown
		{
			int lPrevKnownNum = knownLiterals.size();
			
			
			// loop all sentences left with unknown positiveLiterals
			SentenceLoop:
			for( Sentence lSentence : lUnknownSentences )
			{
				// loop through otherLiterals
				for( int k=0; k<lSentence.otherLiteralsLength(); k++)
				{
					// if literal is not in knownLiterals
					if( !knownLiterals.contains( lSentence.getOtherLiteral(k) ) )
						continue SentenceLoop;	// abandon sentence
				}
				
				
				// this is only reached if all left hand side literals are known
				
				// put positiveLiteral into knownLiterals list
				knownLiterals.add( lSentence.getPositiveLiteral() );
				
				// add sentence to chainSteps
				fChainSteps.add(lSentence);
			
				// if we found the result then no need to search anymore
				if(lSentence.getPositiveLiteral() == fQuery )
				{
					solutionPrepared = true;
					break SearchLoop;
				}
			
			}
			
			// remove new found sentences from unknownSentences
			for(int k=fChainSteps.size()-1; k>=0; k--) // lPrevKnownNum+1
			{
				lUnknownSentences.remove( fChainSteps.get(k) );
			}
			
			
			// if we've looked at ALL the sentences and haven't found another link, it can't be solved
			if( knownLiterals.size() == lPrevKnownNum)
				break SearchLoop;
		
		}
				
		
		return solutionPrepared;
	}
	
	
	@Override
	public String printSolutionList()
	{		
		String lMessage = "";
		
		if(!solutionPrepared)	// a solution wasn't found - so don't print the steps found
			return lMessage;
		
		if(!fChainSteps.isEmpty())
		{
			
			for(int k=0; k<fChainSteps.size(); k++ )
			{
				Sentence lSentence = fChainSteps.get(k);
				
				if(k!=0)	lMessage += ", ";
				lMessage +=	lSentence.getPositiveLiteral().getName();
			}
		
		}
		
		return lMessage;
	}
	
	
	
	
	
	
}
