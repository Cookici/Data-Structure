package _06_Stack;

/**
 * @ProjectName: Data Structure
 * @Package: _06_Stack
 * @ClassName: Calculator
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/7 14:27
 */

public class Calculator {

    public static void main(String[] args) {
        String expression = "7*7*2-9*9";
        ExtendArrayStack extendArrayStackOperator = new ExtendArrayStack(10);
        ExtendArrayStack extendArrayStackNumber = new ExtendArrayStack(10);

        //定义相关变量
        int index = 0;
        int number1 = 0;
        int number2 = 0;
        int operator = 0;
        int result = 0;
        char temp = 0;  //将每次扫描得到的char保存到temp
        String keepNum = "";//用于拼接多位数

        while (true) {
            //依次得到expression的每一个字符
            temp = expression.substring(index, index + 1).charAt(0);
            //判断temp是什么
            if (extendArrayStackOperator.isOperator(temp)) {    //如果是运算符
                //判断当前的符号是否为空
                if (!extendArrayStackOperator.isEmpty()) {
                    //如果符号栈中有操作数，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数
                    //再从符号栈中pop出一个符号，进行运算，将得到的结果入数栈，然后将当前的操作符入符号栈
                    if (extendArrayStackOperator.priority(temp) <= extendArrayStackOperator.priority(extendArrayStackOperator.peek())) {
                        number1 = extendArrayStackNumber.pop();
                        number2 = extendArrayStackNumber.pop();
                        operator = extendArrayStackOperator.pop();
                        result = extendArrayStackNumber.calculate(number1, number2, operator);
                        extendArrayStackNumber.push(result);
                        extendArrayStackOperator.push(temp);
                    } else {
                        //优先级高则直接入栈
                        extendArrayStackOperator.push(temp);
                    }
                } else {
                    //符号栈为空直接入栈
                    extendArrayStackOperator.push(temp);
                }
            } else {
                //1.当多位数时，不能发现是一个数就立即入栈，因为他可能多位数
                //2.在处理数需要向expression的表达式的index后再看一位，如果是符号才入栈
                //3.定义一个字符串变量用于拼接
                keepNum += temp;

                if (index == expression.length() - 1) {
                    extendArrayStackNumber.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，是数字继续扫描拼接
                    if (extendArrayStackOperator.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符则直接入栈
                        extendArrayStackNumber.push(Integer.parseInt(keepNum));
                        //全局变量keepNum清空
                        keepNum = "";
                    }
                }
            }
            // 让index+1，并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {
            //如果符号栈为空，则计算最后的结果，数栈中只有一个数字
            if (extendArrayStackOperator.isEmpty()) {
                break;
            }
            number1 = extendArrayStackNumber.pop();
            number2 = extendArrayStackNumber.pop();
            operator = extendArrayStackOperator.pop();
            result = extendArrayStackNumber.calculate(number1, number2, operator);
            extendArrayStackNumber.push(result);
        }

        System.out.println("表达式结果为：" + extendArrayStackNumber.pop());


    }
}


//定义一个ArrayStack来表示栈
class ExtendArrayStack {

    private int maxSize;    //栈的大小
    private int[] stack;    //数组，数组模拟栈，数据就放在该数组
    private int top = -1;


    //构造器
    public ExtendArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }


    //查看栈顶的值
    public int peek() {
        return stack[top];
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

    //返回运算符的优先级,优先级自定
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }


    //计算方法
    public int calculate(int number1, int number2, int operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number2 - number1;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                result = number2 / number1;
                break;
        }

        return result;

    }

}
