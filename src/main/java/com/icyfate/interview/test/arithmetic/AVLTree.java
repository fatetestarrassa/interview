package com.icyfate.interview.test.arithmetic;

import lombok.Data;

/**
 * 二叉平衡树（AVL树）：如果一个二叉查找树（BST树），每一个节点的左右子树高度差的绝对值不超过1，就是一个二叉平衡树
 *      解决BST某些情况会变成线性结构的问题。
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/10/29 15:27
 */
@Data
public class AVLTree {
    private TreeNode root;

    /**
     * 插入节点，供外部调用
     *
     * @param value 插入的值
     */
    public void insert(int value){
        if(root == null){
            root = new TreeNode(value);
        }
        System.out.println("插入节点：" + value);
        insert(root,value);
    }

    private void insert(TreeNode node, int value){
        if (node.getValue() > value){
            //节点值大于插入值，插入到左子树
            if(node.getLeft() == null){
                node.setLeft(new TreeNode(value));
            } else {
                insert(node.getLeft(),value);
            }
        } else if (node.getValue() < value){
            //节点值小于插入值，插入到右子树
            if(node.getRight() == null){
                node.setRight(new TreeNode(value));
            } else {
                insert(node.getRight(),value);
            }
        }

        balance(node);
    }

    private void balance(TreeNode node){
        if(node.getLeftHeight() - node.getRightHeight() > 1){
            //如果左子节点高度 - 右子节点高度大于1
            TreeNode leftNode = node.getLeft();
            if(leftNode.getRightHeight() > leftNode.getLeftHeight()){
                /**
                 * leftNode的 右子树高度 是否大于 左子树高度，
                 *      是：则是左右型，需要双旋转，以leftNode为根节点的子树左旋，然后以node为根节点的树右旋
                 *      否：否则左左型，仅右旋一次即可
                 */

                leftNode.leftRotate();
            }
            //以node为根节点的树右旋
            node.rightRotate();
        } else if (node.getRightHeight() - node.getLeftHeight() > 1){
            //如果右子节点高度 - 左子节点高度大于1
            TreeNode rightNode = node.getRight();
            /**
             * rightNode 左子树高度 是否大于 右子树高度，
             *      是：则是右左型，需要双旋转，以rightNode为根节点的子树右旋，然后以node为根节点的树左旋
             *      否：否则右右型，仅左旋一次即可
             */
            if(rightNode.getLeftHeight() > rightNode.getRightHeight()){
                rightNode.rightRotate();
            }

            node.leftRotate();
        }


    }

    public static void main(String[] args) {
        //左左型
        AVLTree llTree = new AVLTree();
        TreeNode llRoot = new TreeNode(5);
        llTree.setRoot(llRoot);
        llTree.insert(6);
        llTree.insert(3);
        llTree.insert(2);
        llTree.insert(4);
        llTree.insert(1);
        System.out.println("左左型平衡结果：");
        BinaryTreeDemo.levelTraverse(llRoot);
        //左右型
        AVLTree lrTree = new AVLTree();
        TreeNode lrRoot = new TreeNode(5);
        lrTree.setRoot(lrRoot);
        lrTree.insert(6);
        lrTree.insert(2);
        lrTree.insert(3);
        lrTree.insert(1);
        lrTree.insert(4);
        System.out.println("左右型平衡结果：");
        BinaryTreeDemo.levelTraverse(lrRoot);
        //右右型
        AVLTree rrTree = new AVLTree();
        TreeNode rrRoot = new TreeNode(2);
        rrTree.setRoot(rrRoot);
        rrTree.insert(1);
        rrTree.insert(4);
        rrTree.insert(3);
        rrTree.insert(5);
        rrTree.insert(6);
        System.out.println("右右型平衡结果：");
        BinaryTreeDemo.levelTraverse(rrRoot);
        //右左型
        AVLTree rlTree = new AVLTree();
        TreeNode rlRoot = new TreeNode(2);
        rlTree.setRoot(rlRoot);
        rlTree.insert(1);
        rlTree.insert(5);
        rlTree.insert(4);
        rlTree.insert(6);
        rlTree.insert(3);
        System.out.println("右左型平衡结果：");
        BinaryTreeDemo.levelTraverse(rlRoot);

    }
}
