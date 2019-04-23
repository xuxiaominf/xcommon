package utils.xcommon.util.dataStructure.tree.binaryTree;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import utils.xcommon.util.dataStructure.tree.Tree;

public class BinaryTree implements Tree {
	private Node root;

	@Override
	public void insert(int data) {
		Node node = new Node(data);
		if (root == null) {
			root = node;
			return;
		}
		insert(root, node);
	}

	private Node insert(Node beInsertNode, Node insertNode) {
		if (insertNode == null) {
			throw new IllegalArgumentException("Data can not be null");
		}
		if (beInsertNode == null) {
			return insertNode;
		} else {
			if (beInsertNode.getData() > insertNode.getData()) {
				beInsertNode.setLeft(insert(beInsertNode.getLeft(), insertNode));
			} else {
				beInsertNode.setRight(insert(beInsertNode.getRight(), insertNode));
			}
		}
		return beInsertNode;
	}

	@Override
	public void beforeOrder() {
		List<Node> nodeList = Lists.newArrayList();
		beforeOrder(root, nodeList);
		nodeList.forEach((node) -> {
			System.out.println(node.getData());
		});
	}

	/**
	 * 先序遍历，用于排序
	 * 
	 * @param node
	 * @param nodeList
	 * @return
	 */
	private Node beforeOrder(Node node, List<Node> nodeList) {
		if (node.isLeaf()) {
			return node;
		} else {
			if (node.getLeft() != null) {
				Node preNode = beforeOrder(node.getLeft(), nodeList);
				if (preNode != null) {
					nodeList.add(preNode);
				}
			}
			nodeList.add(node);
			if (node.getRight() != null) {
				Node postNode = beforeOrder(node.getRight(), nodeList);
				if (postNode != null) {
					nodeList.add(postNode);
				}
			}
		}
		return null;
	}

	@Override
	public int getDeepth() {
		return getDeepth(root);
	}

	private int getDeepth(Node node) {
		if (node == null) {
			return 0;
		} else {
			int leftLength = getDeepth(node.getLeft());
			int rightLength = getDeepth(node.getRight());
			return leftLength > rightLength ? leftLength + 1 : rightLength + 1;
		}
	}

}
