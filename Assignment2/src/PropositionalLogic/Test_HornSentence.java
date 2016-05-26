package PropositionalLogic;
import static org.junit.Assert.*;

import org.junit.Test;

public class Test_HornSentence {


	
	
	@Test
	public void HornSentence_RecognitionTest() throws Exception {
		
		Sentence lSentence;
		
		lSentence = new Sentence("Big&Tall=>Giant");
		assertEquals( true, lSentence.isHornClause() );
		
		lSentence = new Sentence("Big&Tall<=>Giant");
		assertEquals( false, lSentence.isHornClause() );
		
		lSentence = new Sentence("a&b=>~c");
		assertEquals( false, lSentence.isHornClause() );
		
		lSentence = new Sentence("a&b=>c\\/d");
		assertEquals( false, lSentence.isHornClause() );
		
	}
	
	
	
	
	@Test
	public void HornSentence_SetupLiteralsTest() throws Exception {
		
		Sentence lSentence = new Sentence("Big&Tall=>Giant");
		assertEquals( true, lSentence.isHornClause() );
		
		String lOtherLiterals = "";
		for( int k=0; k<lSentence.otherLiteralsLength(); k++ )
		{
			if(k!=0)
				lOtherLiterals += " ";
			lOtherLiterals += lSentence.getLiteral(k).getName(); // TODO Why does getFromSentence work here?
		}
		
		// make sure positive literal is correct
		assertEquals(lSentence.getLiteral(lSentence.literalsLength()-1).getName(), lSentence.getPositiveLiteral().getName());
		assertEquals("Giant", lSentence.getPositiveLiteral().getName());
		
		// check other literals
		assertEquals(2, lSentence.otherLiteralsLength());
		assertEquals(lSentence.literalsLength()-1, lSentence.otherLiteralsLength());	// Check the number of elements in the sentence is exact
		assertEquals("Big Tall", lOtherLiterals);
		
	}
	
	
	

}
