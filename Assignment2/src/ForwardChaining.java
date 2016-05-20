import java.util.ArrayList;

public class ForwardChaining extends Method {
	
	String fQuery;
	ArrayList<Sentence> fChainSteps;
	
	public ForwardChaining(ArrayList<Sentence> aKB, String aQ)
	{
		super(aKB);		// save the knowledgebase using the superclasses constructor
		fQuery = aQ;
	}
	
	@Override
	public boolean solve()
	{
		boolean result = false;
		
		
		// check everything in aKB is a horn sentence
		for( Sentence lSentence : fKB )
		{
			if(!lSentence.isHornClause())
			{
				fSolveResult = "Not all sentences are Horn Clauses";
				return false;	// abort the process
			}
		}
		
		
		// create unknownSentences = aKB (copy values not references)
		fChainSteps = new ArrayList<>();
		
		// create knownLiterals array;
		ArrayList<Literal> knownLiterals = new ArrayList<>();
		
		// SentenceLoop:
		// loop unknownSentences sentences repeatedly (while true)
		
			// loop through otherLiterals
		
				// if literal is not in knownLiterals
					// continue SentenceLoop;	// abandon sentence
		
			// end otherLiterals loop
			
			// this is only reached if there are no unknown literals in otherliterals
			// put positiveLiteral into knownLiterals list
			// remove sentence from unknownSentences & add sentence to chainSteps 
		
			// if positiveLiteral.getName() = fQuery
				//break SentenceLoop
		
		// endloop
				
				
		
		
		// iterate through chainSteps to create fSolveResult
		
		
		
		
		
		
		return result;
	}
}
