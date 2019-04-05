import org.junit.Test;

import java.io.DataOutputStream;
import java.util.HashMap;

/**
 * Created by 周大侠
 * 2019-01-17 9:27
 */
public class ExtHashMap<K, V> implements ExtMap<K, V> {
    // 1.定义table 存放HasMap 数组元素 默认是没有初始化容器 懒加载
    Node<K, V>[] table = null;
    // 2. 实际用到table 存储容量 大小
    int size;
    // 3.HashMap默认负载因子，负载因子越小，hash冲突机率越低， 根据每个链表的个数
    float DEFAULT_LOAD_FACTOR = 0.75f;
    // 4.table默认初始大小 16
    static int DEFAULT_INITIAL_CAPACITY = 16; // 16


    @Override
    public V put(K k, V v) {

        if (table == null) {
            // 如果table为空初始化节点数组
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }
        if (size > table.length * DEFAULT_LOAD_FACTOR) {
            resize();
        }
        int index = getIndex(k,table.length);
        Node<K, V> firstNode = table[index];
        if (firstNode == null) {
            // 如果该位置没有节点 没有哈希冲突 直接创建一个新的节点 他的下一个节点为NULL
            firstNode = new Node(k, v, null);

        } else {  // 该位置有节点 需要解决哈希冲突
            // 为了保留头节点 不能让头节点移动 因为最后需要创建新节点指向头节点
            Node<K, V> node = firstNode;
            // 遍历该位置的节点
            while (node != null) {
                // 该位置KEY与添加的key重复 则直接覆盖VALUE值 结束方法 不需要遍历了
                if (node.getKey().equals(k)||node.getKey()==k) {
                    return node.setValue(v);
                }
                // 如果遍历到最后一个节点 还没有找到重复的KEY的节点 那就直接创建一个新的节点 指向第一个节点
                if (node.next == null) {

                    firstNode = new Node(k, v, firstNode);
                }
                // 为了遍历该位置中的每个节点
                node = node.next;
            }
        }
        table[index] = firstNode;
        size++;

        return null;
    }

    private void resize() {
        /*
         * 1.创建一个新的数组
         * 2.遍历老数组 得到每一个节点
         * 3.重新哈希 放入新的数组中
         */
        System.out.println("马上进行扩容");
        Node<K, V>[] newTable = new Node[table.length << 1];
        for (int i = 0; i < table.length; i++) {
            Node<K,V> node = table[i];
            while (node != null) {
                table[i]=null;
                int index = getIndex(node.getKey(),newTable.length);

                node.next = newTable[index];

                newTable[index] = node;
                System.out.println("复制");
                node = node.next;
            }
        }

        table = newTable;
        DEFAULT_INITIAL_CAPACITY=newTable.length;
        newTable = null;


    }

    @Override
    public V get(K k) {
        int index = getIndex(k,table.length);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.getKey().equals(k)) {
                return node.getValue();
            }
            node = node.next;
        }


        return null;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 根据key计算出哈希值
     *
     * @param k
     * @return
     */
    public int getIndex(K k,int length) {
        return k.hashCode() % length;
    }

    void print(){
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            System.out.print("索引"+i+":");

            while (node!=null){
                System.out.print(node.getKey()+":"+node.getValue()+",");
                node = node.next;
            }
            System.out.println();
        }
    }

    /**
     * 其实就是Entry 多了一个next节点 用于解决哈希冲突
     *
     * @param <K>
     * @param <V>
     */
    class Node<K, V> implements Entry<K, V> {
        private K k;
        private V v;
        private Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "k=" + k +
                    ", v=" + v +
                    '}';
        }

        public Node(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V value) {
            this.v=value;
            return v;
        }
    }
    @Test
    public void fun1(){
        ExtHashMap extHashMap = new ExtHashMap();
        for (int i = 0; i < 123; i++) {
            extHashMap.put(i*234234,i);
        }
        extHashMap.print();

    }

}
