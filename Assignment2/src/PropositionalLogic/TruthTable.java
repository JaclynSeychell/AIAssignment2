package PropositionalLogic;
import java.util.ArrayList;
import java.util.Stack;

/**
 * TruthTable extends Method as a way to calculate a solution to the desired query literal
 * based on the KnowledgeBase initially passed into the InferenceEngine class.
 * It is automatically called from the InferenceEngine class.
 * It can solve based on any sentence format.
 * 
 * @author Jaclyn
 */
public class TruthTable extends Method {
	
	
	private boolean fLiteralsTable[][];		// TruthTable values
	private ArrayList<Literal> fLiterals;
	private ArrayList<Sentence> fSentences;
	private int fTrueValues;				// Number of valid rows in the TruthTable
	
	
	/**
	 * Initialises the KnowledgeBase to be used by the solve method.
	 * 
	 * @param aKB takes a ArrayList of sentences
	 * @param aLiterals takes an ArrayList of all Literals in the KnowledgeBase
	 */
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
	
	
	
	
	
	
	
	/**
	 * Finds number of rows in the TruthTable that are valid.
	 * Saves that number in fTrueValues field.
	 * 
	 * @return A boolean indicating whether a solution was successfully found
	 */
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
	
	
	
	
	
	/**
     * Creates a human readable summary of the solution previously found by the solve method (the number of valid rows).
     * 
     * @return String as a human readable summary of the previously found solution.
     */
	@Override
	public String getSolution()
    {
        return Integer.toString(fTrueValues);
    }
	
	
	
	/**
	 * Creates a TruthTable of all possible boolean value permutations for the supplied Literals.
	 * result is accessible from the fLiteralsTable field.
	 * 
	 * @param totalCols requires the number of Literals the truthtable should be built to support
	 */
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
	
	
	/**
	 * Looks up a boolean value at a specified cell of the TruthTable calculated in createTruthTable.
	 * 
	 * @param row is an integer indicating the row of the TruthTable
	 * @param item is a Literal that is resolved to the column in the TruthTable containing the correct value. 
	 * @return The boolean value at the corresponding cell in the TruthTable
	 */
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
	
	
	/**
	 * Get's a String that is a human readable representation of the TruthTable with line breaks after each row.
	 * Only used for testing.
	 * 
	 * @return A String
	 */
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
	
	
	/**
	 * Get's a 1 or 0 String that is a human readable representation of all
	 * cells in a particular row of the TruthTable.
	 * Only used for testing.
	 * 
	 * @return A String
	 */
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
