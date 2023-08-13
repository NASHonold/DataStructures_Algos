package Test;

import IntegerSetTree.IntegerSet;

public class Test {

    public static void main(String[] args) {

        IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);
        setA.add(5);
        setA.add(6);
        setA.add(7);
        setA.add(8);
        
        IntegerSet setB = new IntegerSet(new int[]{5,6,7,8,9,10,11,12});

        System.out.println("Contents of setA:" + setA);
        System.out.println("Contents of setB:" + setB);
        System.out.println();
        int toCheckA = 4;
        int toCheckB = 10;
        int toCheckBoth = 8;

        System.out.println("Does setA contain " + toCheckA + ": " + setA.contains(toCheckA));
        System.out.println("Does setA contain " + toCheckB + ": " + setA.contains(toCheckB));
        System.out.println("Does setb contain " + toCheckB + ": " + setB.contains(toCheckB));
        System.out.println("Does setb contain " + toCheckA + ": " + setB.contains(toCheckA));
        System.out.println("Does setA contain " + toCheckBoth + ": " + setA.contains(toCheckBoth));
        System.out.println("Does setB contain " + toCheckBoth + ": " + setB.contains(toCheckBoth));
        System.out.println();

        IntegerSet unionSet = setA.union(setB);
        IntegerSet intersectionSet = setA.intersection(setB);
        IntegerSet differenceSetA = setA.difference(setB);
        IntegerSet differenceSetB = setB.difference(setA);
        IntegerSet symmetricDiffSet = setA.symmetricDifference(setB);


        System.out.println("The union between setA and setB should output all integers in both sets \n" + 
                 "Output should be: { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }\n" + "Union of setA and setB: " + unionSet);
        System.out.println();
        System.out.println("The intersection between setA and setB should output all integers that BELONG to both sets \n" + 
                 "Output should be: { 5, 6, 7, 8}\n"+ "Intersection of setA and setB: " + intersectionSet);
        System.out.println();
        System.out.println("The difference between setA and setB should output all integers that are in setA and NOT setB\n" + 
                 "Output should be: { 1, 2, 3, 4}\n"+ "Difference of setA to setB: " + differenceSetA);
        System.out.println("The difference between setB and setA should output all integers that are in setB and NOT setA\n" + 
                 "Output should be: { 9, 10, 11, 12}\n"+ "Difference of setB to setA: " + differenceSetB);
        System.out.println();
        System.out.println("The symmetric difference of setA and setB should output elements in either set not found in the other set.\n" + 
                 "Output should be: { 1, 2, 3, 4, 9, 10, 11, 12}\n"+ "Difference of setA to setB: " + symmetricDiffSet);
        System.out.println();



        System.out.println("This should be min of setA: " + setA.getMin());
        System.out.println("This should be Max of setA: " + setA.getMax());
        System.out.println("This should be min of setB: " + setB.getMin());
        System.out.println("This should be Max of setB: " + setB.getMax());

        System.out.println();

        System.out.println("Checking the height of sets to ensure they are balanced");
        System.out.println("setA height is:" + setA.getHeight());
        System.out.println("setB height is:" + setB.getHeight());
        System.out.println("Union set height is:" + unionSet.getHeight());
        System.out.println("Intersection set height is:" + intersectionSet.getHeight());
        System.out.println("Difference set of setA to setB height is:" + differenceSetA.getHeight());
        System.out.println("Difference set of setB to setA height is:" + differenceSetB.getHeight());
        System.out.println("Symmetrical difference set height is:" + symmetricDiffSet.getHeight());


        int aRemove = 5;
        int bRemove = 8;
        System.out.println("\nRemoving the " + aRemove + " value from setA: ");
        setA.remove(aRemove);
        System.out.println("setA values:" + setA);
        
        System.out.println("\nRemoving the " + bRemove + " value from setB: ");
        setB.remove(bRemove);
        System.out.println("setB values:" + setB);
        

       
        // System.out.println("This is the intersection of setA and setB:");
        // IntegerSet intersectionSet = setA.intersection(setB);
        // System.out.println(intersectionSet);

        

    }
}
