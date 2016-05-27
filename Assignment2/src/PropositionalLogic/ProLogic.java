package PropositionalLogic;


/**
 * ProLogic is an abstract class representing any type of item that can be contained within a Sentence.
 * 
 * @author Dale
 *
 */
public abstract class ProLogic {
	
    protected String fName; 	// the human readable representation of the ProLogic item
    
    /**
     * Initialise the string name of the object.
     * 
     * @param aName The human readable name.
     */
    public ProLogic(String aName)
    {
        this.fName = aName;
    }
    
    
    
    /**
     * Gets the human readable name.
     * 
     * @return String of the human readable name
     */
    public String getName()
    {
        return fName;
    }
}
