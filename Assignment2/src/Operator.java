
public class Operator extends ProLogic {

    // Operators with lower precedence values get calculated first
	private final static int NEGATION = 1;
	private final static int CONJUNCTION = 2;
	private final static int DISJUNCTION = 3;
	private final static int IMPLICATION = 4;
	private final static int EQUIVALENCE = 5;
	private final static int LEFT_PARENTHESIS = 20; // should always have the highest value to cause it to remain low in the stack until the right parenthesis is seen and all operators above are popped off
    
	
	// symbols
	private final static String NEGATION_SYMBOL = "~";
	private final static String CONJUNCTION_SYMBOL = "&";
	private final static String DISJUNCTION_SYMBOL = "\\/";	// extra backslash because it's java's escape character
	private final static String IMPLICATION_SYMBOL = "=>";
	private final static String EQUIVALENCE_SYMBOL = "<=>";
	private final static String LEFT_PARENTHESIS_SYMBOL = "(";
    
    //Determines what precedence the operator has (think BODMAS)
    private int fPrecedence;
    
    
    
    // return if the string is an operator or not
    public static boolean isOperator(String aName)
    {
    	switch(aName)
        {
        	case NEGATION_SYMBOL:		return true;
        	case CONJUNCTION_SYMBOL:	return true;
        	case DISJUNCTION_SYMBOL:	return true;
        	case IMPLICATION_SYMBOL:	return true;
        	case EQUIVALENCE_SYMBOL:	return true;
        	// "(" isn't includeded here so it isn't pushed in relative to the others
        	default:	return false;
        }
    }
    
    
    // return if the string is an operator or not
    public static String negationSymbol()
    {
    	return NEGATION_SYMBOL;
    }
    
    
    
    public Operator(String aName)
    {
        super(aName);
        
        switch(aName)
        {
        	case NEGATION_SYMBOL:			this.fPrecedence = NEGATION;			break;
        	case CONJUNCTION_SYMBOL:		this.fPrecedence = CONJUNCTION;			break;
        	case DISJUNCTION_SYMBOL:		this.fPrecedence = DISJUNCTION;			break;
        	case IMPLICATION_SYMBOL:		this.fPrecedence = IMPLICATION;			break;
        	case EQUIVALENCE_SYMBOL:		this.fPrecedence = EQUIVALENCE;			break;
        	case LEFT_PARENTHESIS_SYMBOL:	this.fPrecedence = LEFT_PARENTHESIS;	break;
        	default:	break;
        }
        
    }
    
    
    
    
    
    public int getPrecedence()
    {
        return fPrecedence;
    }    
}
