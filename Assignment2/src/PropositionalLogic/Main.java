package PropositionalLogic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class Main {
	
	
	/**
	 * Reads in the Knowledgebase in the specified file, begins an InferenceEngine and solves based on the method defined.
	 * 
	 * @param aMethod A String indicating the method to use to solve the Knowledgebase and Query
	 * @param aFilename A String of the filename containing the KnowledgeBase and query to process
	 */
	public static void processExternalFile(String aMethod, String aFilename)
    {
        File lFile = new File(aFilename);
        
        try
        {
            InputStream in = new FileInputStream(lFile);
            String lLine;
            String lKB= "";	// knowledge base line
            String lQ = "";	// query line
            
            //We are uising UTF-8 as the default is system dependent, which could introduce inconsistencies.
            Reader lReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader lBuffer = new BufferedReader(lReader);
            
            // find information in file
            while ( ( lLine = lBuffer.readLine()) != null) {
            	
            	if(lLine.equals("TELL"))
            	{
            		// the very next line has to be the Knowledge Base
            		lKB = lBuffer.readLine();
            		continue;
            		
            	} else if(lLine.equals("ASK"))
            	{
            		// the very next line has to be the query
            		lQ = lBuffer.readLine().trim();
            		continue;	// could technically break here as long as the TRY is always before the ASK
            		
            	}
            }
            
            lBuffer.close();
            
            // need to make sure if lKB and lQ aren't found/set, it errors and exits
            
            
            InferenceEngine iEngine = new InferenceEngine(lKB);
            iEngine.solve(lQ, aMethod);
            System.out.println(iEngine.getSolveResult());
            
            
        }
        catch(IOException ioe)
        {
            System.out.println(aFilename + " could not be read.");
        }
    }
	
	
	
	
	

	public static void main(String[] args) {
		
		// check for correct number of command line arguments
		if(args.length < 2)
		{
			System.out.println();
			System.out.println("Please use the commandline argument format:  InferenceEngine.jar XX filename");
			System.out.println();
			System.out.println("Where XX equals method of solving:");
			System.out.println("   TT = Forward Chaining");
			System.out.println("   FC = Forward Chaining");
			System.out.println("   BC = Backward Chaining");
			System.out.println("And filename is the text file that contains the knowledgebase and query.");
			return;
		}
		
		
		// process based on commandline arguments
		processExternalFile(args[0], args[1]);
	}

}
