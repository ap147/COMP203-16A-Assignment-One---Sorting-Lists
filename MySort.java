// Amarjot Parmar
// 1255668
import java.util.Random;   //Used to create random ints

class MySort{
    public static IntList myIntList;            //Instance used to store,sort,display random ints
    public static void main(String []args){
        String SortType = "x";          //value set to what user passes through args[],used to decide sort method
        String SortTypeFullForm = "x";  //value changed depending on what sort is used,value used to print
      
        if(args.length !=2){    //if user has entered less or more then 2 values after java MySort
            Instructions();     //Show instructions how to use this program
            return;             //and stop the program
        }
        try{                                                //try & catch incase user inputs unexpected values
            int AmountNeeded = Integer.parseInt(args[1]);   //Converting useres secound value into int, used to check value is not 0
            if(args[0].toLowerCase().equals("i") || args[0].toLowerCase().equals("q")){ //If the users first value is "i" or "q"
                SortType = args[0].toLowerCase();                                       //store the user value in this variable created previously
            }
            else{                           //if first value is not "i" or "q"
                Instructions();             //print instructions and stop the program
                return;
            }
         
            if(AmountNeeded == 0){          //checking the amount of random ints to generate is not zero
                Instructions();             //if so prints out useage message
                return;                     //and exit program
            }
         
        }catch (Exception ex){Instructions();return;}       //catch when error occurs, prints out what this program expects from user
        
        
        myIntList = new IntList();              //Creating instance of IntList, used to store random ints, call sort method &print ints
        int Max = Integer.parseInt(args[1]);    //Storing users 2nd value as a int, used to create random ints & the amount of ints
        
        for(int x = 0; x < Max; x++){        //loop until the amount of ints user wanted (Max)
            myIntList.add(getRandom(Max));   //adding a random int between 1 - Max
        }
        
        System.out.println("--------------------------------------");
        System.out.println("UnSorted List");
        myIntList.dump();                       //Printing random int list before it gets sorted
        
        
        if(SortType.equals("i")){                   //when users first input is "i"
            myIntList.isort();                      //Call isort (insertion) method
            SortTypeFullForm = "Insertion Sort";    //Set value of printing variable, used at end of program to print what sort was used.
        }
        else{                                 //otherwise user value must be "q",
            myIntList.qsort();                //therefore call qsort method
            SortTypeFullForm = "Quick Sort";  //& set printing variable value to quicksort, which is used at end of program
        }
        
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("Sorted List");
        myIntList.dump();                                                                     //Printing random int list after it is sorted
        System.out.println();
        System.out.println("Sort Type                 : " + SortTypeFullForm);                //Printing what type of sort was used
        System.out.println("Amount of Ints Sorted     : "+ myIntList.size);                   //,amount of ints that are sorted int
        System.out.println("Amount of Comparisons made: " + myIntList.GetCompareCounter());   // & the amount of comparasions(int vs int)  made
        System.out.println("--------------------------------------");

    }
    
    //Gets passed in a int value
    //Gets random number between 1 & max value and returns it
    public static int getRandom (int max){
        int randnumber;                     //Random Int will be stored here
        Random ran = new Random();          //Creates a random instance
        randnumber = 1 + ran.nextInt(max);  //Gets a random int between 1 & max value
        return randnumber;                  //returns the random int
    }
    
    
    //Method which simply prints instructions on how to use the program from
    public static void Instructions(){
        System.out.println("------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("|____________Usage Guide___________|");
        System.out.println();
        System.out.println("Example:'java MySort a b'");
        System.out.println("       : a = sort type (i or q)");
        System.out.println("       : b = amount of Ints (0 < b)");
        System.out.println();
        System.out.println("Example: 'java MySort q 100'");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("------------------------------------");
    }
}

class IntList{
    
    int size =0;                 //Holds amount of Nodes Linked in this IntList
    double CompareCounter = 0;   //Holds the amount of Comparisons made during the sort
    Node Pointer = new Node(0);  //A emputy Node that is used to navigate the linkedlist
    //Contructor
    public IntList(){}
    
    /*
    add method which takes random int value, create a node containing random int value
    checks if list is empty or not, if so changes pointers next node to this new node
    if not then changes new values next node to whatever pointers next node value is 
    then changes pointers next node to the new node
    */
    public void add(int value){
        Node New = new Node(value);    //Creating new Node
        if(Pointer.next == null){      //Checking if list is empty
            Pointer.setNext(New);      //setting New Node as Pointer's Next Node
        }
        else{
            New.setNext(Pointer.getNext());  //Setting New Nodes Next value to the last Node that was linked
            Pointer.setNext(New);            //Setting Pointers Next Node to the New value
        }
        size++;     //Incrementing list size value
    }
    
