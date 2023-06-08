package _06_Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @ProjectName: Data Structure
 * @Package: _06_Stack
 * @ClassName: PolandNotation
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/7 16:21
 */

public class PolandNotation {

    public static void main(String[] args) {
        //先定义逆波兰表达式

        // ((3+4)*5)-6)   =>    3 4 + 5 * 6 -
        // ((((4*5)-8)+60)+(8/2))   =>    4 5 * 8 - 60 + 8 2 / +

        String suffixExpressionOne = "3 4 + 5 * 6 -";

        String suffixExpressionTwo = "4 5 * 8 - 60 + 8 2 / +";

        String expression = "1+((2+3))*4-5";

        List<String> infixExpressionList = toInfixExpressionList(expression);

        List<String> strings = parseSuffixExpressionList(infixExpressionList);


        //思路
        //1.先将"3 4 + 5 * 6 -" =>放到ArrayList中
        //2.将ArrayList传递给一个方法，配合栈，完成计算

        List<String> rpnListOne = getListString(suffixExpressionOne);
        List<String> rpnListTwo = getListString(suffixExpressionTwo);

        int resultOne = calculator(rpnListOne);
        int resultTwo = calculator(rpnListTwo);

        System.out.println("计算结果为: " + resultOne);
        System.out.println("计算结果为: " + resultTwo);
        System.out.println("中缀转后缀表达式的结果为：" + calculator(strings));

    }

    //将一个逆波兰表达式，依次将数据和运算符放入ArrayList中
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }


    //完成对逆波兰表达式的计算
    /*
     * 1.从左到右扫描,将3和4压入堆栈
     * 2.遇到+运算符,因此弹出4和3(4为栈顶元素,3为次顶元素) 计算出3+4的值 再压入栈
     * 3.将5压入栈
     * 4.接下来是*运算符,因此弹出5和7，计算出5*7的值 将得出的值压入堆栈
     * 5.将6压入栈
     * 6.最后是-运算符，计算出35-6的值，得出最后结果
     */
    public static int calculator(List<String> ls) {

        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //正则表达式取出数
            if (item.matches("\\d+")) {   //匹配多位数
                //入栈
                stack.push(item);
            } else {
                //弹出两个数并运算,再入栈
                int number1 = Integer.parseInt(stack.pop());
                int number2 = Integer.parseInt(stack.pop());
                int result;
                switch (item) {
                    case "+":
                        result = number1 + number2;
                        break;
                    case "-":
                        result = number2 - number1;
                        break;
                    case "*":
                        result = number1 * number2;
                        break;
                    case "/":
                        result = number2 / number1;
                        break;
                    default:
                        throw new RuntimeException("运算符错误");
                }
                stack.push(String.valueOf(result));
            }
        }
        //最后留在Stack中的就是最后结果
        return Integer.parseInt(stack.pop());
    }


    //将中缀表达式转化成对应的List
    public static List<String> toInfixExpressionList(String s) {

        //定义一个List,存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        //用于遍历中缀表达式字符串s
        int index = 0;
        //做对多位数的拼接操作
        StringBuilder str;
        //每遍历到一个字符，就放入到c
        char c;
        do {
            //如果c是一个非数字，加入到ls
            if ((c = s.charAt(index)) < 48 || (c = s.charAt(index)) > 57) {
                ls.add(String.valueOf(c));
                index++;
            } else {    //如果是一个数，需要考虑多位数
                str = new StringBuilder();
                while (index < s.length() && s.charAt(index) >= 48 && (c = s.charAt(index)) <= 57) {
                    str.append(c);
                    index++;
                }
                ls.add(str.toString());
            }
        } while (index < s.length());
        return ls;
    }

    //将中缀表达式转化为后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls) {

        //定义两个栈
        Stack<String> stack = new Stack<>();   //符号栈
        //Stack<String> s2 = new Stack<>();   //中间结果栈     在整个转化过程中没有pop操作而且需要逆序输出 使用List<String>来代替
        List<String> list = new ArrayList<>();

        //遍历ls
        for (String item : ls) {
            //如果是一个数就加入list
            if (item.matches("\\d+")) {
                list.add(item);
            } else if ("(".equals(item)) {
                stack.push(item);
            } else if (")".equals(item)) {
                //如果是右括号")"，则一次弹出stack栈顶的运算符,并压入list,直到遇到左括号位为止
                while (!"(".equals(stack.peek())) {
                    list.add(stack.pop());
                }
                stack.pop(); //将(弹出stack栈，消除小括号
            } else {
                //当item的运算符的优先级小于等于栈顶运算符
                //将stack栈顶的运算符弹出加入到list，再次与stack中新的栈顶元素运算符比较
                while (stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(item)) {
                    list.add(stack.pop());
                }
                //将item压入栈中
                stack.push(item);
            }
        }
        //将stack中剩余的运算符依次弹出加入到list
        while (stack.size() != 0) {
            list.add(stack.pop());
        }

        return list;    //按顺序输入就是逆波兰表达式

    }


}

// 编写一个类Operation返回一个运算符的优先级
class Operation {

    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {

            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }

        return result;

    }


}
