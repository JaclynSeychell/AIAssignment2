import static org.junit.Assert.*;

import org.junit.Test;

public class Test_InferenceEngine {

	
	@Test
	public void InferenceEngine_TestKnowledgeBase() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("Big&Tall=>Giant;Male\\/Female<=>Human;Big&Male&Giant=>Friendly;Giant&~Female=>Male&Big;");
		
		assertEquals( "BigTall&Giant=>;MaleFemale\\/Human<=>;BigMaleGiant&&Friendly=>;GiantFemale~&MaleBig&=>;", iEngine.getKnowledgeBaseString() );
		
	}
	
	
	
	@Test
	public void InferenceEngine_TestLiterals() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("Big&Tall=>Giant;Male\\/Female<=>Human;Big&Male&Giant=>Friendly;Giant&~Female=>Male&Big;");
		
		assertEquals( "Big;Tall;Giant;Male;Female;Human;Friendly;", iEngine.getLiteralsString() );
		
	}
	
	
	
	@Test
	public void InferenceEngine_TestLiteralUnifying() throws Exception {
		
		// TODO: iterate through sentence literals to make sure they are the same objects as those in the Inference Engine's Literals list.
		InferenceEngine iEngine = new InferenceEngine("Big&Tall=>Giant;Male\\/Female<=>Human;Big&Male&Giant=>Friendly;Giant&~Female=>Male&Big;");
		
		assertEquals( "Big;Tall;Giant;Male;Female;Human;Friendly;", iEngine.getLiteralsString() );
		
		
		
	}
	

}
