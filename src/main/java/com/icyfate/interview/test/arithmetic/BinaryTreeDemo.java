package com.icyfate.interview.test.arithmetic;

import java.util.*;

/**
 * 二叉树：是n(n>=0)个结点的有限集合，该集合或者为空集（称为空二叉树），或者由一个根结点和两棵互不相交的、分别称为根结点的左子树和右子树组成。
 *         个人理解：最多有2个孩子节点（即左子树和右子树）且子树不能相交
 *      节点：树结构的中的基本单位
 *      节点度：节点子树的数量
 *      节点关系：双亲节点、孩子节点、兄弟节点
 *      节点层次：根节点为第一层，依次。。
 *      树的深度、高度
 *
 * 普通二叉树：
 *      1）在二叉树的第i层上最多有2^(i-1)个节点 。（i>=1）              个人理解：按2进制理解，第一层 2^0  第二层 2^1 依次类推
 *      2）二叉树中如果深度为k,那么最多有2^k-1个节点。(k>=1）             个人理解：按2进制理解，下一位刚好是2^k个，然后减一
 *      3）n0=n2+1 n0表示度数为0的节点数，n2表示度数为2的节点数。        个人理解，每多一个n2,就会多2个n0,然后少一个n0，最后结果是不多不少
 *
 * 完全二叉树：具有n个节点的二叉树按层编号，如果任意一点的编号都和满二叉树编号相同，则为完全二叉树（区别是节点有可能只有左子树）
 *      4）在完全二叉树中，具有n个节点的完全二叉树的深度为[log2n]+1，其中[log2n]是向下取整。
 *      5）若对含 n 个结点的完全二叉树从上到下且从左至右进行 1 至 n 的编号，则对完全二叉树中任意一个编号为 i 的结点有如下特性：
 *          (1) 若 i=1，则该结点是二叉树的根，无双亲, 否则，编号为 [i/2] 的结点为其双亲结点;
 *          (2) 若 2i>n，则该结点无左孩子， 否则，编号为 2i 的结点为其左孩子结点；
 *          (3) 若 2i+1>n，则该结点无右孩子结点， 否则，编号为2i+1 的结点为其右孩子结点。
 *
 *      1）叶子结点只能出现在最下层和次下层。
 *      2）最下层的叶子结点集中在树的左部。
 *      3）倒数第二层若存在叶子结点，一定在右部连续位置。
 *      4）如果结点度为1，则该结点只有左孩子，即没有右子树。
 *      5）同样结点数目的二叉树，完全二叉树深度最小。
 *      注：满二叉树一定是完全二叉树，但反过来不一定成立。
 *
 * 满二叉树：所有分支节点(非叶子节点)都存在左子树和右子树，并且所有叶子节点都处在同一层
 *      1）叶子只能出现在最下一层。出现在其它层就不可能达成平衡。
 *      2）非叶子结点的度一定是2。
 *      3）在同样深度的二叉树中，满二叉树的结点个数最多，叶子数最多。
 *
 * 二叉树遍历：
 *      1.前序遍历: 理解方式：父节点——>左子节点——>右子节点
 *          实现方式：从根节点开始，递归的，先输出父节点，然后遍历左子树，最后遍历右子树
 *      2.中序遍历：理解：左子节点——>父节点——>右子节点
 *          实现方式：从根节点开始，递归的，先遍历左子树，然后输出父节点，最后遍历右子树
 *      3.后序遍历：理解：左子节点——>右子节点——>父节点
 *          实现方式：从根节点开始，递归的，先遍历左子树，然后遍历右子树，最后输出父节点
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/22 15:25
 */
public class BinaryTreeDemo {

    private static List<Integer> recursiveResultList;

    /**
     * 递归前序遍历
     * @param parent 父节点
     */
    public static void recursivePreOrderTraverse(TreeNode parent){
        if(parent != null){
            recursiveResultList.add(parent.getValue());
            recursivePreOrderTraverse(parent.getLeft());
            recursivePreOrderTraverse(parent.getRight());
        }
    }

    /**
     * 递归中序遍历
     *
     * @param parent
     */
    public static void recursiveInOrderTraverse(TreeNode parent){
        if(parent != null){
            recursiveInOrderTraverse(parent.getLeft());
            recursiveResultList.add(parent.getValue());
            recursiveInOrderTraverse(parent.getRight());
        }
    }

    /**
     * 递归后序遍历
     *
     * @param parent
     */
    public static void recursivePostOrderTraverse(TreeNode parent){
        if(parent != null){
            recursivePostOrderTraverse(parent.getLeft());
            recursivePostOrderTraverse(parent.getRight());
            recursiveResultList.add(parent.getValue());
        }
    }

    /**
     * 非递归前序遍历实现：
     *      1.栈中压入根节点
     *      2.循环弹出栈顶节点，并且依次压入该节点的右子节点和左子节点，直至栈为空
     *
     * @param parent
     * @return
     */
    public static Integer[] preOrderTraverse(TreeNode parent){
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(parent);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            resultList.add(node.getValue());
            if(node.getRight() != null){
                stack.push(node.getRight());
            }
            if(node.getLeft() != null){
                stack.push(node.getLeft());
            }

        }

