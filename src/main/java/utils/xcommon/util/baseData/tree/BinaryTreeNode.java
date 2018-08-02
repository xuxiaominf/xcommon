package utils.xcommon.util.baseData.tree;

public class BinaryTreeNode<T extends Comparable<T>>{
	private T data; 
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	
	public boolean isLeaf(){
		return data!=null && getLeft()==null && getRight()==null;
	}
	

	public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public BinaryTreeNode(T data) {
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

	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}

	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
}
