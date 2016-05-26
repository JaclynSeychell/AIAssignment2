package PropositionalLogic;
import static org.junit.Assert.*;

import org.junit.Test;

public class Test_TruthTable {


	@Test
	public void CreateTruthTableTest() {
		
		String eol = System.getProperty("line.separator");
		String TT = "";
		TT += "000" + eol;
		TT += "001" + eol;
		TT += "010" + eol;
		TT += "011" + eol;
		TT += "100" + eol;
		TT += "101" + eol;	// row 5
		TT += "110" + eol;
		TT += "111" + eol;	// row 7
		
		InferenceEngine iEngine = new InferenceEngine("Big&Tall=>Giant;Male\\/Female<=>Human;Big&Male&Giant=>Friendly;Giant&~Female=>Male&Big;");
		
		TruthTable TruthT = new TruthTable(iEngine.fKnowledgeBase, iEngine.fLiterals);
		TruthT.createTruthTable(3);
		
		assertEquals(TT, TruthT.getTruthTable());
	}

	
	
	@Test
	public void TruthTableTest_Basic() {
		
		InferenceEngine iEngine = new InferenceEngine("a\\/b=>c;~b=>a;");
		TruthTable TruthT = new TruthTable(iEngine.fKnowledgeBase, iEngine.fLiterals);
		
		assertTrue( TruthT.solve() );					// check solution reports as solvable
		
		assertEquals( "2", TruthT.getSolution() );		// check 2 rows are reported as true - row 5 and 7 (zero indexed)
														// see truth table in above test for easy reference
		
		
		iEngine = new InferenceEngine("a\\/b=>c;~b=>a;b;a;");
		TruthT = new TruthTable(iEngine.fKnowledgeBase, iEngine.fLiterals);
		
		assertTrue( TruthT.solve() );
		assertEquals( "1", TruthT.getSolution() );
		
	}
	
	
	@Test
	public void TruthTableTest_Example() {
		
		InferenceEngine iEngine = new InferenceEngine("p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;");
		TruthTable TruthT = new TruthTable(iEngine.fKnowledgeBase, iEngine.fLiterals);
		
		assertTrue( TruthT.solve() );					// check solution reports as solvable
		
		assertEquals( "3", TruthT.getSolution() );	// check it reports correct number of rows
		
	}
	

}
