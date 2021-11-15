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

    public TreeNode findMinNode(){
        return findMinNode(root);
    }

    private TreeNode findMinNode(TreeNode node){
        if(node.getLeft() == null)
            return node;

        return findMinNode(node.getLeft());
    }

    public void delete(int value){
        if(root == null){
            return;
        }
        //处理删除根节点的情况
        if(root.getValue() == value){
            if(root.getRight() == null){
                root = root.getLeft();
            } else {
                TreeNode node = findMinNode(root.getRight());
                node.setLeft(root.getLeft());
                root = root.getRight();
            }
            return;
        }

        delete(null,root,value);
    }

    private void delete(TreeNode parentNode,TreeNode node, int value){

        if(node.getValue() > value){
            //node值大于要删除的值，循环查找左子树
            delete(node,node.getLeft(),value);
            return;
        }
        if(node.getValue() < value){
            //node值小于要删除的值，循环查找右子树
            delete(node,node.getRight(),value);
            return;
        }

        boolean isLeft = parentNode.getValue() > node.getValue();
        //node值等于要删除的值，是要删除的节点
        if(node.getRight() == null && node.getLeft() == null){
            //如果node是叶子节点：直接删除
            if(isLeft){
                //说明node是parent的左子节点，设置左子节点为null即可
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else if(node.getLeft() != null && node.getRight() != null){
            /**
             * 如果node左右子节点都存在,查询当前节点的右子树的最小节点(此处思考为什么是右子树的最小节点，其实保证
             *      只移动一个节点就完成删除操作，因为此节点必然大于所有左子树节点，又小于其他右子树节点。同理查询
             *      左子树的最大节点也行)
             */

            TreeNode minNode = findMinNode(node.getRight());
            delete(minNode.getValue());//删除最小节点，此节点必定是叶子元素，会直接移除
            if(isLeft){
                parentNode.setLeft(minNode);
            } else {
                parentNode.setRight(minNode);
            }
            minNode.setLeft(node.getLeft());
            minNode.setRight(node.getRight());

        } else {
            //有一个子节点:把node唯一子节点放在node的位置
            TreeNode childNode = node.getLeft() == null?node.getRight():node.getLeft();
            if(isLeft){
                parentNode.setLeft(childNode);
            } else {
                parentNode.setRight(childNode);
            }

        }


        balance(parentNode);
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
        BinaryTreeDemo.levelTraverse(llTree.getRoot());
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
        BinaryTreeDemo.levelTraverse(lrTree.getRoot());
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
        BinaryTreeDemo.levelTraverse(rrTree.getRoot());
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
        BinaryTreeDemo.levelTraverse(rlTree.getRoot());

        //删除节点
        AVLTree delTree = new AVLTree();
        TreeNode delRoot = new TreeNode(4);
        delTree.setRoot(delRoot);
        delTree.insert(5);
        delTree.insert(2);
        delTree.insert(1);
        delTree.insert(3);
        delTree.delete(5);
        BinaryTreeDemo.levelTraverse(delTree.getRoot());

    }
}
