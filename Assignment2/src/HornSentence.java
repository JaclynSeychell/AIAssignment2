import java.util.ArrayList;

public class HornSentence extends Sentence {
	
	private Literal fPositiveLiteral;
	private ArrayList<Literal> fOtherLiterals;
	
	public HornSentence(String aSentence)
	{
		super(aSentence);	// interpret most in the Sentence class
		
		// initialise fields
		fOtherLiterals = new ArrayList<Literal>();
		
		// interpret other literals and positive literal
		
		fPositiveLiteral = getLiteral(literalsLength()-1);
		
		for(int k=0; k<literalsLength()-1; k++)	// not the last one because that's the positive literal
			if( getLiteral(k) instanceof Literal )
				addOtherLiteral(getLiteral(k));
		
	}
	
	
	
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
