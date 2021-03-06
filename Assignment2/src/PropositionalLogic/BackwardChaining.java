package PropositionalLogic;
import java.util.ArrayList;
import java.util.*;

/**
 * BackwardChaining extends Method as a way to calculate a solution to the desired query literal
 * based on the KnowledgeBase initially passed into the InferenceEngine class.
 * It is automatically called from the InferenceEngine class.
 * It can only solve if the KnowledgeBase is all HornClauses.
 * 
 *  @author Dale
 */
public class BackwardChaining extends Method {
	
	Literal fQuery;
	Stack<Sentence> fChainSteps;
	
	
	/**
	 * Initialises the KnowledgeBase and Query Literal to be used by the solve method.
	 * 
	 * @param aKB takes a ArrayList of sentences
	 * @param aQuery takes a Literal indicating the requested solution
	 */
	public BackwardChaining(ArrayList<Sentence> aKB, Literal aQuery)
	{
		super(aKB);		// save the knowledgebase using the superclasses constructor
		fQuery = aQuery;
	}
	
	
	
	
	/**
	 * Finds steps in the chain that can be used to solve the query.
	 * Saves those steps in fChainSteps.
	 * 
	 * @return A boolean indicating whether a solution was successfully found
	 */
	@Override
	public boolean solve()
	{
		
		// check everything in aKB is a horn sentence
		for( Sentence lSentence : fKB )
		{
			if(!lSentence.isHornClause())
			{
				// TODO: throw an error here
				//"ERROR: Not all sentences are Horn Clauses - Cannot use Backward Chaining";
				return false;	// abort the process
			}
		}
		
		
		// sentences that haven't had their Literals explored yet
		ArrayList<Sentence> lUnknownSentences = fKB;
		
		// steps found leading back from fQuery
		fChainSteps = new Stack<>();
		
		// The list of literals we've found so far
		Stack<Literal> lKnownLiterals = new Stack<>();
				
		// The list of literals we need to find solutions for
		Queue<Literal> lNeededLiterals = new LinkedList<>();
		lNeededLiterals.add(fQuery);
		
		
		
		Literal lNeededLiteral;
		
		// keep looping while there are literals needed
		NeededLiteralLoop:
		while( !lNeededLiterals.isEmpty() )
		{
			lNeededLiteral = lNeededLiterals.remove();	// get the first needed literal
			
			// loop all sentences left with unknown positiveLiterals
			for( Sentence lSentence : lUnknownSentences )
			{
				
				// if the needed literal is on the right hand side of the implication
				if( lNeededLiteral == lSentence.getPositiveLiteral() )
				{
					// we've found a sentence that will give us the needed literal
					
					// remove sentence from unknownSentences and add to chainSteps
					lUnknownSentences.remove(lSentence);
					fChainSteps.push(lSentence);
					
					// put the literal into knownLiterals stack
					lKnownLiterals.push( lNeededLiteral );
				
					// if it's not a fact (a 1 literal sentence)
					if(lSentence.sentenceLength() > 1 )
					{
						// add all literals on the left to the needed list
						for(int k = 0; k<lSentence.otherLiteralsLength(); k++)
							if( !lKnownLiterals.contains(lSentence.getOtherLiteral(k)) )	// not if they're already known
								if( !lNeededLiterals.contains(lSentence.getOtherLiteral(k)) )	// not if they're already in the needed list
									lNeededLiterals.add(lSentence.getOtherLiteral(k));
					}
					
					// if there's no more literals needed
					if(lNeededLiterals.isEmpty())
					{
						// then no need to search anymore
						fSolutionPrepared = true;
						break NeededLiteralLoop;
					} else {
						// otherwise, skip to the next literal needed
						continue NeededLiteralLoop;
					}
				}
				
			} // SentenceLoop end
			
			
			// if this is reached the literal wasn't found as implied in any sentence
			// try and backtrack to find a new path
			
			// erase the lNeededLiterals stack as it contains literals that contribute to a sentence that can't be solved
			lNeededLiterals.clear();
			
			// add the last found literal back into lNeededLiterals to look for a different route
			// (the sentence that was used has already been removed so it won't see that again)
			if(!lKnownLiterals.isEmpty())
				lNeededLiterals.add( lKnownLiterals.pop() );
			else
				break NeededLiteralLoop; // haven't found the solution and there's no more steps
				
			// remove the last added item in fChainSteps also as it's not longer a valid step
			//fChainSteps.pop();	// this is commented out so no attempted steps are removed
			
		} // NeededLiteralLoop end
		
		
		return fSolutionPrepared;
	}
	
	
	
	/**
     * Creates a human readable summary of the solution previously found by the solve method.
     * 
     * @return String as a human readable summary of the previously found solution.
     */
	@Override
	public String getSolution()
	{
		String lMessage = "";
		
		if(!fChainSteps.isEmpty())
		{
			for(int k=fChainSteps.size()-1; k>=0; k-- )
			{
				Sentence lSentence = fChainSteps.get(k);
				if(k!=fChainSteps.size()-1)
					lMessage += ", ";
				lMessage +=	lSentence.getPositiveLiteral().getName();
			}
		}
		
		if(fErrorMessage != "")
		{
			lMessage += System.getProperty("line.separator");
			lMessage += fErrorMessage;
		}
		
		return lMessage;
	}
	
	
	
	
	
	
	
	
	
	
	
}