    /*
    Removes the last value was added to this IntList
    */
    public void remove(){
        Node DeadNode = Pointer.next;      //Storing the Last Node that was added
        Pointer.setNext(DeadNode.next);    //Changing the Pointers Next Node value to the Secound Last Node added
    }
    
    /*
    Prints out all Nodes linked,
    Starting from the value that Pointer is pointing at
    */
    public void dump(){
        if(size != 0){                                  //If there are Nodes in the IntList
        Node NodeToPrint = Pointer.getNext();           //Node which is used to navigate (Holding last node atm)
            while(NodeToPrint != null){                 //While Navigating node is not point to a null
                System.out.println(NodeToPrint.value);  //Print the int value stored in the navigating node
                NodeToPrint = NodeToPrint.next;         //Moving the navigating Node to the next node
            }
        }
        else{                                           //If list is empty
            System.out.println("NO Nodes");             //Print this message
        }
    }
    
    /*
     Takes a int from isort, creates a new node and stores it in there
     checks if this list is empty or not, if so sets the pointers next value to the new node [Pointer] ---> [New]
     Otherwise Creates 2 Nodes,
     1st is Node that is pointing to the value that is going to be compared to the value being inserted,
     In case new value is smaller, it changes new Nodes next value to 2nd value (value being compared)
     and changes 1st nodes next value to new node
     [1st] ---> [New] ---> [2nd] 
     loops until New value is bigger then 2nd Nodes value or if the 2nd Nodes next value is empty  [1]--> [2nd] -->
     while looping it increments 1st & 2nd Nodes to there next Node, after falling out the loop 
     checks if the reason loop stopped is because 2nd value was point at nothing and if 2nd nodes value is smaller then new,
     if so it sets 2nd Nodes next value to New node
     otherwise 2nd value must be bigger then new nodes value regardless if 2nd node is point at nothing.
     increments size variable since another Node is added to linked list
    */
    public void insert(int value){
        Node New = new Node(value);     //Creating new Node with parsed in value
        if(Pointer.next == null){       //checking if list is empty
            Pointer.setNext(New);       //Adding the first value to the link
        }
        else{
            Node PrevNode = Pointer;              //Setting Node to point at node, that is pointing at the comparing value
            Node NextNode = Pointer.getNext();    //Setting Node to be the value that is going to be comapred
            
            //loop until int being parsed is larger then comparing value & until comparing nodes is not pointing at null
            while(New.value > NextNode.value && NextNode.next != null){
                CompareCounter++;                       //two ints are comapred in while condition, therfore incremeant counter
                PrevNode = NextNode;                    //setting the 1st Node to point at whatver 2nd Node is pointing at
                Node NExtNExt = NextNode.getNext();     //Geting the next Node that 2nd Node is pointing at
                NextNode = NExtNExt;                    //Setting 2nd node to point at whatever its own next was pointing at
            }
            //Checking if the reason loop is stopped because we are at end of list and new node value is greater then 2nd nodes value
            if(NextNode.next == null && NextNode.value <= New.value){
                NextNode.setNext(New);  //Setting 2nd Nodes next value to the new value added
                CompareCounter++;       //Incrementing two ints compared counter
            }
            else{                       //otherwise value must be smaller then the last node value (2nd) in the list
                PrevNode.setNext(New);  //Setting 2nd last node's next value to new node
                New.setNext(NextNode);  //Setting New Nodes next value to the last node (2nd) in list
                CompareCounter++;       //Incrementing two ints compared counter
            }
            size++;     //incrementing size since another node is added to list
        }
    }
    /*
     Creating an instance of IntList, which is where all the sorted Nodes go
     Loops untill all Nodes in this IntList is added into SortIntList that is just created
     while looping, gets the last value added and stores the value in it, 
     calls the remove method to remove the last Node, calls the insert method from SortIntList
     and passes it the last removed Node's value and deincrements the size of this list.
     After all the nodes are inserted into SortedIntList
     Sets this lists, Pointer's next value to whatever SortedIntList's Pointer's next value.
     which result in this list having all the Nodes back but sorted. 
     Also sets this lists CompareCounter's value to whatever SortedIntList had in its CompareCounter
     We do this because CompareCounter is only altered in SortedIntList since insert method only increments CompareCounter
     and insert method is never called in this IntList.
    */
    public void isort(){
        IntList SortedIntList = new IntList();     //Creating new list, used to store sorted Nodes
        int ListSize = size;                       //getting this lists size
        Node PrivatePointer = Pointer.next;        //getting the last added node
        
        //looping until there are not Nodes left in this list
        while (ListSize != 0){
            int TransferingNode = PrivatePointer.value; //Getting nodes int value, used to send to insert method
            remove();                                   //removing last node adedd
            PrivatePointer = PrivatePointer.next;       //moving onto the next node
            
            SortedIntList.insert(TransferingNode);      //calling sorted lists insert method
            ListSize--;                                 //deincrementing this list size
        }
        Pointer = SortedIntList.Pointer;                      //Setting this lists pointer value to value of pointer in the sorted list
                                                              //this results in this list having all its nodes back agian but in sorted form.
        CompareCounter = SortedIntList.GetCompareCounter();   //getting the amound on comparasions made and storing it in this lists local variable.
    }
    
