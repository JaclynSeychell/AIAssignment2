import java.util.ArrayList;
import java.util.Stack;

public class TruthTable extends Method {
	
	//Get literals from the sentence, place into a stack in RPN
	//Create  Array, place individual literals into one array (a,b,c,etc)
	//Create Array, place sentences literals into array : done by using the stack
	//popping literal off stack, searching for in first array, repeat until you find condtion
	//evaluate for T/F of each sentence
	//calculate all the T for the whole KB
	
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
	
		printTruthTable(fLiterals.size());
		printTruthTable(fSentences.size());

		// look for correct rows
		
		// loop 2^fLiterals.length times
		for( int row = 0; row < (int) Math.pow(2,fLiterals.size()); row++)
		{
			boolean rowValid = true;
			
			for (int j = 0; j < fSentences.size(); j++)
			// loop fSentence.length times
			{
				// init stack =0 
				Stack<Boolean> lCurrentLiterals = new Stack<Boolean>();
				
				// loop through each MathLogic in sentence(get from sentence)
				
				Sentence currentSentence = fSentences.get(j);
				for( int k = 0; k < currentSentence.sentenceLength(); k++)
				{
					ProLogic item = currentSentence.getFromSentence(k);
					
					// if(temp == instanceof Literal)
					// add to stack
					if( item instanceof Literal)
					{
						lCurrentLiterals.push(getValueFromTT((Literal)item ,row));
					}
					// else if an Operator
					else if (item instanceof Operator)
					{
						// run that operation on the stack (returns result onto stack)				
						((Operator) item).eval(lCurrentLiterals);
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
	}
	
	private Boolean getValueFromTT(ProLogic item, int row)
	{
		//find integer that correlates to literal passed in
		//loop through fLiterals until found
		
		for (int i = 0; i < fLiterals.size(); i++)
		{
			if( fLiterals.get(i) == item)
			{
				//index = column of truth table
				//return bool in truthtable at [col][row]
				return fLiteralsTable[i][row];
			}
		}
		return true;	
	}

	public void printTruthTable(int n) 
	{
		//uses to binary string to convert to 1 and 0
		for (int i = 0 ; i != (1 << n) ; i++) 
		{
		    String s = Integer.toBinaryString(i);
		    while (s.length() != n) 
		    {
		        s = '0'+s;
		    }
		    System.out.println(s);
		}
	}
		

	public void countTrueValues()
	{
		//check to see if the truth table result has any true values
		//output yes or no and value 
		if(trueValues >= 1)
		{
			System.out.println("YES: "+ trueValues);
		}
		else
		{
			System.out.println("NO: " + trueValues);
		}
	}
	
}
