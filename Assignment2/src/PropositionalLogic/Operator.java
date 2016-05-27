package PropositionalLogic;
import java.util.Stack;


/**
 * The Operator class extends ProLogic items as it often sits in an array containing Literal objects also.
 * All visual string representations of operators are available as static finals so they can be utilised
 * through the program but only updated here.
 * The Operators also perform their calculation operation internally so that all operator changes and updates
 * can be made within this class only.
 * 
 * @author Dale
 *
 */
public class Operator extends ProLogic {

    // Operators with lower precedence values get calculated first
	private final static int NEGATION = 1;
	private final static int CONJUNCTION = 2;
	private final static int DISJUNCTION = 3;
	private final static int IMPLICATION = 4;
	private final static int EQUIVALENCE = 5;
	private final static int LEFT_PARENTHESIS = 20; // should always have the highest value to cause it to remain low in the stack until the right parenthesis is seen and all operators above are popped off
    
	
	// symbols
	public final static String NEGATION_SYMBOL = "~";
	public final static String CONJUNCTION_SYMBOL = "&";
	public final static String DISJUNCTION_SYMBOL = "\\/";	// extra backslash because it's java's escape character
	public final static String IMPLICATION_SYMBOL = "=>";
	public final static String EQUIVALENCE_SYMBOL = "<=>";
	public final static String LEFT_PARENTHESIS_SYMBOL = "(";
    
    //contains the precedence the operator has (order of operations)
    private int fPrecedence;
    
    
    
    /**
     * Use this to tell if an String can be interpreted as one of the predefined operators.
     * It is a static method.
     * 
     * @param aName The String to check
     * @return A boolean indicating if it is interpretable as an operator
     */
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
    
    
    
    /**
     * Initialises the type of operator based on the passed in string.
     * 
     * @param aName The String representing the operator
     */
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
    
    
    
    /**
     * Pops the correct number of boolean values off the passed in Stack object and
     * evaluates them according to the type of Operator of the calling Object and
     * pushes the result back onto the stack.
     * 
     * @param aStack A Stack of boolean values
     * @return The result of the operation.
     */
    public boolean eval(Stack<Boolean> aStack) {
    	
    	boolean result = false;
    	
    	if(NEGATION_SYMBOL.equals(getName()))
		{
    		boolean a = aStack.pop();
    		
    		result = !a;
    		
		}
    	else if(CONJUNCTION_SYMBOL.equals(getName()))
		{
    		boolean b = aStack.pop();
    		boolean a = aStack.pop();
    		
    		result = (a && b);
    		
		}
    	else if(DISJUNCTION_SYMBOL.equals(getName()))
		{
    		boolean b = aStack.pop();
    		boolean a = aStack.pop();
    		
    		result = (a || b);
    		
		}
    	else if(IMPLICATION_SYMBOL.equals(getName()))
		{
    		boolean b = aStack.pop();
    		boolean a = aStack.pop();
    		
    		if( (!a && b) || (!a && !b) || (a && b) )
    			result = true;
    		else
    			result = false;
    		
    		
		}
    	else if(EQUIVALENCE_SYMBOL.equals(getName()))
		{
    		boolean b = aStack.pop();
    		boolean a = aStack.pop();
    		
    		if( (a && b) || (!a && !b) )
    			result = true;
    		else
    			result = false;
    	
		}
    	
    	aStack.push(result);
    	return result;
    }
    
    
    
    
    /**
     * Gets the precedence value of this objects operator type.
     * 
     * @return an int indicating the Precedence
     */
    public int getPrecedence()
    {
        return fPrecedence;
    }
    
    
}
