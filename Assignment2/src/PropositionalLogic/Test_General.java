package PropositionalLogic;
import static org.junit.Assert.*;

import org.junit.Test;

public class Test_General {

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

}
