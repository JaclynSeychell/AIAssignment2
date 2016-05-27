package PropositionalLogic;
import java.util.ArrayList;

/**
 * Method is an abstract class to be extended by methods of solving a KnowledgeBase and Query
 * 
 * @author Dale
 */
public abstract class Method {
	
    protected ArrayList<Sentence> fKB;	// to store the knowledgebase passed in
    protected boolean solutionPrepared = false;
    
    
    /**
     * Initialises the KnowledgeBase.
     * 
     * @param aKB is an ArrayList of Sentences
     */
    public Method(ArrayList<Sentence> aKB)
    {
    	fKB = aKB;
    }
        
    
    /**
     * Solves KnowledgeBase as per extending class implementation.
     * Is overriding by extending classes.
     * 
     * @return boolean to indicate whether a solution was found.
     */
    public boolean solve()
    {
    	// override this function for each method that extends this class
    	return false;
    }
    
    
    
    /**
     * Creates a human readable summary of the solution previously found by the solve method.
     * Is overriding by extending classes.
     * 
     * @return String as a human readable summary of the previously found solution.
     */
    public String getSolution()
    {
        return "";
    }
    
    
    
}