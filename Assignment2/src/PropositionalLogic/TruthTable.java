package PropositionalLogic;
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
	private int fTrueValues;
	
	public TruthTable(ArrayList<Sentence> aKB, ArrayList<Literal> aLiterals)
	{
		super(aKB);
		
		// generate full truth table
		fLiterals = aLiterals; //	[literalIndex]
		
		// do same thing for sentences in aKB
		fSentences = aKB;
		//fSentencesTable = new boolean[fSentences.size()][(int) Math.pow(2,fLiterals.size())];
	
		fTrueValues = 0;
		
		//loop or use recursion to populate fLiteralValues array
	
		createTruthTable(fLiterals.size()); 
		
	}
	
	
	
	
	
	
	
	// look for correct rows
	// loop 2^fLiterals.length times
	@Override
	public boolean solve()
	{
		
		for( int row = 0; row < fLiteralsTable.length; row++)
		{
			boolean rowValid = true;
			
			for (int sIndex = 0; sIndex < fSentences.size(); sIndex++)
			// loop fSentence.length times
			{
				Sentence curSentence = fSentences.get(sIndex);
				Stack<Boolean> lCurrentLiterals = new Stack<Boolean>();
				
				// loop through each ProLogic in sentence(get from sentence)
				for( int k = 0; k < curSentence.sentenceLength(); k++)
				{
					ProLogic item = curSentence.getFromSentence(k);
					
					// if(temp == instanceof Literal)
					// add to stack
					if( item instanceof Literal)
					{
						lCurrentLiterals.push( getValueFromTT(row,(Literal)item) );
						//System.out.print( item.getName() + "=" + getValueFromTT(row,(Literal)item) + ", " );
					}
					// else if an Operator
					else if (item instanceof Operator)
					{
						// run that operation on the stack (returns result onto stack)				
						((Operator) item).eval(lCurrentLiterals);
					}
					
				// end current sentence loop
				} 
				
				// if the sentence doesn't result in a single true or false, the sentence has something wrong with it
				if(lCurrentLiterals.size() != 1)
					System.out.println("Sentence invalid");
					
				if(lCurrentLiterals.peek()== false)
				{
					rowValid = false;
				}
				
			// end all sentences loop
			}
			
			// increase valid row count if valid
			if(rowValid)
			{
				//System.out.println(row);
				//System.out.println(getRowValues(row));
				fTrueValues++;
			}
			
		// end row loop
		}

		
		// If there are valid rows, note that.
		if(fTrueValues>0)
			solutionPrepared = true;
		
		return solutionPrepared;
	}
	
	
	
	
	

	@Override
	public String getSolution()
    {
        return Integer.toString(fTrueValues);
    }
	
	
	
	
	public void createTruthTable(int totalCols) 
	{
		int totalRows =  (int)Math.pow(2,totalCols);
		
		fLiteralsTable = new boolean[totalRows][totalCols];
		
		for (int row = 0 ; row < totalRows ; row++) 
		{
			
			String s = Integer.toBinaryString(row);
			
			while (s.length() < totalCols)
			    s = '0'+s;
			
			//System.out.println(s);
			
			for(int col=0; col < totalCols; col++)
			{

			   if( s.charAt(col) == '1')
				   fLiteralsTable[row][col] = true;
			   else
				   fLiteralsTable[row][col] = false;
			   
			}
		   
		}
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
			result += System.getProperty("line.separator");
		}
		return result;
	}
	
	
	
	private String getRowValues(int aRow)
	{
		String result = "";
		
		for (int col= 0; col < fLiteralsTable[aRow].length; col++)
		{
			if(col!=0)
				result+=", ";
			
			result += fLiterals.get(col).getName() + "=";
			
			if(fLiteralsTable[aRow][col])
				result+="1";
			else
				result+="0";
			
		}
		
		
		return result;
	}
		
	
	
	
	
	
	
	
	
	

	
	
}
