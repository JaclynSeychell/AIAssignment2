/* Abstract class created to set up each of the different 
 * token symbols in order to be evaluated. This is the "base"
   that each symbol shall be evaluated and found on*/

public abstract class Token {
	
	public final static int NEGATION = 1;
	public final static int CONJUNCTION = 2;
	public final static int DISJUNCTION = 3;
	public final static int IMPLICATION = 4;
	public final static int EQUIVALENCE = 5;
	public final static int LEFT_PARENTHESIS = 20;
	
	protected String fSymbol;
	protected boolean fIsConditional = false;
	protected int fType, fPosition = 0;
	
	abstract public int getPrecedence();
	
	public int getPosition()
	{
		return fPosition;
	}
	
	public void setPosition(final int aPosition)
	{
		this.fPosition = aPosition;	
	}
	
	public int getType()
	{
		return fType;
	}
	
	public String getSymbol()
	{
		return fSymbol;
	}
	
	public boolean isConditional()
	{
		return fIsConditional;
	}

}
