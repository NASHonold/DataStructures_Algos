/**
 * Student: Nash Honold
 * Date: 10/03/2021
 * Class: CSC 385
 * Assignment: Efficient Sorting 
 */


public class IntegerSet {

    // The array that represents the set.
    private final int set[];

    /**
     * The constructor for IntegerSet. When an IntegerSet is created it must be
     * initialized with an integer array. The set will then pull out the duplicated
     * items and keep the unique integers.
     * 
     * @param arr
     *            The array to create the set from.
     */
    public IntegerSet(int arr[]) {
		if (arr == null) {
			throw new IllegalArgumentException("The array must not be null");
		}

		set = uniqueElements(arr);

    }

    /**
     * This is the size of the set which, in this case, is just the length of the
     * array.
     * 
     * @return The length of the set.
     */
    public int magnitude() {
		return set.length;
    }

    /**
     * This method is private and is used to help set up the set array. An integer
     * set is one in which the elements are unique (no duplicates) and are sorted.
     * 
     * @param arr
     *            The array that will be used to retrieve the unique elements from.
     * @return The new integer array that contains the unique elements from arr.
     */
    private int[] uniqueElements(int arr[]) {

      sort(arr);
      int tempArr[] = longUnique(arr);
      int lastIdx = findEndIdx(tempArr);
      int lastArr[] = IntArrayOperations.truncateArray(tempArr, lastIdx + 1);
  
      return lastArr;
      
    }

    /**
     * This method returns whether or not value is located in the set. If the value
     * is in the set then return true otherwise return false. <br />
     * Example:
     * <pre>
     * 		IntegerSet iS1 = new IntegerSet([1,2,3,4]); 
     * 		iS1.contains(3); //returns true
     * 		iS2.contains(6); //returns false
     * </pre>
     * 
     * @param value
     *            The integer to look for.
     * @return True if value is located in the set otherwise false.
     */
    public boolean contains(int value) {
      int low = 0;
      int high = this.set.length - 1;
      int mid = 0;
      while(low <= high){
        mid = (low + high) / 2;
        
        if(this.set[mid] > value && mid != high){
          high = mid;
        }
        else if(this.set[mid] < value){
          low = mid + 1;
        }
        else if(this.set[mid] == value){
          return true;
        }
        else{
          return false;
        }
      }
      return false;

    }

    /**
     * A union of two sets is a new set that contains all elements from both sets.
     * This method takes another set and unions it with the set that calls this
     * method. A new IntegerSet is returned that contains the union of both sets.<br />
     * Example:
     * <pre>
     * 		IntegerSet is1 = new IntegerSet([1, 2, 3, 4]); 
     * 		IntegerSet is2 = new IntegerSet([3, 4, 5, 6, 7, 8]);
     * 		is1.union(is2) //returns new IntegerSet([1, 2, 3, 4, 5, 6, 7, 8]);
     * </pre>
     * 
     * @param otherSet
     *            The set to be unioned with.
     * @return A new IntegerSet that is the union of the calling set with the
     *         otherSet.
     */
    public IntegerSet union(IntegerSet otherSet) {
      int temp[] = new int[this.magnitude() + otherSet.magnitude()];
      int j = 0;
      for (int i = 0; i < this.magnitude(); i++) {
        temp[i] = this.set[i];
        j++;
      }
      for (int i = 0; i < otherSet.magnitude(); i++) {
        temp[j] = otherSet.set[i];
        j++;
      }
      return new IntegerSet(temp);
    }

