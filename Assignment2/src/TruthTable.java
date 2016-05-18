import java.util.ArrayList;
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
	private boolean fLiteralsTable[][]; //[index][row(t/f)]
	private boolean lSentencesTable[][]; //[index][row(t/f)]
	private Stack lKnownLiterals = new Stack();
	
	
	public TruthTable(ArrayList<Sentence> aKB, ArrayList<Literal> aLiterals)
	{
		
		
		// generate full truth table
		fLiterals = aLiterals; //	[literalIndex]
		fLiteralsTable = new boolean[fLiterals.length][2^fLiterals.length]; //		[literalIndex][row(t/f)]
		
		// do same thing for sentences in aKB
		fSentences = aKB;
		fSentencesTable = new boolean[fSentences.length][2^fLiterals.length];
		
		//loop or use recursion to populate fLiteralValues array
		//
		//
		
		
		
		
		
		
		// look for correct rows
		
		// loop 2^fLiterals.length times
			
			// loop fSentence.length times
		
				// init stack =0 
		
				// loop through each MathLogic in sentence
					
					// if(temp == instanceof Literal)
						// add to stack
					
					// else if an Operator
		
						// run that operation on the stack (returns result onto stack)				
						temp.eval(stack);
						
						/*
						 * Don't do this
						if(temp.getName() == temp.NEGATION_SYMBOL)
						{
							// first ting off stack
							// negate it
							// put it back on the stack
						} else (){
							
						}
						*/
		
				// end loop
		
				// store if row is true or false
		
			// end sentences loop
		
		// end row loop
				
		
		
		
		
		/*
		
		
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
		
		
		*/
		
		
		
		
		
		
		
		
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
