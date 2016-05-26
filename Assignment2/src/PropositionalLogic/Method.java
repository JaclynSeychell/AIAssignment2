package PropositionalLogic;
import java.util.ArrayList;

public abstract class Method {
	
    protected ArrayList<Sentence> fKB;	// to store the knowledgebase passed in
    protected boolean solutionPrepared = false;
    
    public Method(ArrayList<Sentence> aKB)
    {
    	fKB = aKB;
    }
        
    
    // to be overridden by extending classes
    public boolean solve()
    {
    	// override this function for each method that extends this class
    	return false;
    }
    
    // to be overridden by extending classes
    public String getSolution()
    {
        return "";
    }
    
    
    
}