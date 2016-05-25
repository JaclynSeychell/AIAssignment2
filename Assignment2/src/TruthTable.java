import java.util.ArrayList;
import java.util.Stack;

public class TruthTable extends Method {
	
	//Get literals from the sentence, place into a stack in RPN
	//Create  Array, place individual literals into one array (a,b,c,etc)
	//Create Array, place sentences literals into array : done by using the stack
	//popping literal off stack, searching for in first array, repeat until you find condtion
	//evaluate for T/F of each sentence
	//calculate all the T for the whole KB
	
	private boolean fLiteralsTable[][]; //[index][row(t/f)] TRUTH TABLE < -
	private ArrayList<Literal> fLiterals;
	private ArrayList<Sentence> fSentences;
	private int trueValues;
	
	public TruthTable(ArrayList<Sentence> aKB, ArrayList<Literal> aLiterals)
	{
		super(aKB);
		
		// generate full truth table
		fLiterals = aLiterals; //	[literalIndex]
		
		// do same thing for sentences in aKB
		fSentences = aKB;
		//fSentencesTable = new boolean[fSentences.size()][(int) Math.pow(2,fLiterals.size())];
	
		trueValues = 0;
		
		//loop or use recursion to populate fLiteralValues array
	
		createTruthTable(fLiterals.size()); 
		
	}
	
	
	
	
	
	private Boolean getValueFromTT(int row, Literal item)
	{
		//find integer that correlates to literal passed in
		//loop through fLiterals until found
		
		for (int i = 0; i < fLiterals.size(); i++)
		{
			if( fLiterals.get(i) == item)
			{
				//index = column of truth table
				//return bool in truthtable at [col][row]
				return fLiteralsTable[row][i];
			}
		}
		return true;	
	}

	
	
	
	
	public void createTruthTable(int totalCols) 
	{
		//uses to binary string to convert to 1 and 0
		int totalRows =  (int)Math.pow(2,totalCols);
		
		fLiteralsTable = new boolean[totalRows][totalCols];
		
		for (int cel = 0 ; cel < (totalRows*totalCols) ; cel++) 
		{
			int row = (int)Math.ceil(cel / totalCols);
			int col = cel%totalCols;
			
			String s = Integer.toBinaryString(cel);
			
			while (s.length() <= totalCols) 
			{
			    s = '0'+s;
		    }
		   
		   if( s.charAt(col) == '1')
		   {
			   fLiteralsTable[row][col] = true;
		   }
		   else
		   {
			   fLiteralsTable[row][col] = false;
		   }
		   
		}
	}
	
	
	
	public String getTruthTable()
	{
		String result = "";
		for (int row = 0; row < fLiteralsTable.length; row++)
		{
			for (int col= 0; col < fLiteralsTable[row].length; col++)
			{
				if(fLiteralsTable[row][col])
					result+="1";
				else
					result+="0";
			}
		}
		return result;
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
	
	
	
	
	
	
	// look for correct rows
	// loop 2^fLiterals.length times
	@Override
	public boolean prepareSolution()
	{
		
		
		for( int row = 0; row < fLiteralsTable.length; row++)
		{
			boolean rowValid = true;
			
			for (int sIndex = 0; sIndex < fSentences.size(); sIndex++)
			// loop fSentence.length times
			{
				Sentence currentSentence = fSentences.get(sIndex);
				Stack<Boolean> lCurrentLiterals = new Stack<Boolean>();
				
				
				// loop through each ProLogic in sentence(get from sentence)
				for( int k = 0; k < currentSentence.sentenceLength(); k++)
				{
					ProLogic item = currentSentence.getFromSentence(k);
					
					// if(temp == instanceof Literal)
					// add to stack
					if( item instanceof Literal)
					{
						lCurrentLiterals.push( getValueFromTT(row,(Literal)item) );
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
					System.out.println("Sentence invalid");
					
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

		return solutionPrepared;		
	}
	
	
}
