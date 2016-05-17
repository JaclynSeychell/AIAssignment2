import java.util.Stack;

public class TruthTable extends Sentence {
	
	//Get literals from the sentence, place into a stack in RPN
	//Create  Array, place individual literals into one array (a,b,c,etc)
	//Create Array, place sentences literals into array : done by using the stack
	//popping literal off stack, searching for in first array, repeat until you find condtion
	//evaluate for T/F of each sentence
	//calculate all the T for the whole KB
	
	private int fNumberOfLines;
	private int fLiteralsArray [];  //Array for the literals index 
	private int fSentencesArray[]; //Array for the sentences index 
	private boolean lLiteralsTable[][]; //[index][row(t/f)]
	private boolean lSentencesTable[][]; //[index][row(t/f)]
	private Stack lKnownLiterals = new Stack();
	
	
	public TruthTable(Literal aLiteral, ProLogic aProLogic)
	{
		int index;
		int Lvalue;
		int Svalue;
		
		//Initialising the arrays to hold the same index but different value(T/F)
		lLiteralsTable[index][Lvalue];
		lSentencesTable[index][Svalue]; 
		
		
		//Add the sentences to the array 
		for(int i = 0; i < fSentencesArray.length; i++)
		{
			getFromSentence(fSentencesArray[i]); 
		}
		//Add the literals to the array 
	
		for (int j = 0; j < fLiteralsArray.length; j++)
		{
			getLiteral(fLiteralsArray[j]);
		}	
		
		//Add Literals to the stack to be checked off 
		for (int k = 0; k < lKnownLiterals.size(); k++)
		{
			lKnownLiterals.push(addLiteral(aLiteral));
		}
	}
	
	
	
	
	//generate T/F (1 / 0) 
	/*public void generate(int i, int n)
	{
		if(i == (1 << n) )
			return;
		else
		{
			String temp = Integer.toBinaryString(i);
			while(temp.length() < n)
			{
				temp = '0' + temp;
			}
			generate(i+1,n);
		}
	}
	*/
	
	
	
	

	

	
}
