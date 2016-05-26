package PropositionalLogic;
import static org.junit.Assert.*;

import org.junit.Test;

public class Test_General {

	@Test
	public void General_TestForwardChainingSuccess() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		assertTrue( iEngine.solve("d", "FC") );
		
	}
	
	@Test
	public void General_TestForwardChainingFailure() throws Exception {
		
		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		assertFalse( iEngine.solve("k", "FC") );
		
	}

}
