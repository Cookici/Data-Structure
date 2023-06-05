package _04_DoublyLinkedList;

/**
 * @ProjectName: Data Structure
 * @Package: _04_DoublyLinkedList
 * @ClassName: DoublyLinkedList
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/5 20:52
 */

public class DoublyLinkedList {

    public static void main(String[] args) {

        System.out.println("双向链表测试");
        Node hero1 = new Node(1, "宋江", "及时雨");
        Node hero2 = new Node(2, "卢俊义", "玉麒麟");
        Node hero3 = new Node(3, "吴用", "智多星");
        Node hero4 = new Node(4, "公孙胜", "入云龙");
        Node hero5 = new Node(5, "公孙胜", "入云龙");

        System.out.println("添加后：");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero5);
        doubleLinkedList.show();

        System.out.println("修改后：");
        Node node = new Node(5, "大刀", "关胜");
        doubleLinkedList.update(node);
        doubleLinkedList.show();

        System.out.println("删除后：");
        doubleLinkedList.delete(5);
        doubleLinkedList.show();


        System.out.println("删除后：");
        doubleLinkedList.delete(2);
        doubleLinkedList.show();

        System.out.println("按顺序添加：");
        Node h2 = new Node(2, "卢俊义", "玉麒麟");
        Node h5 = new Node(5, "大刀", "关胜");
        doubleLinkedList.addByOrder(h2);
        doubleLinkedList.addByOrder(h5);
        doubleLinkedList.show();


    }


}

//创建双向链表
class Node {
    public int no;

    public String name;
    public String nickname;
    public Node next;   //指向下一个节点
    public Node pre;    //指向前一个节点

    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

class DoubleLinkedList {

    private Node headNode = new Node(0, "", "");

    public Node getHeadNode() {
        return headNode;
    }

    //显示链表
    public void show() {
        //判断链表是否为空
        if (headNode.next == null) {
            System.out.println("链表为空");
            return;
        }
        Node temp = headNode.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }


    //添加
    public void add(Node node) {
        //因为head节点不能动,因此需要一个辅助节点
        Node temp = headNode;
        //遍历链表,找到最后
        while (temp.next != null) {
            //如果没有找到，将temp后移
            temp = temp.next;
        }
        //构建双向链表
        temp.next = node;
        node.pre = temp;
    }

    //修改节点信息,根据no编号来修改
    public void update(Node node) {
        if (headNode.next == null) {
            System.out.println("链表为空");
            return;
        }

        Node temp = headNode.next;
        boolean flag = false; //是否找到该节点
        while (true) {
            if (temp == null) {
                break;  //已经遍历完链表
            }
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
            temp.nickname = node.nickname;
        } else {
            System.out.println("没有找到编号为" + node.no + "的节点");
        }

    }


    //删除
    public void delete(int no) {

        //判断当前链表是否为空
        if (headNode.next == null) {
            System.out.println("空链表");
            return;
        }

        Node temp = headNode.next;
        boolean flag = false;
        while (true) {
            if (temp == null) { //已经找到链表最后节点的最后
                break;
            }
            if (temp.no == no) {
                //待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;  //temp后移
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {        //最后一个节点不为空
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("要删除的节点" + no + "不存在");
        }
    }


    // 添加节点时，根据编号将节点插入到指定位置
    // 如果有这个编号，则添加失败，并给出提示
    public void addByOrder(Node node) {
// 头节点不能动，通过一个辅助指针（变量）帮助找到需要添加的位置
        Node temp = headNode;
        boolean flag = false;	// flag标志添加的编号是否存在，默认为false
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no > node.no) {
                break;
            }
            if(temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;	// 遍历链表
        }
        if(flag) {
            System.out.printf("输入的编号%d已经存在,不能加入\n", node.no);
        }
        else {
            // 为防止出现空指针的情况，需要对temp节点位置进行判断
            // 若双向链表尚未到达尾端，则需要将node节点与其相邻的后面的节点进行连接
            if(temp.next != null) {
                node.next = temp.next;
                temp.next.pre = node;
            }
            // 无论双向链表是否到达尾端，都需要将node节点与其相邻的前面的节点进行连接
            temp.next = node;
            node.pre = temp;
        }


    }


}