package utils.xcommon.util.baseData;

import java.util.Random;

import utils.xcommon.util.baseData.tree.BinarySearchTree;

public class TreeTest {
	public static Integer[] getIntArry(int number){
		Integer[] array = new Integer[number];
		for (int i=0;i<array.length;i++) {
			Random random = new Random();
			array[i] = random.nextInt();
		}
		return array;
	}
	
	
	public static void main(String[] args) throws Exception {
		Integer[] ints = {443,999,23};
//		Integer[] ints = getIntArry(125000);
		BinarySearchTree<Integer> root = new BinarySearchTree<>(ints[0]);
//		System.out.println(JSON.toJSONString(ints));
		for (Integer integer : ints) {
			root.insert(integer);
		}
		
		System.out.println(root.inOrder());
		System.out.println(root.levelOrder());
		
		System.out.println(root.findMax());
		System.out.println(root.findMin());
		
		
		System.out.println(root.height());
		int removeInt = 123;
		System.out.println("removeInt:"+removeInt);
		root.remove(removeInt);
		System.out.println(root.inOrder());
		System.out.println(root.height());
		
		System.out.println(root.contains(9991));
		root.clear();
		System.out.println(root.inOrder());
	}
}
