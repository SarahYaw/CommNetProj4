/**
 *
 * @author sarahyaw
 */
import java.util.*;
import java.io.*;
public class Router 
{
    public static void main(String[] args)
    {
        boolean hasInput=false, hasOutput=false;
        int inputIndex=0, outputIndex=0;
        File input, output;
        FileWriter out;
        Scanner in;
        if(args.length>0)
        {
            //check to see what command is input by user
            for(int i=0; i<args.length;i++)
            {
                if(args[i].equals("-i"))
                {
                    hasInput=true;
                    inputIndex = i+1;
                }
                if(args[i].equals("-o"))
                {
                    hasOutput=true;
                    outputIndex = i+1;
                }
                //if there is an invalid command
                if(!args[i].equals("-i")&&!args[i].equals("-0")&&args[i].charAt(0)=='-')
                {
                    System.out.println("Invalid command "+args[i]);
                    System.exit(0);
                } 
            }
                    
            // Get input
            if(hasInput)
            {
                input = new File(args[inputIndex]);
                System.out.println("input is: "+inputIndex);
            }
            else
            {
                input = new File("sye_input.txt");
                System.out.println("input is: sye_input.txt");
            }
            
            //Get output
            if(hasOutput)
            {
                output = new File(args[outputIndex]);
                System.out.println("output is: "+outputIndex);
            }
            else
            {
                output = new File("sye_output.txt");
                System.out.println("output is: sye_input.txt");
            }
        }
        else 
        {
            input = new File("sye_input.txt");
            try{}
            catch(Exception e)
            {
                try
                {
                    input.createNewFile();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            System.out.println("input is: sye_input.txt");
            
            output = new File("sye_output.txt");
            try
            {
                out = new FileWriter(output);
                in = new Scanner(input);
                String line = in.nextLine();
                while (in.hasNextLine())
                {
                    String temp[] = line.split("\\s");
                    String lne="";
                    for(int i=0; i<temp.length; i++)
                    {
                        if(!temp[i].isEmpty())
                            lne+=temp[i]+" ";
                    }
                    System.out.println("line: "+lne);
                    line = in.nextLine();
                }
                out.write("hi");
                out.flush();
                out.close();
            }
            catch(Exception exe)
            {
                System.out.println("No File");
                System.exit(0);
            }
            System.out.println("output is: sye_input.txt");
        }
        System.out.println("All Done---------------------");
    }
}