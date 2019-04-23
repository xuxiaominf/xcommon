package utils.xcommon.util.dataStructure.tree;

public interface Tree {
	/**
	 * 插入数据
	 * @param data
	 */
	void insert(int data);
	/**
	 * 先序遍历
	 */
	void beforeOrder();
	/**
	 * 获取树的深度
	 * @return
	 */
	int getDeepth();
}
