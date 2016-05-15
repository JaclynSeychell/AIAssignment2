
public class TruthTable extends Sentence {
	
	//Get literals from the sentence, place into a stack in RPN
	//Create  Array, place individual literals into one array (a,b,c,etc)
	//Create Array, place sentences literals into array : done by using the stack
	//popping literal off stack, searching for in first array, repeat until you find condtion
	//evaluate for T/F of each sentence
	//calculate all the T for the whole KB
	
	private Literal fLiteralsArray [];  //Array for the literals 
	private ProLogic fSentencesArray[]; //Array for the sentences
	
	
	public TruthTable()
	{
		for (int i = 0; i < fLiteralsArray.length; i++)
		{
			addLiteral(fLiteralsArray[i]);
		}
		
		
	}
	
	

	
}
