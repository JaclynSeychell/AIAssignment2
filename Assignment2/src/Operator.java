
public class Operator extends ProLogic {

    // Operators with lower precedence values get calculated first
	private final static int NEGATION = 1;
	private final static int DISJUNCTION = 2;
	private final static int CONJUNCTION = 3;
	private final static int IMPLICATION = 4;
	//public final static int EQUIVALENCE = 5;
	private final static int LEFT_PARENTHESIS = 20; // should always have the highest value to cause it to remain low in the stack until the right parenthesis is seen and all operators above are popped off
    
    
    //Determines what precedence the operator has (think BODMAS)
    private int fPrecedence;
    
    
    // return if the string is an operator or not
    public static boolean isOperator(String aName)
    {
    	switch(aName)
        {
        	case "-":	return true;
        	case "/":	return true;
        	case "&":	return true;
        	case "=>":	return true;
        	default:	return false;
        }
    }
    
    public Operator(String aName)
    {
        super(aName);
        
        switch(aName)
        {
        	case "-":	this.fPrecedence = NEGATION;	break;
        	case "/":	this.fPrecedence = DISJUNCTION;	break;
        	case "&":	this.fPrecedence = CONJUNCTION;	break;
        	case "=>":	this.fPrecedence = IMPLICATION;	break;
        	default:	break;
        }
        
    }
    
    public int getPrecedence()
    {
        return fPrecedence;
    }    
}
