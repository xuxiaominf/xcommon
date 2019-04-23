package utils.xcommon.util.sort;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;

public class SortTest {
	public static int[] NUMBERS = {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
	 
	 public static void main(String[] args) {
//		 insertSort();
		 shellSort();
//		 shellSort()
//		 shellSort(NUMBERS);
		 System.out.println(JSON.toJSONString(NUMBERS) + "   sorted");
		 
	}
	 
	 /**
	  * 插入排序
	  */
	 public static void insertSort(){
		 int length = NUMBERS.length;
		 if(length<=1)return;
		 int insert = 0;
		 for(int i=1;i<length;i++){
			 insert = NUMBERS[i];
			 
			 int j=i-1;
			 while(j>=0 && NUMBERS[j]>insert){
				 NUMBERS[j+1] = NUMBERS[j--];
			 }
			 NUMBERS[j+1] = insert;
		 }
	 }
	 
	 /**
	  * 希尔排序
	  * https://www.cnblogs.com/LeslieXia/p/5814571.html
	  */
	 public static void shellSort(){
		 int len = NUMBERS.length;
		 for(int i=len/2; i>=1;i/=2){
			 for(int j=i;j<len;j++){
				 int tmp = NUMBERS[j];
				 int k = j-i;
				 while(k>=0 && tmp < NUMBERS[k]){
					 NUMBERS[k+i] = NUMBERS[k];
					 k -= i;
				 }
				 NUMBERS[k+i] = tmp;
			 }
		 }
	 }
	 
	 /**
	  * 快速排序
	  * https://www.cnblogs.com/LeslieXia/p/5815069.html
	  */
	 public static void quickSort(){
		 
	 }
	 
}

