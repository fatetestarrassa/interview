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

    /**
     * 获取当前节点高度
     *
     * @return 当前节点高度
     */
    public int getHeight(){
        return Math.max(left == null?0:left.getHeight(),right == null?0:right.getHeight()) + 1;
    }

    /**
     * 获取当前节点左子节点的高度
     *
     * @return 左子节点的高度
     */
    public int getLeftHeight(){
        return left==null?0:left.getHeight();
    }

    /**
     * 获取当前节点右子节点的高度
     *
     * @return 右子节点的高度
     */
    public int getRightHeight(){
        return right==null?0:right.getHeight();
    }

    /**
     * 左旋转（当前节点为不平衡节点时）
     */
    public void leftRotate(){
        System.out.println("当前节点值：" + value + ",左旋");
        //1.新建节点newNode，值为不平衡节点的值
        TreeNode newNode = new TreeNode(value);
        //2.newNode的左子节点设为不平衡节点的左子节点
        newNode.setLeft(left);
        //3.newNode的右子节点设为不平衡节点的右子节点的左子节点
        newNode.setRight(right.getLeft());
        //4.不平衡节点的值设为右子节点的值（根节点设置为右子节点）
        value = right.getValue();
        //5.不平衡节点左子节点设为newNode，右子节点设置为不平衡节点的右子节点的右子节点
        left = newNode;
        right = right.getRight();

    }

    /**
     * 右旋转（当前节点为不平衡节点时）
     */
    public void rightRotate(){
        System.out.println("当前节点值：" + value + ",右旋");
        //1.新建节点newNode，值为不平衡节点的值
        TreeNode newNode = new TreeNode(value);
        //2.newNode右子节点设为不平衡节点的右子节点
        newNode.setRight(right);
        //3.newNode左子节点设为不平衡节点的左子节点的右子节点
        newNode.setLeft(left.getRight());
        //4.不平衡节点的值设为其左子节点的值
        value = left.getValue();
        //5.不平很节点的左子节点设为其左子节点的左子节点，右子节点设置为newNode
        left = left.getLeft();
        right = newNode;
    }
}
