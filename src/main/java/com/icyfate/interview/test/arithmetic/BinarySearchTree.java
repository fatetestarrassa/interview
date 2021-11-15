package com.icyfate.interview.test.arithmetic;

import lombok.Data;

import java.util.Arrays;

/**
 * 二叉查找树：
 *      1.如果任意节点的左子树不为空，贼左子树上任一节点的值小于父节点的值
 *      2.如果任意节点的右子树不为空，贼右子树上任一节点的值大于父节点的值
 *      3.任意节点的左子树和右子树也都是二叉查找树
 *      4.没有键值相等的节点
 *
 *
 *      查找、插入的时间复杂度为Olog n
 *
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/10/18 15:00
 */
@Data
public class BinarySearchTree {

    private TreeNode root;

    /**
     * 插入节点，供外部调用
     *
     * @param value 插入的值
     * @return 成功返回true，否则false
     */
    public boolean insert(int value){
        if(root == null){
            root = new TreeNode(value);
            return true;
        }

        return insert(root,value);
    }

    /**
     * 插入节点,内部递归调用
     *
     *      1.如果节点值大于插入值，判断根节点的左子节点是否为空，是：则设置为根节点的左子节点，
     *                                                      否：设置根节点为左子节点，继续递归调用插入
     *      2.如果节点值小于插入值，跟步骤1类似。
     *
     * @param node  根节点(包括子树的根节点)
     * @param value 插入的值
     * @return 成功返回true，否则false
     */
    private boolean insert(TreeNode node, int value){
        if (node.getValue() > value){
            //节点值大于插入值，插入到左子树
            if(node.getLeft() == null){
                node.setLeft(new TreeNode(value));
                return true;
            } else {
                return insert(node.getLeft(),value);
            }
        } else if (node.getValue() < value){
            //节点值小于插入值，插入到右子树
            if(node.getRight() == null){
                node.setRight(new TreeNode(value));
                return true;
            } else {
                return insert(node.getRight(),value);
            }
        } else {
            return false;
        }
    }

    /**
     * 提供一个int数组快速初始化二叉查找树的方法，其中第一个元素会被设为根节点
     *
     * @param array  int数组
     * @return  返回一个新的二叉查找树
     */
    public static BinarySearchTree initTree(int[] array){
        BinarySearchTree tree = new BinarySearchTree();
        for(int i = 0; i < array.length ; i++){
            tree.insert(array[i]);
        }
        return tree;
    }

    /**
     * 查找节点
     *
     * @param value 要查找节点的值
     * @return  如果有则返回该节点，否则返回null
     */
    public TreeNode find(int value){

        if(root == null){
            return null;
        }
        return find(root,value);
    }

    /**
     * 查找节点,内部递归调用
     *
     * @param node      递归比较值的节点
     * @param value     要查找节点的值
     * @return  如果有则返回该节点，否则返回null
     */
    private TreeNode find(TreeNode node, int value){

        if(node == null){
            return null;
        }
        if(node.getValue() == value){
            return node;
        }
        if(node.getValue() > value){
            return find(node.getLeft(),value);
        }
        return find(node.getRight(),value);
    }

    /**
     * 查询整棵树的最小值节点
     *
     * @return
     */
    public TreeNode findMinNode(){
        return findMinNode(root);
    }

    /**
     * 查找以指定节点为根节点（不一定是整棵树，有可能是一部分子树）的树的最小值节点
     *
     * @param node 指定为根节点的节点
     * @return 最小值的节点
     */
    private TreeNode findMinNode(TreeNode node){
        if(node.getLeft() == null)
            return node;

        return findMinNode(node.getLeft());
    }

    /**
     * 删除节点
     *      删除根节点特殊处理：
     *          1.如果有无右子树，很简单，把原根节点的左子节点设为根节点即可
     *          2.如果有右子树，由于左子树小于又子树的任何一个节点，把左子树设置为右子树的最小节点的左子树即可
     *                  （右子树的最小节点必然是叶子节点，且是最左节点）
     *      非根节点，调用递归删除方法
     *
     * @param value 要删除的节点的值
     * @return  是否删除成功
     */
    public boolean delete(int value){
        if(root == null){
            return false;
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
            return true;
        }

        return delete(null,root,value);
    }

    /**
     * 删除节点：比较删除节点和递归节点的值
     *      1.递归节点的值大于要删除节点的值，递归左子树继续比较
     *      2.递归节点的值小于要删除节点的值，递归右子树继续比较
     *      3.递归节点的值等于要删除节点的值，说明递归节点就是要删除的节点，执行删除逻辑
     *          3.1 要删除节点是叶子节点，直接删除
     *          3.2 要删除的节点只有一个子节点，把唯一子节点设置要删除节点的父节点的相应子节点(删除节点为右则为右，左则为左)
     *          3.3 要删除的节点有2个子节点，找出删除节点的右子树里的最小节点，替代删除节点的位置（删除最小节点，设置最小节点为
     *              删除节点的父节点的相应子节点，设置删除节点左右子节点为最小接节点的左右子节点）。
     *
     *
     * @param parentNode    递归节点的父节点
     * @param node          递归节点
     * @param value         要删除节点的值
     * @return   是否删除成功
     */
    private boolean delete(TreeNode parentNode,TreeNode node, int value){

        if(node.getValue() > value){
            //node值大于要删除的值，循环查找左子树
            return delete(node,node.getLeft(),value);
        }
        if(node.getValue() < value){
            //node值小于要删除的值，循环查找右子树
            return delete(node,node.getRight(),value);
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

        return true;

    }

    public static void main(String[] args) {
//        BinarySearchTree tree = new BinarySearchTree();
//        tree.insert(4);
//        tree.insert(1);
//        tree.insert(2);
//        tree.insert(3);
//        tree.insert(5);
//        tree.insert(6);
//        tree.insert(7);

        int[] array = {4,2,1,3,6,5,7};//注意此处的顺序，为了形成一个满二叉树,如果，4123456根节点的左右子树会变成线性结构
        BinarySearchTree tree = initTree(array);
        Integer[] result = BinaryTreeDemo.inOrderTraverseByTemplate(tree.getRoot());
        System.out.println("初始化二叉查找树中序遍历结果：" + Arrays.toString(result));
        System.out.println(tree.find(6));
        System.out.println("查找最小值为：" + tree.findMinNode().getValue());

        int deleteVal = 6;
        tree.delete(deleteVal);

        System.out.println("删除节点" + deleteVal + "后结果：" + Arrays.toString(BinaryTreeDemo.inOrderTraverseByTemplate(tree.getRoot())));
        BinaryTreeDemo.levelTraverse(tree.getRoot());
    }


}
