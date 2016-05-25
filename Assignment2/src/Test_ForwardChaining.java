import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Test_ForwardChaining {

	@Test
	public void ForwardChaining_TestNonHornSentence_Simple() throws Exception {
				
		ArrayList<Sentence> lKB = new ArrayList<>();
		lKB.add( new Sentence("p2=> p3&g") );

		Literal lQueryLiteral = lKB.get(0).getLiteral( lKB.get(0).literalsLength()-1 );
		assertEquals( lQueryLiteral.getName(), "g" );	// fail safe to check we're actually trying to solve for the correct literal
		
		ForwardChaining lMethod = new ForwardChaining(lKB, lQueryLiteral);
		
		assertFalse( lMethod.prepareSolution() );	// check it doesn't solve it
		
	}
	
	
	@Test
	public void ForwardChaining_TestNonHornSentence_Complex() throws Exception {
				
		ArrayList<Sentence> lKB = new ArrayList<>();
		lKB.add( new Sentence("p2&w\\/~p5=> p3&g\\/h<=>y") );
		
		Literal lQueryLiteral = lKB.get(0).getLiteral( lKB.get(0).literalsLength()-1 );
		assertEquals( lQueryLiteral.getName(), "y" );	// fail safe to check we're actually trying to solve for the correct literal
		
		ForwardChaining lMethod = new ForwardChaining(lKB, lQueryLiteral);
		
		assertFalse( lMethod.prepareSolution() );	// check it doesn't solve it
		
	}
	
	
	@Test
	public void ForwardChaining_TestNonHornSentence_MultipleImplications() throws Exception {
				
		ArrayList<Sentence> lKB = new ArrayList<>();
		lKB.add( new Sentence("p2=>p3&g=>y") );

		Literal lQueryLiteral = lKB.get(0).getLiteral( lKB.get(0).literalsLength()-1 );
		assertEquals( lQueryLiteral.getName(), "y" );	// fail safe to check we're actually trying to solve for the correct literal
		
		ForwardChaining lMethod = new ForwardChaining(lKB, lQueryLiteral);
		
		assertFalse( lMethod.prepareSolution() );	// check it doesn't solve it
		
	}
	
	
	

}
