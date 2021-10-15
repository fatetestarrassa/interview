package com.icyfate.interview.test.arithmetic;

import lombok.Data;

/**
 * 二叉树树节点
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/9/28 16:07
 */
@Data
public class TreeNode {

    /**
     * 节点值
     */
    private Integer value;
    /**
     * 左子节点
     */
    private TreeNode left;
    /**
     * 右子节点
     */
    private TreeNode right;

    public TreeNode(Integer value) {
        this.value = value;
    }

    /**
     * 弹出后操作：0=设置为1再次放入栈中   1=把节点值放入结果集
     */
    private Integer flag;
}
