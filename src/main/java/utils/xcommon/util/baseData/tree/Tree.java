package utils.xcommon.util.baseData.tree;

public interface Tree<T extends Comparable<T>> {
	public boolean isEmpty();
	public int size();
	public int height();
	public String preOrder();
	public String inOrder();
	public String postOrder();
	public String levelOrder();
	public void insert(T data);
	public void remove(T data);
	public T findMin();
	public T findMax();
	public BinaryTreeNode<T> findNode(T data);
	public boolean contains(T data) throws Exception;
	public void clear();
}
