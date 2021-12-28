package com.icyfate.interview.test.arithmetic;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 红黑树：理解为由2-3树演变而来，3-node的左元素抽出来作为右元素（右元素定义为h）的左子节点，并用红线连接，才是已经是红黑树的雏形，
 *          进一步把连线颜色描述为子节点颜色，就变成了真正的红黑树。（其实上述定义指的是左连接红黑树，接下来也是主要介绍这种红黑树），
 *          红黑树即是二叉树，又是2-3树。
 *      1.节点是红色或黑色
 *      2.根节点是黑色
 *      3.每个叶子节点都是黑色的空节点（NIL节点）
 *      4.每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 *      5.从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。(2-3树本身是完美平衡的，演变成红黑树之后，3-node拆分后也只是
 *          多了条红链接，不会影响黑链接的数量,可以称之为完美黑色平衡)
 *
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/12/6 11:00
 */
@Data
public class RedBlackTree<Key extends Comparable<Key>,Value>{

    private Node root;//根节点
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    /**
     * 红黑树节点class
     *
     * @param <K>   键（二叉树排序字段）
     * @param <V>   值
     */
    private class Node<K,V>{
        K k;//key
        V v;//value
        Node left,right;//左右子节点
        boolean color;//父节点指向它的链接颜色（节点颜色）

        public Node(K k, V v, boolean color) {
            this.k = k;
            this.v = v;
            this.color = color;
        }

    }

    private boolean isRed(Node n){
        if(n == null){
            return false;
        }
        return n.color == RED;
    }

    /**
     * 左旋转
     *
     * @param h 旋转子树的根节点
     * @return 旋转后新子树的根节点
     */
    Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    /**
     * 右旋转
     *
     * @param h
     * @return
     */
    Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    /**
     * 变色
     *
     * @param h
     */
    private void flipColor(Node<Key, Value> h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key,Value value){
        //查找key，查到则更新它的value，查不到插入新节点
        root = put(root,key,value);
        //变色操作可能导致根节点变为红色，故重新设置为黑色
        root.color = BLACK;
    }

    /**
     * 插入新节点
     *      1.新插入节点均为红连接，插入key等于节点key的情况更新节点value，否则插入新节点
     *      2.向2-node中插入
     *          如果插入key 小于节点key，插入新节点，红色左链接
     *          如果插入key 大于节点key，插入新节点，红色右链接，左旋
     *      3.向3-node中插入
     *          1）如果key大于3-node中的2个key，插入新节点，为红色右连接（此时左右连接均为红色），变色
     *          2）如果key小于3-node中的2个key，插入新节点，为红色左连接（此时，左连接和左连接的左连接都是红色），
     *              把上层的红连接右旋，变为第一种情况，然后变色
     *          3）如果key介于3-node中的2个key之间，插入新节点，为红色右链接（此时，左连接和左连接的右连接都是红色），
     *              把下层的红连接左旋，变为第二种情况，然后上层红连接右旋，变为第一种情况，然后变色。
     *
     *      4.通过递归调用保证旋转后，重新设置h节点对应子树的子节点，同时也保证了变色后（变色后h节点连接为红色，相当于把h节点向上传递，
     *          插入到父节点），继续向上遍历父节点是否需要继续旋转或者变色。
     *
     *
     * @param h
     * @param key
     * @param value
     * @return
     */
    private Node put(Node<Key,Value> h,Key key,Value value){
        if(h == null)
            //标准插入操作，新节点为红连接
            return new Node(key,value,RED);

        int cmp = key.compareTo(h.k);
        if(cmp < 0)
            h.left = put(h.left,key,value);//新插入键小于节点键,设置为左子节点
        else if (cmp > 0)
            h.right = put(h.right,key,value);//新插入键大于节点键，设为值右子节点
        else
            h.v = value;//新插入键等于节点键，更新节点值

        if(isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);//子树根节点的右连接为红色，左连接为黑色，需要左旋
        if(isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);//子树根节点的左连接为红色，左孙子连接也为红色，需要右旋
        if(isRed(h.left) && isRed(h.right))
            flipColor(h);//子树根节点的左右连接均为红色，变色

        return h;
    }

    private void levelTraverse(){
        if(root == null)
            return ;
        Queue<Node<Integer,String>> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0;i < size;  i ++){
                Node<Integer,String> node =  queue.poll();
                System.out.print(node.k + "_" + node.v + (isRed(node)?"红色":"黑色") + "   ");
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        RedBlackTree<Integer,String> tree = new RedBlackTree<>();
        tree.put(3,"3");
        tree.put(2,"2");
        tree.put(1,"1");
        tree.levelTraverse();
    }

}
