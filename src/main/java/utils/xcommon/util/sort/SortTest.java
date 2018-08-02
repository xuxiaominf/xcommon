package utils.xcommon.util.sort;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;

public class SortTest {
	 
	 public static void main(String[] args) {
		 final int[] NUMBERS = {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
//		 insertSort(NUMBERS);
		 
		 shellSort(NUMBERS);
	}
	 
	 /**
	  * 插入排序
	  * 
	  * 
	 * @param arr
	 */
	public static void insertSort(int [] arr){
		 for(int i=1;i<arr.length;i++){
			 //把最新一个保存起来
			 int tmp = arr[i];
			 int j = i-1;
			 //已经排过序的和要排序的比较
			 for(; j>=0 && arr[j]>tmp ; j--){
				 //已经拍过序的依次往后移，给要排序的留一个位置
				 arr[j+1] = arr[j];
			 }
			 arr[j+1] = tmp;
		 }
		 System.out.println(JSON.toJSONString(arr) + "sorted");
	 }
	
	
    public static void shellSort(int[] array) { 
    	long curr = System.currentTimeMillis();
        int i;  
        int j;  
        int temp;  
        int gap = 1;  
        int len = array.length;  
        while (gap < len / 3) { gap = gap * 3 + 1; }  
        for (; gap > 0; gap /= 3) {  
            for (i = gap; i < len; i++) {  
                temp = array[i];  
                for (j = i - gap; j >= 0 && array[j] > temp; j -= gap) {  
                    array[j + gap] = array[j];  
                }  
                array[j + gap] = temp;  
            }  
        }  
        System.out.println(System.currentTimeMillis()-curr);
        System.out.println(Arrays.toString(array) + " shellSort");  
    }  
	 
}

