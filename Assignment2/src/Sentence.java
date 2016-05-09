import java.util.ArrayList;

public class Sentence {
	// DALE TO WRITE
	
	private ArrayList<String> fLiterals;
	private int fLiteralsLength;
	private ArrayList<ProLogic> fRpnSentence; // this shouldn't be called math logic, it should be prologic
	private int fRpnSentenceLength;

	public Sentence(String aSentence)
	{
		fLiteralsLength = 0;
		fRpnSentenceLength = 0;
		// interpret aSentence as and fill out stuff
		
		
	}
	
	
	
	/*
	 * Manipulating Literals
	 */
	
	private void addLiteral()
	{
		
		// if valid add to Literals and
		fLiteralsLength++;
	}
	
	public Literal getLiteral(int aIndex)
	{
		
		Literal temp = new Literal("temp");
		
		return temp;
	}
	
	public int literalsLength()
	{
		return fLiteralsLength;
	}
	
	
	
	
	/*
	 * Manipulating the Sentence
	 */
	
	private void addToSentence(ProLogic aItem)
	{
		
		// if valid add to sentence and
		fRpnSentenceLength++;
	}
	
	public ProLogic getFromSentence(int aIndex)
	{
		
		Literal temp = new Literal("temp");
		return temp;
	}
	
	public int sentenceLength()
	{
		return fRpnSentenceLength;
	}
	
	
	
	
}
