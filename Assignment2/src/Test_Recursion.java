import static org.junit.Assert.*;

import org.junit.Test;

public class Test_Recursion {

	@Test
	public void test() {
		
		InferenceEngine iEngine = new InferenceEngine("Big&Tall=>Giant;Male\\/Female<=>Human;Big&Male&Giant=>Friendly;Giant&~Female=>Male&Big;");
		
		TruthTable TruthT = new TruthTable(iEngine.fKnowledgeBase, iEngine.fLiterals);
		
		TruthT.createTruthTable(2);
		//TruthT.printTruthTable();
	}
	

}
