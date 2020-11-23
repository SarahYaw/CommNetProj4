/**
 *
 * @author sarahyaw
 */
import java.util.*;
import java.io.*;
import java.util.Map.Entry;
public class Router 
{
    //declare variables
    public static boolean hasInput=false, hasOutput=false;
    public static int inputIndex=0, outputIndex=0, nodes=0;
    public static File input, output;
    public static FileWriter out;
    public static Scanner in;
    public static String[] paths, table;
    public static String nodeList;
    public static String[][] matrix;

    public static void main(String[] args)
    {
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
                compute(input, output);
            }
            else
            {
                output = new File("sye_output.txt");
                System.out.println("output is: sye_input.txt");
                compute(input, output);
            }
        }
        else //if no arguements
        {
            input = new File("sye_input.txt");
            output = new File("sye_output.txt");
            try
            {
                input.createNewFile();
                output.createNewFile();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            compute(input, output);
        }
    }
    public static void compute(File input, File output)
    {
        try 
        {
            out = new FileWriter(output);
            in = new Scanner(input);
        }
        catch(Exception e){e.printStackTrace();}
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
        nodeList="";
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
        matrix = new String[tempo.length+1][tempo.length+1];
        matrix[0][0]=" ";
        for(int i = 0; i<tempo.length; i++)
            matrix[0][i+1]=tempo[i];
        for(int i = 0; i<tempo.length; i++)
            matrix[i+1][0]=tempo[i];
        

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
        //place mirror of initial values into matrix
        for(int i = 0; i<paths.length; i++)
        {
            String[] onePath = paths[i].split(" ");
            for(int j = 0; j<paths.length; j++)
                if(onePath[1].equals(matrix[0][j]))
                    for(int k = 0; k<paths.length; k++)
                        if(onePath[0].equals(matrix[0][k]))
                            matrix[j][k]=onePath[0]+","+onePath[2];
        }
//dijkstra for every node
        for(int i = 0; i<tempo.length; i++)
        {
            dijkstra(tempo[i]);
            //dijkstra("A");
            System.out.println("------------------------------------------------------------------");
        }
        
        for(int i=0; i<tempo.length; i++)
            matrix[i+1][i+1]="-";

        //display matrix
        for(int i = 0; i<matrix[0].length; i++)
        {
            for(int j = 0; j<matrix[0].length; j++)
            {
                if(i==1&&j==0)
                {
                    for(int k=0; k<tempo.length;k++)
                        System.out.print("-----------");
                    System.out.print("\n");
                }  
                if(i>=1&&j==0)
                    System.out.print(" "+matrix[i][j]+"\t|");
                else
                    System.out.print(" "+matrix[i][j]+"\t");
            }
            System.out.println("");
        }
        
        try
        {
            out.write("hi");
            out.flush();
            out.close();
        }
        catch(Exception e){e.printStackTrace();}

        System.out.println("\nAll Done----------------------------------------------------------");
    }
    public static void dijkstra(String startNode)
    {   //help here from baeldung-> https://www.baeldung.com/java-dijkstra
        String[] no = nodeList.split(" ");
        Set<node> unsettled = new HashSet<node> ();
        //Queue<node> temp = new LinkedList<node> ();
        Set<node> settled =new HashSet<node> ();
        int index=0;

        System.out.println("Node: "+startNode);

        //set all costs to infinity
        for(int i = 0; i<no.length; i++)
        {
            node n;
            node s;
            if(no[i].equals(startNode))
            {
                s=new node(no[i]);
                s.distance=0;
                s.predecessor=no[i];
                for(int j = 0; j<paths.length; j++)
                {
                    String[] onePath = paths[j].split(" ");
                    if(s.name.equals(onePath[0]))
                        s.addNode(new node(onePath[1]),Integer.parseInt(onePath[2]));
                    else if (s.name.equals(onePath[1]))
                        s.addNode(new node(onePath[0]),Integer.parseInt(onePath[2]));
                }
                System.out.println("added "+s.name+" "+s.distance);
                s.path.add(s);
                unsettled.add(s);
            }
            else
            {
                n=new node(no[i]);
                n.distance=99999;
                n.predecessor=null;
                n.path.add(n);
                for(int j = 0; j<paths.length; j++)
                {
                    String[] onePath = paths[j].split(" ");
                    if(n.name.equals(onePath[0]))
                        n.addNode(new node(onePath[1]),Integer.parseInt(onePath[2]));
                    else if (n.name.equals(onePath[1]))
                        n.addNode(new node(onePath[0]),Integer.parseInt(onePath[2]));
                }
                System.out.println("added "+n.name+" "+n.distance);
                for(Entry<node,Integer> adjPair: n.adjacentTo.entrySet())
                {
                    System.out.println(" "+adjPair.getKey().name+" "+adjPair.getValue());
                }
                unsettled.add(n);
            }
        }
            System.out.println("----------------------------------------------");

        


        while(unsettled.size()!=0)
        {
            node eval = getClosest(unsettled);
            System.out.println("Evaluating "+eval.name);

            unsettled.remove(eval);
            for(Entry <node, Integer> adjPair: eval.adjacentTo.entrySet())
            {            
                System.out.println("Adjacent node: "+adjPair.getKey().name+" "+adjPair.getValue());

                node adjNode = adjPair.getKey();
                int dist = adjPair.getValue();

                boolean flag=false;
                for(node sett:settled)
                {
                    System.out.println(sett.name+" v "+adjNode.name);
                    if(sett.name.equals(adjNode.name) || settled.contains(adjNode))
                        flag=true;
                }

                        
                if(!flag)
                {
                    System.out.println(adjNode.name+" has not been used");
                    calcMinDist(eval, dist, adjNode);
                    //iterator help from https://www.geeksforgeeks.org/traverse-through-a-hashset-in-java/
                    Iterator <node>  i= unsettled.iterator();
                    node del=null;
                    while(i.hasNext())
                    {
                        node nod = i.next();
                        if(nod.name.equals(adjNode.name))
                            del = nod;
                    }
                    adjNode.distance+=dist;
                    adjNode.predecessor=eval.name;
                
                    if(del!=null)
                    {
                        adjNode.adjacentTo = del.adjacentTo;
                        adjNode.path = del.path;
                        unsettled.remove(del);
                    }
                    unsettled.add(adjNode);
                    System.out.println("Pushed "+adjNode.name+" to unsettled");
                }
                else
                    System.out.println(adjNode.name+" has been used");
                
                flag=false;
            }
            settled.add(eval);
                System.out.println("vetted "+eval.name+"-"+eval.distance);
            System.out.println("Remaining: -----------------------------------");
            for(node nod: unsettled)
                System.out.print(nod.name+"-"+nod.distance+", ");
            System.out.println("\nSettled: -------------------------------------");
            for(node nod: settled)
                System.out.print(nod.name+"-"+nod.distance+"-"+nod.predecessor+", ");
            System.out.println("\n----------------------------------------------");
        }

        node[] lists = settled.toArray(new node[settled.size()]);
        int ind=0;
        for(int m = 0; m<no.length; m++)
            if(no[m].equals(startNode))
                ind=m+1;
        for(int k = 1; k<=lists.length; k++)
        {
            for(int j = 0; j<lists.length; j++)
            {                
                if(lists[j].name.equals(matrix[k][0]))
                    matrix[ind][k]=lists[j].predecessor+","+lists[j].distance;
            }
        }
    
        //Arrays.sort(outp);
    }


    public static node getClosest(Set<node> unsettled)
    {
        node closestNode=null;
        int minDist = 99999;
        for(node no: unsettled)
        {
            int dist = no.distance;
            if(dist<=minDist)
            {
                minDist=dist;
                closestNode=no;
                System.out.println("So far, closest is "+closestNode.name+"-"+closestNode.distance);
            }
        }   
        return closestNode;
    }

    public static void calcMinDist(node eval, int dist, node source)
    {        
        System.out.println("Calc min dist between "+eval.name+" and "+source.name);
        int sourceDist=source.distance;
        System.out.println(source.name+"'s distance is "+dist);
        System.out.println(sourceDist+"+"+dist+" versus "+eval.distance);
        if(sourceDist+dist<=eval.distance||eval.distance==0)
        {
            eval.distance=sourceDist+dist;
            LinkedList<node> smallestPath = new LinkedList<node>(source.path);
            smallestPath.add(source);
            eval.path=smallestPath;
        System.out.println("distance between "+eval.name+" and "+source.name+" is "+eval.distance);
        } 
    }
}

class node
{
    LinkedList<node> path = new LinkedList<node>();
    int distance;
    String predecessor;
    String name;
    Map<node, Integer> adjacentTo = new HashMap<>();
    node(String n)
    {
        name = n;
    }

    public void addNode(node next, int cost) 
    {
        adjacentTo.put(next,cost);
    }
}