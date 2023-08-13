import java.util.*;

public class HashAssignment {

    // private static final Collection T = null;

    public static void main(String args[]) {
        List<List<Integer>> intLists = new LinkedList<>();

        intLists.add(new ArrayList<Integer>(Arrays.asList(3, 4, 9, 8, 12, 15, 7, 13)));
        intLists.add(new ArrayList<Integer>(Arrays.asList(15, 24, 50, 12, 3, 9)));
        intLists.add(new ArrayList<Integer>(Arrays.asList(78, 65, 24, 13, 9, 3, 12)));
        intLists.add(new ArrayList<Integer>(Arrays.asList(15, 78, 14, 3, 2, 9, 44, 12)));
        List<Integer> intResult = findCommonElements(intLists);

        System.out.println("Common elements of the integer list:");
        System.out.println(intResult + "\n");

        List<List<String>> stringLists = new LinkedList<>();

        stringLists.add(new ArrayList<String>(Arrays.asList("a", "b", "d", "c", "h", "e")));
        stringLists.add(new ArrayList<String>(Arrays.asList("g", "b", "e", "j", "u", "z", "h", "d")));
        stringLists.add(new ArrayList<String>(Arrays.asList("y", "p", "b", "d")));

        List<String> stringResult = findCommonElements(stringLists);

        System.out.println("Common elements of the string list:");
        System.out.println(stringResult + "\n");

        List<List<String>> singleList = new LinkedList<>();
        singleList.add(new ArrayList<String>(Arrays.asList("x", "y", "z")));
        List<String> singleResult = findCommonElements(singleList);

        System.out.println("Common elements of the single string list:");
        System.out.println(singleResult + "\n");

        List<List<String>> zeroList = new LinkedList<>();
        List<String> zeroResult = findCommonElements(zeroList);

        System.out.println("Common elements of the zero string list:");
        System.out.println(zeroResult);

    }

    public static <T> List<T> findCommonElements(List<List<T>> collections) {
        HashSet<T> hashA = new HashSet<T>();
        HashSet<T> hashB = new HashSet<T>();
        int listIndex = 1;
        List<T> returnList = new ArrayList<T>();
        if (collections.size() == 0) {// checks if no 0 nested Lists present
            return returnList;
        }
        if (collections.size() == 1) {// checks if only 1 nested List present
            return collections.get(0);

        } else {// if more than one nested List now do comparison algo
            for (int i = 0; i < collections.get(0).size(); i++) {// get the values from the first list into hashA
                List<T> firstList = collections.get(0);
                hashA.add(firstList.get(i));
            }

            while (listIndex < collections.size()) {// iterate over the remaining Lists

                if (listIndex % 2 == 1) {// use HashA as the reference but add commons to hashB then clear() hashA
                    for (int i = 0; i < collections.get(listIndex).size(); i++) {
                        T thing = collections.get(listIndex).get(i);
                        if (!hashA.add(thing)) {
                            hashB.add(thing);
                        }
                    }
                    hashA.clear();
                } else {// use hashB as the reference but add commons to hashA then clear() hashB
                    for (int i = 0; i < collections.get(listIndex).size(); i++) {
                        T thing = collections.get(listIndex).get(i);
                        if (!hashB.add(thing)) {
                            hashA.add(thing);
                        }
                    }
                    hashB.clear();
                }
                listIndex++;// increment over nested Lists to next
            }

            HashSet<T> hashOut = new HashSet<T>();// initialize the HashSet that will point to the final common value
                                                  // hashSet
            hashOut = (listIndex % 2 == 1) ? hashA : hashB;// point to HashSet that was not cleared
            Iterator<T> iter = hashOut.iterator();

            while (iter.hasNext()) {// add values to List to be returned
                T common = iter.next();
                returnList.add(common);
            }

            return returnList;
        }
    }

}
