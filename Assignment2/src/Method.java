import java.util.ArrayList;

public abstract class Method {
	
    protected ArrayList<Sentence> fKB;	// to store the knowledgebase passed in
    protected String fResult;			// to store a string of the results to be printed to the screen
    
    
    public Method(ArrayList<Sentence> aKB)
    {
    	fKB = aKB;
    }
    
    public String getResult()
    {
        return fResult;
    }
    
    
    // to be overridden by extending classes
    public boolean solve()
    {
    	// override this function for each method that extends this class
    	return true;
    }
}