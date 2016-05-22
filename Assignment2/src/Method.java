import java.util.ArrayList;

public abstract class Method {
	
    protected ArrayList<Sentence> fKB;	// to store the knowledgebase passed in
    protected boolean fReadyToSolve = false;
    
    public Method(ArrayList<Sentence> aKB)
    {
    	fKB = aKB;
    }
    
    // to be overridden by extending classes
    public String printSolutionList()
    {
        return "";
    }
    
    
    // to be overridden by extending classes
    public boolean isSolvable()
    {
    	// override this function for each method that extends this class
    	return false;
    }
}