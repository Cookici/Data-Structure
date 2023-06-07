package _06_Stack;

import java.util.Scanner;

/**
 * @ProjectName: Data Structure
 * @Package: _06_Stack
 * @ClassName: Stack
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/6 15:03
 */

public class ArrayFinishStack {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key = " ";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示栈");
            System.out.println("e(exit)：退出程序");
            System.out.println("push(push)：添加数据到栈");
            System.out.println("pop(pop)：从队列取出数据");
            key = scanner.next();
            switch (key) {
                case "s":
                    arrayStack.list();
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("输入一个数字");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.println("取出数据是\n" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}


//定义一个ArrayStack来表示栈
class ArrayStack {

    private int maxSize;    //栈的大小
    private int[] stack;    //数组，数组模拟栈，数据就放在该数组
    private int top = -1;


    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }


    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        top++;
        stack[top] = value;

    }

    //出栈 将栈顶的数据返回
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            System.out.println("栈空");
            throw new RuntimeException("栈空没有数据");
        }

        int value = stack[top];
        top--;
        return value;
    }


    //显示栈的情况,遍历时,需要从栈顶开始显示
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空没有数据");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.println("第" + (i + 1) + "个数据为:" + stack[i]);
        }


    }


}
