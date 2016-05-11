import static org.junit.Assert.*;

import org.junit.Test;

public class Test_Sentence {
	
	/*
	@Before
	public void runBeforeEveryTest() {
		// this code runs before every test case. ie
		//simpleMath = new SimpleMath();
	}
	
	@After
	public void runAfterEveryTest() {
		// this code runs before every test case. ie
		//simpleMath = null;
	}
	*/


	//
	
	
	@Test
	public void Sentence_BasicTest() throws Exception {
		
		Sentence lSentence = new Sentence("Big&Tall=>Giant");
		
		String lRpnSentence = "";
		for( int k=0; k<lSentence.sentenceLength(); k++ )
			lRpnSentence += lSentence.getFromSentence(k).getName();
		
		assertEquals(lSentence.sentenceLength(), 5);	// Check the number of elements in the sentence is exact
		assertEquals(lRpnSentence, "BigTall&Giant=>");	// Check is has been ordered correctly
		
		//System.out.println(lRpnSentence);
	}
	
	
	@Test
	public void Sentence_NegationTest() throws Exception {
		
		//Not sure what negation symbol to use
		fail();
		
	}
	
	
	@Test
	public void Sentence_BracketTest() throws Exception {
		
		//Not sure how RPN should affect brackets
		fail();
		
	}
	
	
}
