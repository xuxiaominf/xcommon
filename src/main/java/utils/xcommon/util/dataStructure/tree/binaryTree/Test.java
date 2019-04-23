package utils.xcommon.util.dataStructure.tree.binaryTree;

import java.util.stream.Stream;

public class Test {
	public static void main(String[] args) {
		Integer [] arr = {17,99,3,11,1,2,13,4,5,7,6,8,9,10};
		BinaryTree bt = new BinaryTree();
		
		Stream.of(arr).forEach(a->{
			bt.insert(a);
		});
		
		System.out.println(bt.getDeepth());

		bt.beforeOrder();
	}
}
