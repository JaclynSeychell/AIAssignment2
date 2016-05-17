
public class Negation extends Token{

	public Negation(final String  aSymbol, final int aPosition)
	{
		fType = Token.NEGATION;
		this.fSymbol = aSymbol;
		this.fPosition = aPosition;
	}
	
	public int getPrecedence()
	{
		return 1;
	}
	
	public TokenValue evaluate(final TokenValue aToken)
	{
		
	}
}
