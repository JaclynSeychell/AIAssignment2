package PropositionalLogic;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Test_BackwardChaining {

	
	
	//
	//	TESTS TO CHECK FOR HORN SENTENCES PROPERLY
	//
	
	@Test
	public void NonHornSentence_Simple() throws Exception {
				
		ArrayList<Sentence> lKB = new ArrayList<>();
		lKB.add( new Sentence("p2=> p3&g") );

		Literal lQueryLiteral = lKB.get(0).getLiteral( lKB.get(0).literalsLength()-1 );
		assertEquals( lQueryLiteral.getName(), "g" );	// fail safe to check we're actually trying to solve for the correct literal
		
		BackwardChaining lMethod = new BackwardChaining(lKB, lQueryLiteral);
		
		assertFalse( lMethod.solve() );	// check it doesn't solve it
		
	}
	
	
	@Test
	public void NonHornSentence_Complex() throws Exception {
				
		ArrayList<Sentence> lKB = new ArrayList<>();
		lKB.add( new Sentence("p2&w\\/~p5=> p3&g\\/h<=>y") );
		
		Literal lQueryLiteral = lKB.get(0).getLiteral( lKB.get(0).literalsLength()-1 );
		assertEquals( lQueryLiteral.getName(), "y" );	// fail safe to check we're actually trying to solve for the correct literal
		
		BackwardChaining lMethod = new BackwardChaining(lKB, lQueryLiteral);
		
		assertFalse( lMethod.solve() );	// check it doesn't solve it
		
	}
	
	
	@Test
	public void NonHornSentence_MultipleImplications() throws Exception {
				
		ArrayList<Sentence> lKB = new ArrayList<>();
		lKB.add( new Sentence("p2=>p3&g=>y") );

		Literal lQueryLiteral = lKB.get(0).getLiteral( lKB.get(0).literalsLength()-1 );
		assertEquals( lQueryLiteral.getName(), "y" );	// fail safe to check we're actually trying to solve for the correct literal
		
		BackwardChaining lMethod = new BackwardChaining(lKB, lQueryLiteral);
		
		assertFalse( lMethod.solve() );	// check it doesn't solve it
		
	}
	
	
	
	//
	// TESTS TO CHECK ON STANDARD SOLVES
	//
	
	@Test
	public void StraightForwardRoute() throws Exception {
				
		// separated onto multiple lines for clarity
		String lKB = "";
		lKB += "p2=> p3;";		// 3
		lKB += "p3 => p1;";		// 2
		lKB += "c => e;";
		lKB += "b&e => f;";
		lKB += "f&g => h;";
		lKB += "p1=>d;";		// 1st
		lKB += "p1&p3 => c;";
		lKB += "a;";
		lKB += "b;";
		lKB += "p2;";			// 4
		
		InferenceEngine iEngine = new InferenceEngine(lKB);
		
		assertTrue( iEngine.solve("d", "BC") );	// returns true if a route is found
		assertEquals( "YES: p2, p3, p1, d", iEngine.getSolveResult() ); 
		
	}
	
	
	@Test
	public void DeceptiveRoute() throws Exception {
		
		// separated onto multiple lines for clarity
		String lKB = "";
		lKB += "p2=> p3;";				// 5
		lKB += "decoy2 => decoy1;";		// 3
		lKB += "decoy1 => p1;";			// 2	(as this is earlier, it will be checked before the line below)
		lKB += "p3 => p1;";				// 4
		lKB += "c => e;";
		lKB += "b&e => f;";
		lKB += "f&g => h;";
		lKB += "p1=>d;";				// 1st
		lKB += "p1&p3 => c;";
		lKB += "a;";
		lKB += "b;";
		lKB += "p2;";					// 6
		
		InferenceEngine iEngine = new InferenceEngine(lKB);
		
		assertTrue( iEngine.solve("d", "BC") );	// returns true if a route is found
		assertEquals( "YES: p2, p3, p1, decoy1, p1, d", iEngine.getSolveResult() ); 
	}
	
	

}
