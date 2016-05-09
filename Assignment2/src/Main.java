import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
	
	
	
	public static void processExternalFile(String aMethod, String aFilename)
    {
        File lFile = new File(aFilename);
        
        try
        {
            InputStream in = new FileInputStream(lFile);
            String lLine;
            String lKB= "";	// knowledge base line
            String lQ = "";	// query line
            
            //We are uising UTF-8 as the default is system dependant, which could introduce inconsistencies.
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
            		lQ = lBuffer.readLine();
            		continue;	// could technically break here as long as the TRY is always before the ASK
            		
            	}
            }
            
            lBuffer.close();
            
            // need to make sure if lKB and lQ aren't found/set, it errors and exits
            
            
            InferenceEngine iEngine = new InferenceEngine(lKB);
            
            iEngine.solve(lQ, aMethod);
            
        }
        catch(IOException ioe)
        {
            System.out.println(aFilename + " could not be read.");
        }
    }
	
	
	
	
	

	public static void main(String[] args) {
		// check for correct number of command line arguments
		
		
		// process based on commandline arguments
		processExternalFile(args[0], args[1]);
	}

}
