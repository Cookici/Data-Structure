package _02_Queue.优化;


import java.util.Scanner;

/**
 * @ProjectName: Data Structure
 * @Package: _02_Queue.优化
 * @ClassName: CircleArrayQueue
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/4 19:41
 */

public class CircleArrayQueue {


    public static void main(String[] args) {
        CircleArray arrayQueue = new CircleArray(4);        //实际长度为3 流出一个空间作为约定
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列列出数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println("取出数据是\n" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.println("队列头的数据是\n" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}


class CircleArray {
    private int maxSize;    //表示数组的最大容量

    private int front;  //队列头

    private int rear;   //队列尾

    private int[] arr;  //该数据用于存储数据,模拟队列

    public CircleArray(int arrMaxSize) {

        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;

    }


    //判断队列是否为满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }


    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }


    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移这里必须取模
        rear = (rear + 1) % maxSize;
    }


    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        //front是指向队列的第一个元素

        //1.将front保存到一个临时变量
        int value = arr[front];

        //2.将front后移
        front = (front + 1) % maxSize;

        //3.将临时变量返回

        return value;
    }


    //展示队列所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空,没有数据");
            return;
        }

        //从front开始遍历
        for (int i = front; i < front + (rear - front + maxSize) % maxSize; i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列的头数据,注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,没有数据");
        }
        return arr[front];
    }


}
