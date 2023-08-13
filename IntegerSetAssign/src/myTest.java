public class myTest {

    private static String string;

    public static void main(String[] args) {
        int array[] = { 1, 5, 4, 4, 8, 2, 4, 9, 12, 10, 13 };
        int arrA[] = { -1, 0, -3, 1, 2, 3, 4 };
        int arrB[] = { 3, 4, 5, 6, 7 };

        IntegerSet IsetA = new IntegerSet(arrA);
        IntegerSet IsetB = new IntegerSet(arrB);
        IntegerSet Iset = new IntegerSet(array);
        string = Iset.toString();
        System.out.println(string);
        boolean doesItContain = Iset.contains(3);
        System.out.println(doesItContain);
        doesItContain = Iset.contains(13);
        System.out.println(doesItContain);
        System.out.println(Iset.contains(3));
        System.out.println(IsetA.intersection(IsetB));
        System.out.println(IsetB.intersection(IsetA));

        System.out.println(IsetA.union(IsetB));

    }

}
