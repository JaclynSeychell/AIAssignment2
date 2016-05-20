import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;
import java.util.List;

public class Sentence {
	
	// All Sentences
	private ArrayList<Literal> fLiterals;
	private ArrayList<ProLogic> fRpnSentence; // this shouldn't be called math logic, it should be prologic
	// If Horn Sentence
	private boolean fIsHornClause = false;
	private Literal fPositiveLiteral;
	private ArrayList<Literal> fOtherLiterals;

	public Sentence(String aSentence)
	{
		// setup temp variables
		Stack<Operator> lOperators = new Stack<>();
		
		// initialise fields
		fRpnSentence = new ArrayList<ProLogic>();	//?? do I need to redefine the <type> here? - seemed to not error without it
		fLiterals = new ArrayList<Literal>();
		


		aSentence = aSentence.trim(); // trim blank space off ends of whole sentence
		
		
		// "\" is always doubled because it is javascript's escape character
		String lDelims = "";
		lDelims += "\\b"; // any alpha/numeric boundary
		lDelims += "|"; // or
		lDelims += "(\\B(?=\\())"; // any non alpha/numeric boundary that is followed by an open bracket. ie &( 
		lDelims += "|"; // or
		lDelims += "(\\B(?="+Operator.NEGATION_SYMBOL+"))"; // any non alpha/numeric boundary that is followed by an exclamation. ie &!
		lDelims += "|"; // or
		lDelims += "((?<=\\))\\B)"; // any non alpha/numeric boundary (ie numeric/numeric) that follows a close bracket
		String[] temp = aSentence.split(lDelims);
		List<String> lSentence = Arrays.asList(temp);
		
		
		
		
		/*
		 * This isn't necessarily true.. be careful
		 */
		/*
		// If the 2nd last item in the sentence is the negation symbol and
		// the implication symbol is the 3rd last, it's not a positive literal but
		// we could negate both sides to create a horn clause.
		if( 	Operator.NEGATION_SYMBOL.equals( lSentence.get(lSentence.size()-2) ) &&
				Operator.IMPLICATION_SYMBOL.equals( lSentence.get(lSentence.size()-3) ) )
		{
			// remove negation symbol off right side of sentence
			int test = lSentence.size()-2;
			lSentence.remove(test);
			// add negation symbol at start with brackets
			lSentence.add(0, "(");
			lSentence.add(0, Operator.NEGATION_SYMBOL);
			lSentence.add(lSentence.size()-2, ")");			// close bracket before implication symbol
		}
		*/
		
		// check for horn form before RPN ordering or it gets too complicated
		if( Operator.IMPLICATION_SYMBOL.equals( lSentence.get(lSentence.size()-2) ) || lSentence.size() == 1 )
			fIsHornClause = true;
		
		

		
				
		for(String lLogicStr : lSentence)
		{
			lLogicStr = lLogicStr.trim(); // remove white space characters from either end
			//System.out.println("item: "+item);
			
			if( Operator.isOperator(lLogicStr) ){
				// it's an operator
				
				Operator lCurOperator = new Operator(lLogicStr);
				 
				while(	!lOperators.isEmpty() &&
						( lOperators.peek().getPrecedence() < lCurOperator.getPrecedence() )
						) {
					addToSentence(lOperators.pop());
	            }
				
				lOperators.push(lCurOperator);
				
				
			} else if( "(".equals(lLogicStr) ) {
				// it's an open bracket
				
				//push it straight into the operator stack regardless of precedence
				lOperators.push( new Operator(lLogicStr) );
					
			} else if( ")".equals(lLogicStr) ) {
				// it's a close bracket
				
				Operator lCurOperator; 
				// put all Operators saved up after the left bracket into the Sentence
	            while(!lOperators.isEmpty())
	            {
	            	lCurOperator = lOperators.pop();
	            	if( "(".equals(lCurOperator.getName()) )
	            		break;
	            	
	            	addToSentence(lCurOperator);
	            }
	            
			} else if( lLogicStr == " " || lLogicStr.length()==0 ) {
				// the regEx split above should result in no spaces or empty strings, but ignore them just incase
			} else {
				// all other strings should be interpreted as a Literal
				Literal lLiteral = new Literal(lLogicStr);
				addLiteral(lLiteral);
				addToSentence(lLiteral);
			}
			
		}
		
		
		//Add all of the remaining operators to fRpnSentence
        while( !lOperators.isEmpty() )
        	addToSentence(lOperators.pop());
        
        
        
        
        
        // if it's a horn clause, populate relevant fields
        if(fIsHornClause)
        {
        	// initialise fields
    		fOtherLiterals = new ArrayList<Literal>();
    		
    		// interpret other literals and positive literal
    		fPositiveLiteral = getLiteral(literalsLength()-1);	// last literal
    		
    		for(int k=0; k<literalsLength()-1; k++)	// not the last one because that's the positive literal
    			if( getLiteral(k) instanceof Literal )
    				addOtherLiteral(getLiteral(k));
    		// if it's a fact, fOtherLiterals will contain nothing - this is expected.
    		
        }
        
		
	}
	
	
	
	// used to check if the sentence is a horn clause
	public boolean isHornClause()
	{
		return fIsHornClause;
	}
	
	
	
	/*
	 * Manipulating Literals
	 */
	
	protected void addLiteral(Literal aLiteral)
	{
		fLiterals.add(aLiteral);
	}
	
	public Literal getLiteral(int aIndex)
	{
		return fLiterals.get(aIndex);
	}
	
	public int literalsLength()
	{
		return fLiterals.size();
	}
	
	
	
	
	/*
	 * Manipulating the Sentence
	 */
	
	private void addToSentence(ProLogic aProLogic)
	{
		fRpnSentence.add(aProLogic);
	}
	
	public ProLogic getFromSentence(int aIndex)
	{
		return fRpnSentence.get(aIndex);
	}
	
	public int sentenceLength()
	{
		return fRpnSentence.size();
	}
	
	
	
	
	
	// Horn Clauses
	
	/*
	 * Manipulate Positive Literal
	 */

	public Literal getPositiveLiteral()
	{
		return fPositiveLiteral;
	}
	
	
	/*
	 * Manipulating Other Literals
	 */
	
	private void addOtherLiteral(Literal aLiteral)
	{
		fOtherLiterals.add(aLiteral);
	}
	
	public Literal getOtherLiteral(int aIndex)
	{
		return fOtherLiterals.get(aIndex);
	}
	
	public int otherLiteralsLength()
	{
		return fOtherLiterals.size();
	}
	
	
}