    /**
     * The intersection of two sets is a new set that contains elements that occur
     * in both sets. This method takes another set and intersects it with the set
     * that calls this method. A new IntegerSet is returned that contains the
     * intersection of the two sets. <br />
     * Example:
     * <pre>
     * 		IntegerSet is1 = new IntegerSet([1,2,3,4]);
     * 		IntegerSet is2 = new IntegerSet([3,4,5]);
     * 		is1.intersection(is2) //returns new IntegerSet([3, 4]);
     * </pre>
     * 
     * @param otherSet
     *            The set to be intersected with.
     * @return A new IntegerSet that is the intersection of the calling set with the
     *         otherSet.
     */
    public IntegerSet intersection(IntegerSet otherSet) {
      int j = 0;
      boolean first = (this.magnitude() > otherSet.magnitude());
      int length;
      if(first){
        length = otherSet.magnitude();
      }
      else{
        length = this.magnitude();
      }
      int tempArr[] = new int[length];
      if(first){
        for (int i = 0; i <= otherSet.magnitude() - 1; i++) {
          if(this.contains(otherSet.set[i])){
            tempArr[j] = otherSet.set[i];
            j++;
          }
        }
      }
      else{
        for(int i = 0; i <= this.magnitude() -1; i++){
          if(otherSet.contains(this.set[i])){
            tempArr[j] = this.set[i];
            j++;
          }
        }
      }

      int index = findEndIdx(tempArr);
      IntegerSet toReturn = new IntegerSet(IntArrayOperations.truncateArray(tempArr, index +1));
      return toReturn;

    }
    /**
     * Returns a string representation of an IntegerSet type. The returned string
     * will have the following structure.
     * 
     * set{ elements in the set separated by a comma }.
     */
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("set{ ");
		for (int i = 0; i < set.length; i++) {
			sb.append(set[i]);
			if (i < set.length - 1) {
			sb.append(", ");
			}
		}
		sb.append(" }");
		return sb.toString();
    }


//======================= void sort() ===========================
/**
 * Method is a mergesort algo for int[] and calls on mergesort and merge
 * 
 * @param arr
 */
    private void sort(int arr[]) {
      mergeSort(arr, new int[arr.length],0, arr.length - 1);
  
    }
//====================== void mergeSort() ====================
/**
 * the recursive mergeSort method that gets called in sort()
 * @param arr
 * @param tempArray
 * @param first
 * @param last
 */
    private void mergeSort(int arr[], int tempArray[],
       int first, int last){
      if(first < last){
        int mid = first + (last - first) / 2;
        mergeSort(arr, tempArray, first, mid);
        mergeSort(arr, tempArray, mid + 1, last );
        merge(arr, tempArray, first, mid, last );
      }
    }

//======================= void merge() ========================
/**
 * the method that merges the results from mergeSort method
 * in sort()
 * @param arr
 * @param tempArray
 * @param first
 * @param mid
 * @param last
 */
    private void merge(int arr[], int tempArray[], int first,
     int mid, int last ){
      int beginHalf1 = first;
      int endHalf1 = mid;
      int beginHalf2 = mid + 1;
      int endHalf2 = last;
      int index = 0;
  
      while(beginHalf1 <= endHalf1 && beginHalf2 <= endHalf2){
        //int res = ((Comparable)arr[beginHalf1]).compareTo(arr[beginHalf2]);
        if(arr[beginHalf1] <= arr[beginHalf2]){
          tempArray[index] = arr[beginHalf1];
          beginHalf1++;
        }
        else{
          tempArray[index] = arr[beginHalf2];
          beginHalf2++;
        }
        index++;
      }
      while(beginHalf1 <= endHalf1){
          tempArray[index] = arr[beginHalf1];
          index++;
          beginHalf1 ++;
        }
        
      while(beginHalf2 <= endHalf2){
          tempArray[index] = arr[beginHalf2];
          index++;
          beginHalf2++;
        }
      
      for (int i = 0, j = first; i <= (last-first); i++, j++) {
            arr[j] = tempArray[i];
        }
        
        
    //====================== int findEndIdx() ==========================
    /** 
     * given an int[] that has '0' values trailing will find 
     * the index of the last positive value. This should only be used 
     * ont int[] arrays that are sorted and have the trailing 0's
     * 
     */
    }
    private int findEndIdx(int arr[]){
      int newdex = arr.length - 1;
      for(int index = arr.length - 1; index >= 0; index--){
          if( arr[index] != 0){
            newdex = index;
            return newdex;
          }
      }

      return 0;
    }
//=========================== int[] longUnique() ======================
/**
 * This returns an int[] of the same length as arr but with only 
 * exclusive values left. With the duplicate values that are removed the tempArr
 * will have trailing 0's in their place
 *  arr {1,1,2,3,3,4} will become  tempArr {1,2,3,4,0,0}
 * @param arr 
 *    array that contains duplicates but is already sorted
 * @return
 *     int[] with trailing 0's
 */

		private int[] longUnique(int arr[]){
      int tempArr[] = new int[arr.length];

      for (int i = 0, j = 0; j < tempArr.length; j++) {
          if(i == 0){
            tempArr[i] = arr[j];
            i++;
          }
          else if(arr[j] != tempArr[i - 1]){
            tempArr[i] = arr[j];
            i++;
          }
      }
      return tempArr;
    }




    
}
