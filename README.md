# COMP203-16A-Assignment-One---Sorting-Lists



COMP203-16A — Assignment One - sorting lists
Due: Thursday, March 31st, 2016 — 11.00pm (15%)

Description: In this assignment, you must implement a linked list of unsorted integers that can sort itself, putting its elements into ascending order (i.e. smallest to largest).
You must implement your own IntList class and data class (to support a dynamically linked list of integer data), and you must include methods that implement two sorting methods (given below). The sort methods should belong to your IntList class; such that the IntList could be asked to sort itself using one of two sorting algorithms. No Arrays—data must always be in a dynamically linked list of self-referential data nodes.

Input/Output: Your program should run as a console program from the Linux command-line (i.e. no GUI). Your program object should be named MySort.java and it should accept one string and one positive integer as command-line arguments. Your program must first create an unsorted list with as many random positive integers as is indicated by the integer argument, and each random integer must be between 1 and the value of that argument. For example, if the integer argument value is 100, then you will create an unsorted list of 100 random positive integers, each with a value between 1 and 100 (and may include duplicate values). Use a Random object from the Java class library to generate random integers. As each random integer is generated, put it in your IntList with an "add" method that simply adds it to the front of the dynamically linked list. Once the list is complete, your program will ask that list to sort itself.

The string argument to your program must have the value "i" or "q" that will tell your MySort program-object which algorithm it is to use to sort the unsorted list. The corresponding algorithms are insertion-sort and quicksort (as applied to lists), which you are to implement yourself and are described below.

Once the IntList has successfully sorted itself with the appropriate algorithm, your program must print to standard out (i.e. System.out) the list elements in ascending order, one integer per line. Your IntList must have a public method called "dump" that does this. Finally, your program must print to standard error (System.err) the total number of data comparisons your IntList used to sort the list with the chosen sorting algorithm. This will be a crude metric for measuring how efficient each algorithm is. Only integer comparisons between two data during the sort are counted.

Insertion sort: Insertion sort takes each item out of an unsorted list, one by one (using a method called "remove"), and inserts them (using a method called "insert") into the correct spot in a new sorted list. If the command-line string argument is "i" then this is the algorithm you must use to sort the list. This method should be called "isort" and is a public method of your IntList.

Quicksort: If the command-line argument is "q" then this algorithm must be used. It should be invoked with a method called "qsort" defined for your IntList class. It works like this: Take the head item off the unsorted list and keep it in a list by itself. Call this the pivot value. For each of the other items, take them out of the unsorted list and add them to one of two other initially-empty lists based upon whether their value is smaller or greater than the pivot value (n.b. just "add" it, don't "insert" it). That is, create a list called smaller and another called bigger and put all values smaller than the pivot in the first list and all the ones greater in the second list. Once smaller and bigger are both sorted, you can produce a sorted list of all the data by appending (i.e. joining) the pivot on to the end of the smaller list and then appending the bigger list on to the end of that. Thus you will need a list method called append that takes one list as an argument and tacks it on to the end of the list whose method is being invoked.

How do you sort smaller and bigger? Simply call the "qsort" method on each of these new sub-lists. That is to say, the algorithm proceeds recursively on smaller and smaller subsets. Note that if a list is empty or has just one item in it then it is sorted already and the recursion stops. Note also that any value equal to the pivot can be added to either list, or even to the list holding the original pivot value; doesn't matter.

Grading: To get a grade of C your program need only implement one sorting algorithm correctly. An A grade is only possibly if you implement both algorithms correctly.

Submission Make sure your code is properly formatted and well-documented, including your name and student identification number. Submit your source code (no .class files) using Moodle.

Tony C. Smith, 11/03/2016
