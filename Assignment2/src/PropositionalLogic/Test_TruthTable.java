package PropositionalLogic;
import static org.junit.Assert.*;

import org.junit.Test;

public class Test_TruthTable {


	@Test
	public void CreateTruthTable() {
		
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
	public void Basic1() {
		
		InferenceEngine iEngine = new InferenceEngine("a\\/b=>c;~b=>a;");
		TruthTable TruthT = new TruthTable(iEngine.fKnowledgeBase, iEngine.fLiterals);
		
		assertTrue( TruthT.solve() );					// check solution reports as solvable
		
		//System.out.println(TruthT.getTruthTable());	// see truth table in above test for easy reference
		
		assertEquals( "3", TruthT.getSolution() );		// check solution reports correct number of valid rows
		
		
		
	}
	
	@Test
	public void Basic2() {
		
		InferenceEngine iEngine = new InferenceEngine("a\\/b=>c;~b=>a;b;a;");
		TruthTable TruthT = new TruthTable(iEngine.fKnowledgeBase, iEngine.fLiterals);
		
		assertTrue( TruthT.solve() );					// check solution reports as solvable
		assertEquals( "1", TruthT.getSolution() );		// check solution reports correct number of valid rows
		
	}
	
	
	
	

}
