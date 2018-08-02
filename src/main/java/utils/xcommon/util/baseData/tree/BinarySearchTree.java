package utils.xcommon.util.baseData.tree;

import java.util.LinkedList;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
	private BinaryTreeNode<T> root;
	public BinarySearchTree(T data) {
		this.root = new BinaryTreeNode<T>(data);
	}

	@Override
	public void insert(T data) {
		insert(data,root);
	}
	
	/**
	 * 插入，按搜索二叉树插入，数据相同的节点直接返回
	 * @param data
	 * @param root
	 * @return
	 */
	public BinaryTreeNode<T> insert(T data, BinaryTreeNode<T> root){
		if(root == null){
			return new BinaryTreeNode<>(data);
		}
		int ret = root.getData().compareTo(data);
		if(ret>0){
			root.setLeft(insert(data,root.getLeft()));
		}else if(ret<0){
			root.setRight(insert(data,root.getRight()));
		}
		return root;
	}
	
	@Override
	public String preOrder() {
		return preOrder(root);
	}
	
	/**
	 * 先序遍历，用于遍历 文件名/文件 这种场景
	 * @param root
	 * @return
	 */
	public String preOrder(BinaryTreeNode<T> root) {
		StringBuilder sb = new StringBuilder();
		if(root==null){
			return "";
		}else{
			sb.append(root.getData()+",");
			sb.append(preOrder(root.getLeft()));
			sb.append(preOrder(root.getRight()));
		}
		return sb.toString();
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		return height(root);
	}
	
	public int height(BinaryTreeNode<T> node){
		if(node==null){
			return 0;
		}else{
			int leftHeiht = height(node.getLeft());
			int rightHeight = height(node.getRight());
			return leftHeiht>rightHeight?leftHeiht+1:rightHeight+1;
		}
	}

	@Override
	public String inOrder() {
		return inOrder(root);
	}
	
	/**
	 * 中序遍历，用于排序的场景
	 * @param node
	 * @return
	 */
	public String inOrder(BinaryTreeNode<T> node){
		StringBuilder sb = new StringBuilder();
		if(node==null){
			return "";
		}else{
			sb.append(inOrder(node.getLeft()));
			sb.append(node.getData()+",");
			sb.append(inOrder(node.getRight()));
		}
		return sb.toString();
	}

	@Override
	public String postOrder() {
		return postOrder(root);
	}
	
	/**
	 * 后序遍历，用于统计文件夹下文件大小这种场景
	 * @param node
	 * @return
	 */
	public String postOrder(BinaryTreeNode<T> node){
		StringBuilder sb = new StringBuilder();
		if(node!=null){
			sb.append(postOrder(node.getLeft()));
			sb.append(postOrder(node.getRight()));
			sb.append(node.getData()+",");
		}
		return sb.toString();
	}

	/**
	 * 分层遍历二叉树
	 * 用队列存root，然后进入循环，弹出一个current输出，然后把current的左右节点存到队列中
	 * 进入循环之后弹出一个，再输出，以此循环
	 * @return
	 */
	@Override
	public String levelOrder() {
		if(root==null)return "";
		BinaryTreeNode<T> current = null;
		StringBuilder sb = new StringBuilder();
		LinkedList<BinaryTreeNode<T>> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()){
			//弹出一个，输出
			current = queue.poll();
			sb.append(current.getData());
			sb.append(",");
			if(current.getLeft()!=null){
				queue.offer(current.getLeft());
			}
			if(current.getRight()!=null){
				queue.offer(current.getRight());
			}
		}
		return sb.toString();
	}

	@Override
	public void remove(T data) {
		root = removeNode(data, root);
	}
	
	/**
	 * 递归删除node节点中数据为data的节点
	 * 二叉树删除分三种情况
	 * 1、要删除的data是左叶子节点或者右叶子节点，此时直接把节点置为null
	 * 2、要删除的节点只有左子节点或者只有右子节点，此时把当前节点设置为当前节点的左节点或者右节点
	 * 3、要删除的节点既有左子节点，又有右子节点，此时找到当前节点右节点中最小的节点 （minRight），把当前节点的data替换为minRight的data，然后递归当前节点的右节点，找到并删除 minRight
	 * 
	 * @param data
	 * @param node
	 * @return
	 */
	public BinaryTreeNode<T> removeNode(T data,BinaryTreeNode<T> node){
		if(node==null)return node;
		int ret = data.compareTo(node.getData());
		//当前节点不是要删除的节点，则根据比较值向左或者向右查找并递归删除
		if(ret>0){
			node.setRight(removeNode(data,node.getRight()));
		}else if(ret<0){
			node.setLeft(removeNode(data,node.getLeft()));
		}else{
			//当前节点是要删除的节点
			//1、要删除的data是左叶子节点或者右叶子节点，此时直接把节点置为null
			if(node.isLeaf()){
				node = null;
			}else if(node.getLeft()!=null&&node.getRight()!=null){
				//2、要删除的节点既有左子节点，又有右子节点，此时找到当前节点右节点中最小的节点 （minRight），把当前节点的data替换为minRight的data，然后递归当前节点的右节点，找到并删除 minRight
				BinaryTreeNode<T> minRight = findMinNode(node.getRight());
				node.setData(minRight.getData());
				node.setRight(removeNode(minRight.getData(),node.getRight()));
			}else{
				//2、要删除的节点只有左子节点或者只有右子节点，此时把当前节点设置为当前节点的左节点或者右节点
				node = node.getLeft()!=null?node.getLeft():node.getRight();
			}
		}
		return node;
	}

	@Override
	public T findMin() {
		BinaryTreeNode<T> minNode = findMinNode(root);
		return minNode==null?null:minNode.getData();
	}
	
	public BinaryTreeNode<T> findMinNode(BinaryTreeNode<T> node){
		if(node!=null&&node.getLeft()!=null){
			node = findMinNode(node.getLeft());
		}
		return node;
	}

	@Override
	public T findMax() {
		BinaryTreeNode<T> maxNode = findMaxNode(root);
		return maxNode==null?null:maxNode.getData();
	}
	
	public BinaryTreeNode<T> findMaxNode(BinaryTreeNode<T> node){
		if(node!=null&&node.getRight()!=null){
			node = findMaxNode(node.getRight());
		}
		return node;
	}

	@Override
	public BinaryTreeNode<T> findNode(T data) {
		return findNode(data,root);
	}
	
	public BinaryTreeNode<T> findNode(T data, BinaryTreeNode<T> node){
		if(node==null)return null;
		int comRet = data.compareTo(node.getData());
		if(comRet>0){
			node = findNode(data,node.getRight());
		}else if(comRet<0){
			node = findNode(data,node.getLeft());
		}
		return node;
	}

	@Override
	public boolean contains(T data) throws Exception {
		return findNode(data)!=null;
	}

	@Override
	public void clear() {
		root = null;
	}
	
}
