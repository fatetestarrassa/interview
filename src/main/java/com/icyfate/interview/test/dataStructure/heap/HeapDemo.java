package com.icyfate.interview.test.dataStructure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 堆：是一种树，每个节点值都大于等于（或者小于等于）子树中所有节点的值    也就是说任何一个节点的值都大于等于（或者小于等于）子节点
 *      斐波那契堆和二项堆不是完全二叉树
 *      二叉堆是一个数组，可以被看成一个近似的完全二叉树
 * 最大堆：大于等于子节点的值
 * 最小堆：小于等于子节点的值
 *
 * 堆的存储方式：数组存储，节省空间，也方便索引。根节点序号为1（数组下表），左子节点序号为2*i, 右子节点序号为2*i + 1
 *
 * 堆操作：
 *      插入元素
 *      删除堆顶元素
 *          自底向上堆化：元素从底部向上移动
 *          自顶向下堆化：元素从顶部向下移动
 *
 * 堆排序：
 *      1.建堆
 *      2.排序
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/9/23 14:35
 */
public class HeapDemo {

    public static int[] addElement(int[] heap,int element){
        //插入元素：1先插入到最后位置 2.然后循环和父节点比较，如果大于父节点则交换位置，直至第一个节点
        int newLength = heap.length + 1;
        int[] copyHeap = Arrays.copyOf(heap,newLength);
        int index = newLength - 1;
        copyHeap[index] = element;//插入元素，放到最后
        while(index > 1){

            int parentIndex = index / 2;//父节点下标
            if(element > copyHeap[parentIndex] ){
                copyHeap[index] = copyHeap[parentIndex];
                copyHeap[parentIndex] = element;
                index = parentIndex;
            } else {
                break;
            }

        }

        return copyHeap;
    }

    /**
     * 删除堆顶并且自底向上堆化，不推荐，会出现气泡，空间浪费
     * @param heap
     * @return
     */
    public static int[] delRootAndHeapFromBottom(int[] heap){

        int index = 1;
        while(true){
            int leftChildIndex = 2 * index;
            int rightChildIndex = 2 * index + 1;
            if(leftChildIndex > heap.length - 1){
                break;
            }
            int replaceIndex = 0;
            if(leftChildIndex == heap.length -1){
                replaceIndex = leftChildIndex;
            } else {
                if(heap[leftChildIndex] > heap[rightChildIndex]){
                    replaceIndex = leftChildIndex;
                } else {
                    replaceIndex = rightChildIndex;
                }
            }
            heap[index] = heap[replaceIndex];
            heap[replaceIndex] = 0;
            index = replaceIndex;

        }
        return heap;
    }

    /**
     * 删除堆顶并且自顶上下堆化
     *      1.先把最后一个节点提到顶部
     *      2.然后顶部节点和左右子节点的较大值比较，如果子节点大，交换位置,循环上述操作，直至无法交换位置
     * @param heap 堆
     * @param lasNodetIndex 指定最后一个节点下标
     * @return 返回重新堆化后的结果
     */
    public static int[] delRootAndHeapFromTop(int[] heap, int lasNodetIndex){
        //1.最后一个节点提到顶部
        int index = 1;
        heap[index] = heap[lasNodetIndex];
        heap[lasNodetIndex] = 0;
        //2.顶部节点和左右子节点的较大值比较
        while(true){
            int result = interchangeOneNode(heap,index,lasNodetIndex);
            if(result == index){
                break;
            } else {
                index = result;
            }

        }

        return heap;
    }

    /**
     * 交换单个节点位置
     *
     * @param heap  堆
     * @param index 要交换的父节点下标
     * @param lasNodetIndex 指定最后一个节点下标
     * @return  交换成功返回原节点被交换到的位置下标，否则返回原位置下标
     */
    private static int interchangeOneNode(int[] heap, int index, int lasNodetIndex){
        int leftChildIndex = 2 * index;
        int rightChildIndex = 2 * index + 1;
        if(leftChildIndex > lasNodetIndex){
            return index;
        }
        int replaceIndex = 0;
        if(leftChildIndex == lasNodetIndex){
            if(heap[index] < heap[leftChildIndex]){
                replaceIndex = leftChildIndex;
            }
        } else {
            int maxChildIndex = 0;
            if(heap[leftChildIndex] > heap[rightChildIndex]){
                maxChildIndex = leftChildIndex;
            } else {
                maxChildIndex = rightChildIndex;
            }
            if(heap[index] < heap[maxChildIndex]){
                replaceIndex = maxChildIndex;
            }
        }

        if(replaceIndex != 0){
            int temp = heap[index];
            heap[index] = heap[replaceIndex];
            heap[replaceIndex] = temp;
            index = replaceIndex;
        }

        return index;
    }

    /**
     * 堆排序
     *      1、无序数组建堆
     *      2、不断进行去顶操作
     * @param array
     * @return
     */
    public static int[] heapSort(int[] array){
        //1.建堆：假设数组长度为n，则下标小于等于n/2的节点需要建堆，倒序建堆
        int index = array.length/2;
        for(int i = index;i > 0;i--){
            interchangeOneNode(array,i,array.length - 1);
        }
        //2.排序，循环删除堆顶并且自顶上下堆化(i=2时，数组剩下2个有效节点，去顶最大节点，只剩下一个最小元素，无需继续循环)
        for(int i = array.length - 1; i > 1; i--){
            int topValue = array[1];
            array[1] = 0;
            delRootAndHeapFromTop(array,i);
            array[i] = topValue;
        }

        return array;

//        List<Integer> list = new ArrayList<>();
//        while(array.length > 1){
//            int topValue = array[1];
//            array[1] = 0;
//            delRootAndHeapFromTop(array);
//            array = Arrays.copyOf(array,array.length - 1);
//            list.add(topValue);
//        }
//        return list.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        //初始化一个堆，数组结构，下标从1开始(0下标可忽略)
        int[] heap = {0,21,15,19,10,7,3,1,5,2};
        int[] copyHeap = addElement(heap,17);
        System.out.println("插入元素17后，堆数据：" + Arrays.toString(copyHeap));
        //删除堆顶元素自底向上堆化
        heap[1] = 0;
        int[] heapFromBottom = delRootAndHeapFromBottom(heap);
        System.out.println("删除堆顶元素自底向上堆化后，堆数据：" + Arrays.toString(heapFromBottom));
        //删除堆顶元素自顶向下堆化
        int[] heap1 = {0,21,15,19,10,7,3,1,5,2};
        heap1[1] = 0;
        int[] heapFromTop = delRootAndHeapFromTop(heap1,heap1.length-1);
        System.out.println("删除堆顶元素自顶向下堆化后，堆数据：" + Arrays.toString(heapFromTop));

        //堆排序
        int[] heap2 = {0,19,7,3,11,1,20};
        int[] sortArray = heapSort(heap2);
        System.out.println("堆排序后，数据：" + Arrays.toString(sortArray));
    }
}
