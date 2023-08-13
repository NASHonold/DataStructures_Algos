public class MyTester {
    public static void main(String args[]){
        Integer arr[] = {10,1,9,2,8,3,7,4,6,5, 15, 14, 16, 13, 20, 1};

        //MergeSort.sort(arr);
        printArray(arr);
        QuickSort.sort(arr);
        printArray(arr);

        String a = "aaa";
        String b = "bbb";

        
    }

    private static void printArray(Object arr[]){
        for (Object object : arr) {
            System.out.print(object + ",");
            
        }
        System.out.println("\n");
    }


    private static void changeArray(Object arr[]){
            Object temp = arr[0];
            arr[0] = arr[arr.length - 1];
            arr[arr.length- 1 ] = temp;
    }
    
}