        return resultList.toArray(new Integer[resultList.size()]);
    }

    /**
     * 非递归前序遍历(模板解法)
     *      1.从根节点开始设为当前节点
     *      2.（如果currNode不为null，否则跳过这一步，执行步骤3）循环栈中压入当前节点的全部左子节点，并且将这些节点放入结果集
     *      3.弹出栈顶节点，设置当前节点为当前节点的右子节点，
     *      4.重复2、3步骤，直至当前节点和栈都为空
     *
     * @param parentNode
     * @return
     */
    public static Integer[] preOrderTraverseByTemplate(TreeNode parentNode){
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = parentNode;
        while(currentNode != null || !stack.isEmpty()){
            while(currentNode != null){
                stack.push(currentNode);
                resultList.add(currentNode.getValue());
                currentNode = currentNode.getLeft();
            }

            TreeNode temp = stack.pop();
            currentNode = temp.getRight();
        }

        return resultList.toArray(new Integer[resultList.size()]);
    }

    /**
     * 非递归中序遍历(模板解法)
     *
     * @param parentNode
     * @return
     */
    public static Integer[] inOrderTraverseByTemplate(TreeNode parentNode){
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = parentNode;
        while(currentNode != null || !stack.isEmpty()){
            while(currentNode != null){
                stack.push(currentNode);
                currentNode = currentNode.getLeft();
            }

            TreeNode temp = stack.pop();
            resultList.add(temp.getValue());
            currentNode = temp.getRight();
        }

        return resultList.toArray(new Integer[resultList.size()]);
    }

    /**
     * 非递归后序遍历(模板解法)
     *
     * @param parentNode
     * @return
     */
    public static Integer[] postOrderTraverseByTemplate(TreeNode parentNode){
        //1.参考非递归前序，只不过循环放入右子节点，然后弹出栈顶节点，设置其左子节点为当前节点
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = parentNode;
        while(currentNode != null || !stack.isEmpty()){
            while(currentNode != null){
                stack.push(currentNode);
                resultList.add(currentNode.getValue());
                currentNode = currentNode.getRight();
            }

            TreeNode temp = stack.pop();
            currentNode = temp.getLeft();
        }
        //2.最后反转输出即可
        Collections.reverse(resultList);
        return resultList.toArray(new Integer[resultList.size()]);
    }

    /**
     * 非递归后序遍历（常规解法）
     *      1.从根节点开始，压入栈中，flag 设为0
     *      2.循环操作如下； 弹出栈顶节点，如果flag = 0，设为1，继续压入栈中,并且按顺序压入其右子节点、左子节点（flag设为0）
     *                                  如果flag = 1，放入结果集中
     *        直至栈为空。
     *
     *
     * @param root
     * @return
     */
    public static Integer[] postOrderTraverse(TreeNode root){
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        root.setFlag(0);
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode tempNode = stack.pop();
            if(tempNode.getFlag() == 0){
                tempNode.setFlag(1);
                stack.push(tempNode);
                TreeNode rightNode = tempNode.getRight();
                if(rightNode != null){
                    rightNode.setFlag(0);
                    stack.push(rightNode);
                }
                TreeNode leftNode = tempNode.getLeft();
                if(leftNode != null){
                    leftNode.setFlag(0);
                    stack.push(leftNode);
                }
            } else {
                resultList.add(tempNode.getValue());
            }
        }


        return resultList.toArray(new Integer[resultList.size()]);
    }

    public static void levelTraverse(TreeNode root){
        List<List<Integer>> resultList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                levelList.add(node.getValue());
                TreeNode leftNode = node.getLeft();
                if(leftNode != null){
                    queue.offer(leftNode);
                }
                TreeNode rightNode = node.getRight();
                if(rightNode != null){
                    queue.offer(rightNode);
                }
            }
            resultList.add(levelList);
        }

        System.out.println(resultList);

    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        root.setLeft(node2);
        root.setRight(node3);

        recursiveResultList = new ArrayList<>();
        recursivePreOrderTraverse(root);
        System.out.println("递归前序遍历：" + Arrays.toString(recursiveResultList.toArray(new Integer[recursiveResultList.size()])));
        System.out.println("非递归前序遍历常规实现：" + Arrays.toString(preOrderTraverse(root)));
        System.out.println("非递归前序遍历模板解法实现：" + Arrays.toString(preOrderTraverseByTemplate(root)));

        recursiveResultList = new ArrayList<>();
        recursiveInOrderTraverse(root);
        System.out.println("递归中序遍历：" + Arrays.toString(recursiveResultList.toArray(new Integer[recursiveResultList.size()])));
        System.out.println("非递归中序遍历模板解法实现：" + Arrays.toString(inOrderTraverseByTemplate(root)));

        recursiveResultList = new ArrayList<>();
        recursivePostOrderTraverse(root);
        System.out.println("递归后序遍历：" + Arrays.toString(recursiveResultList.toArray(new Integer[recursiveResultList.size()])));
        System.out.println("非递归后序遍历模板解法实现：" + Arrays.toString(postOrderTraverseByTemplate(root)));
        System.out.println("非递归后序遍历模板常规实现：" + Arrays.toString(postOrderTraverse(root)));

        System.out.println("层次遍历：");
        levelTraverse(root);
    }
}
