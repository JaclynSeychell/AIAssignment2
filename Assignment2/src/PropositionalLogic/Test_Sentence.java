package PropositionalLogic;
import static org.junit.Assert.*;

import org.junit.Test;

public class Test_Sentence {
	
	
	
	@Test
	public void Sentence_SpacesTest() throws Exception {
		
		Sentence lSentence = new Sentence("Big & ~ Tall => Giant");
		
		String lLiterals = "";
		for( int k=0; k<lSentence.literalsLength(); k++ )
		{
			if(k!=0)
				lLiterals += " ";
			lLiterals += lSentence.getLiteral(k).getName();
		}
		
		assertEquals(3, lSentence.literalsLength());	// Check the number of Literals is exact
		assertEquals("Big Tall Giant", lLiterals);		// Check the literals are exactly the same
		
		
		String lRpnSentence = "";
		for( int k=0; k<lSentence.sentenceLength(); k++ )
			lRpnSentence += lSentence.getFromSentence(k).getName();
		
		assertEquals(6, lSentence.sentenceLength());	// Check the number of elements in the sentence is exact
		assertEquals("BigTall~&Giant=>", lRpnSentence);	// Check is has been ordered correctly
		
	}
	
	
	
	@Test
	public void Sentence_LiteralsTest() throws Exception {
		
		Sentence lSentence = new Sentence("Big&Tall=>Giant");
		
		String lLiterals = "";
		for( int k=0; k<lSentence.literalsLength(); k++ )
		{
			if(k!=0)
				lLiterals += " ";
			lLiterals += lSentence.getLiteral(k).getName();
		}
		
		assertEquals(3, lSentence.literalsLength());	// Check the number of Literals is exact
		assertEquals("Big Tall Giant", lLiterals);		// Check the literals are exactly the same
		
	}

	
	
	@Test
	public void Sentence_BasicTest() throws Exception {
		
		Sentence lSentence = new Sentence("Big&Tall=>Giant");
		
		String lRpnSentence = "";
		for( int k=0; k<lSentence.sentenceLength(); k++ )
			lRpnSentence += lSentence.getFromSentence(k).getName();
		
		assertEquals(5, lSentence.sentenceLength());	// Check the number of elements in the sentence is exact
		assertEquals("BigTall&Giant=>", lRpnSentence);	// Check is has been ordered correctly
		
	}
	
	
	
	@Test
	public void Sentence_NegationTest() throws Exception {
		
		Sentence lSentence = new Sentence("Big&~Tall=>Giant");
		
		String lRpnSentence = "";
		for( int k=0; k<lSentence.sentenceLength(); k++ )
			lRpnSentence += lSentence.getFromSentence(k).getName();
		
		assertEquals("BigTall~&Giant=>", lRpnSentence);	// Check is has been ordered correctly
		
	}
	
	
	
	
	@Test
	public void Sentence_BracketTest() throws Exception {
		
		Sentence lSentence = new Sentence("(A \\/ B) & (C \\/ D) => E");
		
		
		String lLiterals = "";
		for( int k=0; k<lSentence.literalsLength(); k++ )
		{
			if(k!=0)
				lLiterals += " ";
			lLiterals += lSentence.getLiteral(k).getName();
		}
		
		assertEquals("A B C D E", lLiterals);		// Check Literals are correct
		
		
		String lRpnSentence = "";
		for( int k=0; k<lSentence.sentenceLength(); k++ )
			lRpnSentence += lSentence.getFromSentence(k).getName();
		
		
		assertEquals("AB\\/CD\\/&E=>", lRpnSentence);	// Check is has been ordered correctly
		
		
	}
	
	
}
