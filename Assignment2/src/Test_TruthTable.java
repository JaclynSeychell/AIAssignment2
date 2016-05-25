import static org.junit.Assert.*;

import org.junit.Test;

public class Test_TruthTable {

	
	@Test
	public void CreateTruthTableTest() {
		
		String TT = "";
		TT += "00";
		TT += "01";
		TT += "10";
		TT += "11";
		
		InferenceEngine iEngine = new InferenceEngine("Big&Tall=>Giant;Male\\/Female<=>Human;Big&Male&Giant=>Friendly;Giant&~Female=>Male&Big;");
		
		TruthTable TruthT = new TruthTable(iEngine.fKnowledgeBase, iEngine.fLiterals);
		TruthT.createTruthTable(2);
		
		assertEquals(TT, TruthT.getTruthTable());
	}
	

}
