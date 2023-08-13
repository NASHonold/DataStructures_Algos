public class MergeSort {
	
	public static void sort(Object arr[]) {
		mergeSort(arr, new Object[arr.length],0, arr.length - 1);

	}

	private static void mergeSort(Object arr[], Object tempArray[], int first, int last){
		if(first < last){
			int mid = first + (last - first) / 2;
			mergeSort(arr, tempArray, first, mid);
			mergeSort(arr, tempArray, mid + 1, last);
			merge(arr, tempArray, first, mid, last);
		}
	}
	
	private static void merge(Object arr[], Object tempArray[], int first, int mid, int last){
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;
		int index = 0;

		while(beginHalf1 <= endHalf1 && beginHalf2 <= endHalf2){
			int res = ((Comparable)arr[beginHalf1]).compareTo(arr[beginHalf2]);
			if(res <= 0){
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
	}
}
