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
        //declare variables
        boolean hasInput=false, hasOutput=false;
        int inputIndex=0, outputIndex=0, nodes=0;
        File input, output;
        FileWriter out;
        Scanner in;
        String[] paths, table;

        //startup argument handling
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
        else //if no arguements
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
            
            output = new File("sye_output.txt");
            try
            {
                out = new FileWriter(output);
                in = new Scanner(input);
                String line = in.nextLine();
                String intake="";

                //taking in input file paths
                while (in.hasNextLine())
                {
                    String temp[] = line.split("\\s");
                    String lne="";
                    for(int i=0; i<temp.length; i++)
                    {
                        if(!temp[i].isEmpty())
                            lne+=temp[i]+" ";
                    }
                    intake+=lne+"-";
                    line = in.nextLine();
                }
                String temp[] = line.split("\\s");
                String lne="";
                for(int i=0; i<temp.length; i++)
                {
                    if(!temp[i].isEmpty())
                        lne+=temp[i]+" ";
                }
                intake+=lne+"-";

                //put those paths into an array
                paths = intake.split("-");

                //displays input file V
                //for(int i = 0; i<paths.length; i++)
                    //System.out.println(paths[i]);

                //make a list of all of the nodes
                String nodeList="";
                for(int i=0; i<paths.length; i++)
                {
                    String[] oneNode = nodeList.split(" ");
                    boolean isIn = false;
                    String add="";
                    for(int j = 0; j<oneNode.length; j++)
                    {
                        String[] onePath = paths[i].split(" ");
                        if(onePath[0].equals(oneNode[j]))
                            isIn=true;
                        else
                            add=onePath[0];
                    }
                    if(!isIn)
                        nodeList+=add+" ";
                    
                    isIn = false;
                    add="";
                    for(int j = 0; j<oneNode.length; j++)
                    {
                        String[] onePath = paths[i].split(" ");
                        if(onePath[1].equals(oneNode[j]))
                            isIn=true;
                        else
                            add=onePath[1];
                    }
                    if(!isIn)
                        nodeList+=add+" ";
                }
                
                //alphabetize node list
                String[] tempo = nodeList.split(" ");
                Arrays.sort(tempo);
                nodeList = String.join(" ",tempo);
                //System.out.println(nodeList);

                //create & initialize matrix
                String[][] matrix = new String[tempo.length+2][tempo.length+2];
                matrix[0][0]=" ";
                for(int i = 0; i<tempo.length; i++)
                    matrix[0][i+1]=tempo[i];
                for(int i = 0; i<tempo.length; i++)
                    matrix[i+1][0]=tempo[i];
                for(int i=0; i<tempo.length; i++)
                    matrix[i+1][i+1]="-";

                //place initial values into matrix
                for(int i = 0; i<paths.length; i++)
                {
                    String[] onePath = paths[i].split(" ");
                    for(int j = 1; j<paths.length; j++)
                        if(onePath[0].equals(matrix[0][j]))
                            for(int k = 0; k<paths.length; k++)
                                if(onePath[1].equals(matrix[0][k]))
                                    matrix[j][k]=onePath[1]+","+onePath[2];
                }

                //display matrix
                for(int i = 0; i<=tempo.length; i++)
                {
                    for(int j = 0; j<=tempo.length; j++)
                        System.out.print(" "+matrix[i][j]+"\t");
                    System.out.println("");
                }

                out.write("hi");
                out.flush();
                out.close();
            }
            catch(Exception exe)
            {
                exe.printStackTrace();
                System.exit(0);
            }
        }
        System.out.println("All Done---------------------");
    }
}