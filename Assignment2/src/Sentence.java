import java.util.ArrayList;
import java.util.Stack;

public class Sentence {
	
	private ArrayList<Literal> fLiterals;
	private ArrayList<ProLogic> fRpnSentence; // this shouldn't be called math logic, it should be prologic

	public Sentence(String aSentence)
	{
		// setup temp variables
		int k;
		Stack<Operator> lOperators = new Stack<>();
		
		// initialise fields
		fRpnSentence = new ArrayList<ProLogic>();	//?? do I need to redefine the <type> here? - seemed to not error without it
		fLiterals = new ArrayList<Literal>();
		


		aSentence = aSentence.trim(); // trim blank space off ends of whole sentence
		
		// couldn't get this example to work
		
		// "\G" means match all in string rather than stopping after the first
		//String[] lSentence = aSentence.split("(?<=\\G(\\w+(?!\\w+)|==|<=|>=|\\+|/|\\*|-|(<|>)(?!=)))\\s*");
		//String[] lSentence = aSentence.split("(?<=\\G(\\w+(?!\\w+)|=>|&|\\||\\+|/|\\*|-|(<|>)(?!=)))\\s*");
		
		
		// so I made my own
		
		// "\" is always doubled because it is javascript's escape character
		String lDelims = "";
		lDelims += "\\b"; // any alpha/numeric boundary
		lDelims += "|"; // or
		lDelims += "(\\B(?=\\())"; // any non alpha/numeric boundary that is followed by an open bracket. ie &( 
		lDelims += "|"; // or
		lDelims += "(\\B(?="+Operator.NEGATION_SYMBOL+"))"; // any non alpha/numeric boundary that is followed by an exclamation. ie &!
		lDelims += "|"; // or
		lDelims += "((?<=\\))\\B)"; // any non alpha/numeric boundary (ie numeric/numeric) that follows a close bracket
		String[] lSentence = aSentence.split(lDelims);
		
		
		
		
		// TODO Negation should be put after the variable it applies to
		// what about brackets, are they okay?
		
				
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
	
	
	
	
}
