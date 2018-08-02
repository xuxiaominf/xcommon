package utils.xcommon.util.baseData;

public class AVLNode<T extends Comparable<T>> {
	private int height;
	private T data;
	private AVLNode<T> left;
	private AVLNode<T> right;
	public AVLNode(T data) {
		this.left = null;
		this.right = null;
		this.data = data;
		this.height = 0;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public AVLNode<T> getLeft() {
		return left;
	}
	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}
	public AVLNode<T> getRight() {
		return right;
	}
	public void setRight(AVLNode<T> right) {
		this.right = right;
	}
	public boolean isLeaf(){
		return data!=null && getLeft()==null && getRight()==null;
	}
}
