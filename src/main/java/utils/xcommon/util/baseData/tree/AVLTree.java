package utils.xcommon.util.baseData.tree;

import utils.xcommon.util.baseData.AVLNode;

public class AVLTree<T extends Comparable<T>> implements Tree<T>{
	private AVLNode<T> root;
	public AVLTree(T data) {
		root = new AVLNode<T>(data);
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int height() {
		return height(root);
	}
	public int height(AVLNode<T> node){
		if(node==null){
			return 0;
		}
		return node.getHeight();
	}
	
	public String preOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	public String inOrder() {
		return inOrder(root);
	}
	public String inOrder(AVLNode<T> node) {
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
	public String postOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	public String levelOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	public void insert(T data) {
		root  = insert(root,data);
	}
	
	public AVLNode<T> insert(AVLNode<T> root,T data){
		if(root == null){
			return new AVLNode<T>(data);
		}else{
			int ret = root.getData().compareTo(data);
			if(ret>0){
				root.setRight(insert(root.getRight(),data));
				if(root.getLeft()!=null && root.getRight()!=null && root.getRight().getHeight()-root.getLeft().getHeight()==2){
					//新加的节点在右边的右边，进行右旋转
					if(data.compareTo(root.getRight().getData())>0){
						root = rightRightRotation(root);
					}else{
						//新加的节点在右边的左边
						root = rightLeftRotation(root);
					}
					
				}
			}else if(ret<0){
				root.setLeft(insert(root.getLeft(),data));
				if(root.getLeft()!=null && root.getRight()!=null && root.getLeft().getHeight()-root.getRight().getHeight()==2){
					//新加的节点在左边的左边，进行左旋转
					if(data.compareTo(root.getLeft().getData())<0){
						root = leftLeftRotation(root);
					}else{
						root = leftRightRotation(root);
					}
				}
			}
		}
		return root;
	}
	
	
	/**
	 * 左旋转
	 * @param k2
	 * @return
	 */
	public AVLNode<T> leftLeftRotation(AVLNode<T> k2){
		if(k2==null){
			return null;
		}
		AVLNode<T> k1 = k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		
		k2.setHeight(Math.max(k2.getLeft().getHeight(), k2.getRight().getHeight())+1);
		k1.setHeight(Math.max(k1.getLeft().getHeight(), k2.getHeight())+1);
		return k1;
	}
	
	/**
	 * 右旋转
	 * @param k1
	 * @return
	 */
	public AVLNode<T> rightRightRotation(AVLNode<T> k1){
		if(k1==null){
			return null;
		}
		AVLNode<T> k2 = k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		
		k1.setHeight(Math.max(k1.getLeft().getHeight(), k1.getRight().getHeight())+1);
		k2.setHeight(Math.max(k2.getRight().getHeight(), k1.getHeight())+1);
		return k2;
	}
	
	/**
	 * 左右旋转
	 * @param k1
	 * @return
	 */
	public AVLNode<T> leftRightRotation(AVLNode<T> k1){
		k1.setLeft(rightRightRotation(k1.getLeft()));
		return leftLeftRotation(k1);
	}
	
	/**
	 * 右左旋转
	 * @param k1
	 * @return
	 */
	public AVLNode<T> rightLeftRotation(AVLNode<T> k1){
		k1.setRight(leftLeftRotation(k1.getRight()));
		return rightRightRotation(k1);
	}
	
	
	
	public void remove(T data) {
		root = remove(root, data);
	}
	
	public AVLNode<T> remove(AVLNode<T> root,T data){
		if(root==null){
			return null;
		}
		int ret = root.getData().compareTo(data);
		//如果要删除的节点在root的右边节点
		if(ret > 0){
			root.setRight(remove(root.getRight(),data));
			//右边删除一个几点之后，有可能失去平衡
			if(root.getLeft()!=null && root.getRight()!=null && root.getLeft().getHeight() - root.getRight().getHeight() ==2){
				AVLNode<T> left =root.getLeft();
				if(left.getLeft().getHeight()>left.getRight().getHeight()){
					root = leftLeftRotation(root);
				}else{
					root = leftRightRotation(root);
				}
			}
		}else if(ret < 0){
			//要删除的节点在root的左边
			root.setLeft(remove(root.getLeft(),data));
			//平衡
			if(root.getLeft()!=null && root.getRight()!=null && root.getRight().getHeight()-root.getLeft().getHeight()==2){
				AVLNode<T> right =root.getRight();
				if(right.getLeft().getHeight()>right.getRight().getHeight()){
					root = rightLeftRotation(root);
				}else{
					root = rightRightRotation(root);
				}
			}
		}else{
			if(root.isLeaf()){
				root = null;
			}else if(root.getLeft()!=null && root.getRight()!=null){
				//左边比右边深，那么左边删掉一个节点，也不会失去平衡
				if(root.getLeft()!=null && root.getRight()!=null && root.getLeft().getHeight()>root.getRight().getHeight()){
					AVLNode<T> max = findMax(root.getLeft());
					root.setData(max.getData());
					root.setLeft(remove(root.getLeft(),max.getData()));
				}else{
					//右边比左边深，或者等深，删掉一个节点也不会失去平衡
					AVLNode<T> min = findMax(root.getRight());
					root.setData(min.getData());
					root.setRight(remove(root.getRight(),min.getData()));
				}
			}else{
				root = root.getLeft()!=null?root.getLeft():root.getRight();
			}
		}
		return root;
	}
	public T findMin() {
		AVLNode<T> min = findMin(root);
		return min!=null?min.getData():null;
	}
	
	public AVLNode<T> findMin(AVLNode<T> root) {
		return root.getLeft()!=null?findMin(root.getLeft()):root;
	}
	
	public T findMax() {
		AVLNode<T> max = findMax(root);
		return max!=null?max.getData():null;
	}
	
	public AVLNode<T> findMax(AVLNode<T> root) {
		return root.getRight()!=null?findMax(root.getRight()):root;
	}
	public BinaryTreeNode<T> findNode(T data) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean contains(T data) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	public void clear() {
		// TODO Auto-generated method stub
	}
	
    /* 
     * 销毁AVL树
     */
    private void destroy(AVLNode<T> tree) {
        if (tree==null)
            return ;

        if (tree.getLeft() != null)
            destroy(tree.getLeft());
        if (tree.getRight() != null)
            destroy(tree.getRight());

        tree = null;
    }

    public void destroy() {
        destroy(root);
    }
	
    /*
     * 打印"二叉查找树"
     *
     * key        -- 节点的键值 
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(AVLNode<T> tree, T key, int direction) {
        if(tree != null) {
            if(direction==0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.getData(), key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.getData(), key, direction==1?"right" : "left");

            print(tree.getLeft(), tree.getData(), -1);
            print(tree.getRight(),tree.getData(),  1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.getData(), 0);
    }
}
