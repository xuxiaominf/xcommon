package utils.xcommon.util.baseData;

public class BinaryNodeTree<T extends Comparable<T>>{
	private T data;
	private BinaryNodeTree<T> left;
	private BinaryNodeTree<T> right;
	public BinaryNodeTree(T data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}


	public BinaryNodeTree<T> getLeft() {
		return left;
	}


	public void setLeft(BinaryNodeTree<T> left) {
		this.left = left;
	}


	public BinaryNodeTree<T> getRight() {
		return right;
	}


	public void setRight(BinaryNodeTree<T> right) {
		this.right = right;
	}
}
