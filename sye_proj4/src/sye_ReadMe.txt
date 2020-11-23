Creator: Sarah Yaw
Project Description:
     This is a simulator of a routing matrix that utilizes dijkstras algorithm to traverse and process the information given. It takes in an input file consisting of "node adjacentNode distance" such that the amount of space does not matter, the names of the routers do not matter (although alignment of the table might look a little weird if nore than 4 characters long), and that every node in the network is mentioned at least once.
Run Instructions:
    1)  Open up the terminal window.
    2)  Type "cd "and then the file path to where the sye_rmatrix.java file is located and hit enter.
    		Example: cd Desktop/school/s1/CommNet/Project4
    3)  Type "javac sye_rmatrix.java" in the terminal and hit enter. This compiles the program and adds a file called sye_rmatrix.class to your folder.
    4)  Type "java sye_rmatrix" and input the input and output file names then hit enter. If you do not include the input and output file names, defaults will be chosen.
			Example: java sye_rmatrix -i sye_input.txt -0 sye_output.txt
			Note: If running on Google Cloud the run command will be "nohup java sye_rmatrix  -i input.txt -0 output.txt &"
    5)  If you wish to open the output file, please wait until the program has finished.
Conclusion: 
		I estimate this took 30 hours to complete. I finished on my third, pretty solid day of working on it and only took breaks for food and rest. 
                The most difficult part for me was figuring out how exactly to get dijkstra into code. I think the algorithm is a lot of fun to do by hand, but for some reason I was having trouble conceptualizing it into code. I ended up looking up help and commented the URL where i used it in the code.
		I'm happy to report that my program works! I made the input handle a variety of cases (node names being words or characters, nodes with similar names, a mixture of tabs and spaces separating the information of the input file), and it works well.
		I think this assignment worked out pretty good. I don't really have any changes I would make. 