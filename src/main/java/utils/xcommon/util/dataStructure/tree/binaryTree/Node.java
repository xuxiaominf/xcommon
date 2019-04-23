package utils.xcommon.util.dataStructure.tree.binaryTree;

public class Node {
	private Integer data;
	private Node left;
	private Node right;
	public Node(Integer data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
	public void print(){
		System.out.print(data);
	}
	public Integer getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public boolean isLeaf(){
		return data!=null && getLeft()==null && getRight()==null;
	}
}
