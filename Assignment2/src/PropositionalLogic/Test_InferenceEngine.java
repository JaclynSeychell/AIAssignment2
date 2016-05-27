package PropositionalLogic;
import static org.junit.Assert.*;

import org.junit.Test;

public class Test_InferenceEngine {

	
	@Test
	public void KnowledgeBaseInterpretation() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("Big&Tall=>Giant;Male\\/Female<=>Human;Big&Male&Giant=>Friendly;Giant&~Female=>Male&Big;");
		assertEquals( "BigTall&Giant=>;MaleFemale\\/Human<=>;BigMaleGiant&&Friendly=>;GiantFemale~&MaleBig&=>;", iEngine.getKnowledgeBaseString() );
		
	}
	
	
	
	@Test
	public void LiteralInterpretation() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("Big&Tall=>Giant;Male\\/Female<=>Human;Big&Male&Giant=>Friendly;Giant&~Female=>Male&Big;");
		assertEquals( "Big;Tall;Giant;Male;Female;Human;Friendly;", iEngine.getLiteralsString() );
		
	}
	
	
	
	@Test
	public void LiteralUnifying() throws Exception {
		
		// TODO: iterate through sentence literals to make sure they are the same objects as those in the Inference Engine's Literals list.
		InferenceEngine iEngine = new InferenceEngine("Big&Tall=>Giant;Male\\/Female<=>Human;Big&Male&Giant=>Friendly;Giant&~Female=>Male&Big;");
		
		for(int k=0; k<iEngine.fKnowledgeBase.size(); k++)
		{
			Sentence lSentence = iEngine.fKnowledgeBase.get(k);
			
			for(int j=0; j<lSentence.literalsLength(); j++)
			{
				Literal lLiteral = lSentence.getLiteral(j);
				
				for(Literal lLitRef : iEngine.fLiterals)
				{
					if(lLiteral.getName().equals(lLitRef.getName()))
					{
						assertEquals(lLitRef,lLiteral);				// assert that all literals of same type are references of the same object
					}
				}
			}
		}
		
	}
	
	
	@Test
	public void ForwardChainingSuccess() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		assertTrue( iEngine.solve("d", "FC") );
		
	}
	
	@Test
	public void ForwardChainingFailure() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		assertFalse( iEngine.solve("k", "FC") );	// check returns failure for solving a literal not in the sentence
		
	}
	
	@Test
	public void BackwardChainingSuccess() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		assertTrue( iEngine.solve("d", "BC") );
		
	}
	
	@Test
	public void BackwardChainingFailure() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		assertFalse( iEngine.solve("k", "BC") );	// check returns failure for solving a literal not in the sentence
		
	}
	
	
	@Test
	public void TruthTableExampleSolve() throws Exception {

		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		
		assertTrue( iEngine.solve("d", "TT") );					// check it is solved
		assertEquals( "YES: 3", iEngine.getSolveResult() );
		
	}
	
	
	@Test
	public void ForwardChainingExampleSolve() throws Exception {

		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		
		assertTrue( iEngine.solve("d", "FC") );									// check it is solved
		assertEquals( "YES: a, b, p2, p3, p1, d", iEngine.getSolveResult() );	// check it returns the correct chain
		
	}
	
	
	@Test
	public void BackwardChainingExampleSolve() throws Exception {

		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		
		assertTrue( iEngine.solve("d", "BC") );								// check it is solved
		assertEquals( "YES: p2, p3, p1, d", iEngine.getSolveResult() );		// check it returns the correct chain
		
	}
	
	
	
	

}
