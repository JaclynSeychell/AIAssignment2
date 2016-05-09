
public class Operator extends ProLogic {

    // Operators with lower precedence values get calculated first
	public final static int NEGATION = 1;
	public final static int DISJUNCTION = 2;
	public final static int CONJUNCTION = 3;
	public final static int IMPLICATION = 4;
	//public final static int EQUIVALENCE = 5;    
    
    
    //Determines what precedence the operator has (think BODMAS)
    private int fPrecedence;
    
    public Operator(int aPrecedence, String aName)
    {
        super(aName);
        
        this.fPrecedence = aPrecedence;
    }
    
    public int precedence()
    {
        return fPrecedence;
    }    
}