    /*
     Adds the IntList passed in
     To the this IntList (thats method is caled)
    */
    public void append(IntList LinkMe){
        Node EndOfListPointer = Pointer;                //Setting out Node, that will be used to navigate
        while(EndOfListPointer.getNext() !=null){       //While Node has not reached the last Node in the list
            EndOfListPointer = EndOfListPointer.next;   //Keep changing Node to the next Node
        }
        //Set this Lists last Node's Next Node to be the Pointer from the IntList that is passed.
        EndOfListPointer.setNext(LinkMe.Pointer.next);
        //Adding to this IntLists CompareCounter, the CompareCounter that is stored in the passed IntList
        CompareCounter = CompareCounter + LinkMe.GetCompareCounter();
    }
    /*
     Method picks the last value added in the list and stores it in a list
     Uses this value to pick every other Node out of Linked list, if picked value is greater then pivot
     it adds it to the IntList called bigger, if smaller then adds it to smaller list, if equal then adds it to the pivot list
     when all values are taken from this list and put in its correct list
     it calls smaller and bigger IntList's qsort method until both both of them have 1 value left inside them
     them uses append method to link them all together into this list agian
    */
    public void qsort(){
        IntList pivot = new IntList();      //Last value added willl be added into this list
        IntList smaller = new IntList();    //Nodes thats value is smaller then the value in pivot list store here
        IntList bigger = new IntList();     //Nodes thats value is smaller then the value in pivot list store here
        
        Node head = Pointer.getNext();      //Getting the last added Node
        pivot.add(head.value);              //Adding the last node into pivot list
        remove();                           //removing the last node from this list
        
        Node PrivatePointerr = Pointer.getNext();       //Storing 2nd last node that was added (now the last node)
        //Loop untill list is empty
        while(PrivatePointerr != null){
            int CompareValue = PrivatePointerr.value;   //Getting the value inside the last node
            if(CompareValue == head.value){             //if this value is same as pivot
                pivot.add(CompareValue);                //add it to the pivot list
                CompareCounter++;                       //increment compare counter
            }
            else if(PrivatePointerr.value <= head.value){  //if value is smaller then pivot
                smaller.add(CompareValue);                 //add to smaller list
                CompareCounter++;                          //increment compare counter
            }
            else{                                          //otherwise value must be larger then pivot,
                bigger.add(CompareValue);                  //therfore add to bigger list
            }
            remove();                                  //remove the last node
            PrivatePointerr = PrivatePointerr.next;    //move onto the next node
        }
        
        if(smaller.size > 1){ smaller.qsort();}     //if smaller lists size is not 1, it must not be sorted
        if(bigger.size > 1){bigger.qsort();}        //if bigger list size not 1, it must not be sorted
        
        append(smaller);    //adding smaller list to this list
        append(pivot);      //adding pivot list to end of this list
        append(bigger);     //adding bigger list to end of this list
    }
    
    //Returns the amount of Comparisons had to be made to achive the sort.
    public double  GetCompareCounter(){return CompareCounter;}
    
    class Node{
        Node next;      //Holds the next Node that itself is pointing too [1] ---> [2] ---> [3] -->
        int value;      //Random Int will be stored in this variable
        
        //Constructor takes an int
        public Node(int n){
            value = n;      //Storing the random int
            next = null;    //Declaring that this node is not pointing to anything.
        }
        
        //Returning the node that this node is pointing to.
        public Node getNext()        { return next;}
        //Sets this Node's Next Value to whatever Node is pased in.
        public void setNext(Node n)  { next = n; }
    }
}