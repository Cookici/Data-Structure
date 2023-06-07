package _05_JosephRing;

/**
 * @ProjectName: Data Structure
 * @Package: _05_JosephRing
 * @ClassName: JosephRing
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/5 22:33
 */

public class JosephRing {

    public static void main(String[] args) {
        System.out.println("构建环形链表：");
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.show();
        System.out.println("小孩出圈：");
        circleSingleLinkedList.countBoy(1, 2, 5);
    }

}

//创建环形的单向链表
class CircleSingleLinkedList {

    //创建第一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点,构建成一个环形的链表
    public void addBoy(int num) {

        if (num < 1) {
            System.out.println("num的值不正确");
            return;
        }

        Boy curBoy = null;

        //使用for循环创建环形链表
        for (int i = 0; i < num; i++) {
            Boy boy = new Boy(i + 1);
            if (i == 0) {
                first = boy;
                first.setNext(first);   //构成环
                curBoy = first; //curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void show() {

        if (first == null) {
            System.out.println("没有小孩");
            return;
        }

        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号为：" + curBoy.getId());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }

    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param num      表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int num) {
        if (first == null || startNo < 1 || startNo > num) {
            System.out.println("参数有误");
            return;
        }

        //创建辅助指针，帮助小孩出圈
        Boy helper = first;

        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        //报数前，先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //这里是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                break;
            }
            //让first和helper指针同时移动countNum-1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("小孩" + first.getId() + "出圈");
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.println("最后留在圈中的小孩为：" + helper.getId());
    }


}


class Boy {

    private int id;//编号
    private Boy next;//指向下一个节点

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
