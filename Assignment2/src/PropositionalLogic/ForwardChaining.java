package PropositionalLogic;
import java.util.ArrayList;
import java.util.Stack;

public class ForwardChaining extends Method {
	
	Literal fQuery;
	Stack<Sentence> fChainSteps;
	
	public ForwardChaining(ArrayList<Sentence> aKB, Literal aQuery)
	{
		super(aKB);		// save the knowledgebase using the superclasses constructor
		fQuery = aQuery;
	}
	
	
	
	// Finds steps in the chain that can be used to solve the query
	// Saves those steps in fChainSteps
	// returns true if the query can be solves or false if not 
	@Override
	public boolean solve()
	{
		
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
		
		
		// sentences that haven't had their Literals explored yet
		ArrayList<Sentence> lUnknownSentences = fKB;
		
		// steps found leading back from fQuery
		fChainSteps = new Stack<>();
		
		// The list of literals we've found so far
		Stack<Literal> lKnownLiterals = new Stack<>();
		
		
		SearchLoop:
		while(!lUnknownSentences.isEmpty())	// keep looping while there are sentences unknown
		{
			boolean lProgressMade = false;
			
			
			// loop all sentences left with unknown positiveLiterals
			SentenceLoop:
			for( Sentence lSentence : lUnknownSentences )
			{
				// loop through otherLiterals
				for( int k=0; k<lSentence.otherLiteralsLength(); k++)
				{
					// if literal is not in lKnownLiterals
					if( !lKnownLiterals.contains( lSentence.getOtherLiteral(k) ) )
						continue SentenceLoop;	// abandon sentence
				}
				
				// this is only reached if all left hand side literals are known
				lProgressMade = true;
				
				// add to chainSteps
				fChainSteps.push(lSentence);
				
				// put positiveLiteral into knownLiterals list
				lKnownLiterals.add( lSentence.getPositiveLiteral() );
			
				// if we found the result then no need to search anymore
				if(lSentence.getPositiveLiteral() == fQuery )
				{
					//System.out.println("About to break SearchLoop");
					solutionPrepared = true;
					break SearchLoop;
				}
			
			} // SentenceLoop end
			
			
			// remove new found sentences from unknownSentences
			for(int k=fChainSteps.size()-1; k>=0; k--)
			{
				lUnknownSentences.remove( fChainSteps.get(k) );
			}
			
			
			// if we've looked at ALL the sentences and haven't found another step, it can't be solved
			if( !lProgressMade )
			{
				break SearchLoop;
			}
		
			//System.out.println("About to loop again");
		} // SearchLoop end
				
		
		return solutionPrepared;
	}
	
	
	
	
	@Override
	public String getSolution()
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
