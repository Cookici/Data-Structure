package _03_LinkedList;

import java.util.Stack;

import static _03_LinkedList.SingleLinkedList.*;

/**
 * @ProjectName: Data Structure
 * @Package: _03_LinkedList
 * @ClassName: LinkedList
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/5 10:48
 */

public class LinkedList {

    public static void main(String[] args) {
        Node hero1 = new Node(1, "宋江", "及时雨");
        Node hero2 = new Node(2, "卢俊义", "玉麒麟");
        Node hero3 = new Node(3, "吴用", "智多星");
        Node hero4 = new Node(4, "公孙胜", "入云龙");
        Node hero5 = new Node(5, "公孙胜", "入云龙");

        System.out.println("插入操作：");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.show();
        System.out.println("--------------------------------------------------");
        System.out.println("更新操作");
        singleLinkedList.update(new Node(5, "关胜", "大刀"));
        singleLinkedList.show();
        System.out.println("--------------------------------------------------");
        System.out.println("删除操作：");
        singleLinkedList.delete(1);
        singleLinkedList.delete(5);
        singleLinkedList.show();
        System.out.println("--------------------------------------------------");
        System.out.println("有效节点数为: " + getLength(singleLinkedList.getHeadNode()));
        System.out.println("--------------------------------------------------");
        System.out.println("倒数第3个节点为: " + findLastIndexNode(singleLinkedList.getHeadNode(), 3));
        System.out.println("--------------------------------------------------");
        System.out.println("反转链表：");
        reverseLinkedList(singleLinkedList.getHeadNode());
        singleLinkedList.show();
        System.out.println("--------------------------------------------------");
        System.out.println("使用栈反转链表：");
        reversePrint(singleLinkedList.getHeadNode());
        System.out.println("--------------------------------------------------");
        System.out.println("合并两个链表，并且顺序排列：");
        Node h1 = new Node(1, "宋江", "及时雨");
        Node h2 = new Node(2, "卢俊义", "玉麒麟");
        Node h3 = new Node(3, "吴用", "智多星");
        Node h4 = new Node(4, "公孙胜", "入云龙");
        Node h5 = new Node(5, "大刀", "关胜");
        System.out.println("链表一：");
        SingleLinkedList one = new SingleLinkedList();
        one.add(h4);
        one.add(h2);
        one.show();
        System.out.println("链表二：");
        SingleLinkedList two = new SingleLinkedList();
        two.add(h3);
        two.add(h1);
        two.add(h5);
        two.show();
        System.out.println("合并后：");
        mergeLinkedList(one.getHeadNode(), two.getHeadNode());
        one.show();
    }

}

//定义SingleLinkedList管理节点
class SingleLinkedList {

    //初始化一个头节点,不存放具体的数据
    private Node headNode = new Node(0, "", "");


    public Node getHeadNode() {
        return headNode;
    }

    public void setHeadNode(Node headNode) {
        this.headNode = headNode;
    }

    //添加单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后接结点
    //2.将最后这个节点的next指向新节点
    public void add(Node node) {
        //因为head节点不能动,因此需要一个辅助节点
        Node temp = headNode;
        //遍历链表,找到最后
        while (temp.next != null) {
            //如果没有找到，将temp后移
            temp = temp.next;
        }
        //当退出while循环的时候，temp就指向了链表的最后
        temp.next = node;
    }


    //顺序添加 有则添加失败
    public void addByOrder(Node node) {
        Node temp = headNode;
        //找到添加位置的前一个节点
        boolean flag = false; //添加的编号是否存在
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {
            System.out.println("准备插入数据的编号" + node.no + "已经存在");
        } else {
            node.next = temp.next;
            temp.next = node;
        }

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


    //修改节点信息,根据no编号来修改
    //1.根据node的no来修改
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
        Node temp = headNode;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                //待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;  //temp后移
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的节点" + no + "不存在");
        }
    }


    //获取到单链表节点的个数(带头节点的链表不计算头节点)
    public static int getLength(Node headNode) {
        if (headNode.next == null) {
            return 0;
        }
        int length = 0;
        Node temp = headNode.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找单链表倒数第k个节点
    //1.编写一个方法接受head节点，同时接受一个index
    //2.index表示是倒数第index个节点
    //3.先把链表从头到位遍历,得到总长度getLength
    //4.得到size后，我们从链表的第一个开始遍历(size-index)个，就可以得到
    public static Node findLastIndexNode(Node headNode, int index) {
        if (headNode.next == null) {
            return null;
        }
        int size = getLength(headNode);
        if (index <= 0 || index > size) {
            return null;
        }
        Node temp = headNode.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }


    //反转链表
    public static void reverseLinkedList(Node headNode) {

        if (headNode.next == null || headNode.next.next == null) {
            return;
        }

        Node reverseHeadNode = new Node(0, "", "");

        Node temp = headNode.next;

        Node buffer = null;

        while (temp != null) {

            buffer = temp;          //暂时保存当前节点
            temp = temp.next;       //将节点后移
            buffer.next = reverseHeadNode.next;     //始终将保存的节点放在头链表的后面
            reverseHeadNode.next = buffer;

        }

        headNode.next = reverseHeadNode.next;

    }

    //逆序打印
    public static void reversePrint(Node headNode) {
        if (headNode.next == null) {
            return;
        }
        //创建一个栈,将二各个节点压入栈中
        Stack<Node> stack = new Stack<>();
        Node temp = headNode.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }


    //合并两个链表 并且顺序排列
    public static void mergeLinkedList(Node headNodeOne, Node headNodeTwo) {

        if(headNodeOne.next == null && headNodeTwo.next == null){
            return;
        }

        if (headNodeOne.next == null) {
            headNodeOne.next = headNodeTwo.next;
            Node temp = headNodeOne.next;
            Node buff = null;
            int length = 0;
            while (temp != null) {
                length++;
                temp = temp.next;
            }
            temp = headNodeOne;
            for (int i = 0; i < length - 1; i++) {
                for (int j = 0; j < length - i - 1; j++) {
                    if (temp.next.no > temp.next.next.no) {
                        buff = temp.next.next;
                        temp.next.next = temp.next.next.next;
                        buff.next = temp.next;
                        temp.next = buff;
                    }
                    temp = temp.next;
                }
                temp = headNodeOne;
            }
            return;
        }

        if (headNodeTwo.next == null) {
            Node temp = headNodeOne.next;
            Node buff = null;
            int length = 0;
            while (temp != null) {
                length++;
                temp = temp.next;
            }
            temp = headNodeOne;
            for (int i = 0; i < length - 1; i++) {
                for (int j = 0; j < length - i - 1; j++) {
                    if (temp.next.no > temp.next.next.no) {
                        buff = temp.next.next;
                        temp.next.next = temp.next.next.next;
                        buff.next = temp.next;
                        temp.next = buff;
                    }
                    temp = temp.next;
                }
                temp = headNodeOne;
            }
            return;
        }


        Node temp = headNodeOne.next;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = headNodeTwo.next;
        Node buff = null;
        temp = headNodeOne;
        int length = 0;
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        temp = headNodeOne;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (temp.next.no > temp.next.next.no) {
                    buff = temp.next.next;
                    temp.next.next = temp.next.next.next;
                    buff.next = temp.next;
                    temp.next = buff;
                }
                temp = temp.next;
            }
            temp = headNodeOne;
        }


    }


}

class Node {

    public int no;

    public String name;
    public String nickname;
    public Node next;   //指向下一个节点

    //构造器
    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便,重写toString

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + "'" +
                ", nickname='" + nickname + "'" +
                '}';
    }
}