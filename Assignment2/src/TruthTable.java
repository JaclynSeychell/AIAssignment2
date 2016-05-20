import java.util.ArrayList;
import java.util.Stack;

public class TruthTable extends Method {
	
	//Get literals from the sentence, place into a stack in RPN
	//Create  Array, place individual literals into one array (a,b,c,etc)
	//Create Array, place sentences literals into array : done by using the stack
	//popping literal off stack, searching for in first array, repeat until you find condtion
	//evaluate for T/F of each sentence
	//calculate all the T for the whole KB
	
	
	//private int fLiteralsArray [];  //Array for the literals index 
	//private int fSentencesArray[]; //Array for the sentences index 
	private boolean fLiteralsTable[][]; //[index][row(t/f)]
	private boolean fSentencesTable[][]; //[index][row(t/f)]
	
	
	private ArrayList<Literal> fLiterals;
	private ArrayList<Sentence> fSentences;
	private int trueValues;
	
	public TruthTable(ArrayList<Sentence> aKB, ArrayList<Literal> aLiterals)
	{
		super(aKB);
		
		// generate full truth table
		fLiterals = aLiterals; //	[literalIndex]
		fLiteralsTable = new boolean[fLiterals.size()][(int) Math.pow(2,fLiterals.size())]; //		[literalIndex][row(t/f)]
		
		// do same thing for sentences in aKB
		fSentences = aKB;
		fSentencesTable = new boolean[fSentences.size()][(int) Math.pow(2,fLiterals.size())];
	
		trueValues = 0;
		
		//loop or use recursion to populate fLiteralValues array
		//
		//
		//printTruthTable(fLiterals.size());
		//printTruthTable(sentenceslength());

		
	/*
		// look for correct rows
		
		// loop 2^fLiterals.length times
		for( int row = 0; row < (int) Math.pow(2,fLiterals.size()); row++)
		{
			boolean rowValid = true;
			
			for (int j = 0; j < sentenceLength(); j++)
			// loop fSentence.length times
			{
				// init stack =0 
				Stack<Boolean> lCurrentLiterals = new Stack<Boolean>();
				
				// loop through each MathLogic in sentence(get from sentence)
					
				for( int k = 0; k < sentenceLength(); k++)
				{
					ProLogic item = getFromSentence(k);
					
					// if(temp == instanceof Literal)
					// add to stack
					if( item instanceof fLiterals)
					{
						//lKnownLiterals.push(k);
						//getValueFromTT(item,row);
						lCurrentLiterals.push(getValueFromTT(item,row));
						
					}
					// else if an Operator
					else if (item instanceof Operator)
					{
						// run that operation on the stack (returns result onto stack)				
						k.eval(lCurrentLiterals);
					}
					
						
				
				// end current sentence loop
				}
				
				if(lCurrentLiterals.size() != 1)
					System.out.println("No sentence found");
					
				if(lCurrentLiterals.peek()== false)
				{
					rowValid = false;
				}
				
			// end all sentences loop
			}
			
			// store if row is true or false
			if(rowValid == true)
			{
				trueValues++;
			}
			
		// end row loop
		}		
		*/
			
		
	}
	
	public  void printTruthTable(int n) {
		
		//Method from the internet, using recursion
		//uses to binary string to convert to 1 and 0
		for (int i = 0 ; i != (1 << n) ; i++) {
		    String s = Integer.toBinaryString(i);
		    while (s.length() != n) {
		        s = '0'+s;
		    }
		    System.out.println(s);
		}
	}
		
		//My method using recursion, calculating the amount of rows need from
		//givin input,  then looping over the amount of rows to print out 
		//alternating of 1 and 0 using mod 2.
	
		/*int rows = (int) Math.pow(2,n);

        for (int i = 0; i < rows; i++) {
            for (int j = n - 1; j >= 0; j--) {
                System.out.print((i/(int) Math.pow(2, j))%2 + " ");
            }
            System.out.println();
        }
	}
   */
	
	
	

	

	
}
