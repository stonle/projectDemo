package com.java.pattern.interpreter;


import java.util.HashMap;
import java.util.Map;

/**
 * Interpreter : 解释器设计模式
 *
 * 定义：给定一个语言之后，解释器模式可以定义出其文法的一种表示，并同时提供一个
 * 解释器，客户端可以使用这个解释器来解释这个语言中的句子。
 *
 * AbstractExpression：抽象表达式，声明一个所有具体表达式都要实现的接口，
 * 接口中主要是一个interpret()方法，称为解释操作，具体的解释任务由他的各个实现
 * 类来完成，而具体的解释器又分别由 终结符解释器 与 非终结符解释器 完成。
 *
 *
 * TerminalExpression：终结符表达式，实现与文法中的元素相关联的解释操作，
 * 通常一个解释器模式中只有一个终结符表达式，但有多个实例，对应不同的终结符。、
 * 终结符一半是文法中的运算单元，比如有一个简单的公式R=R1+R2，在里面R1和R2就是终结符，
 * 对应的解析R1和R2的解释器就是终结符表达式。
 *
 *
 * NonterminalExpression ：非终结符表达式，文法中的每条规则对应于一个非终
 * 结符表达式，非终结符表达式一般是文法中的运算符或者其他关键字，比如公式R=R1+R2中，
 * +就是非终结符，解析+的解释器就是一个非终结符表达式。非终结符表达式根据逻辑的复杂
 * 程度而增加，原则上每个文法规则都对应一个非终结符表达式。
 *
 * Context：上下文环境，存放文法中各个终结符所对应的具体值，比如R=R1+R2，我们
 * 给R1赋值100，给R2赋值200。这些信息需要存放到环境角色中，很多情况下我们使用Map来充
 * 当环境角色就足够了。
 * Created by Administrator on 2017/11/14.
 */
public class InterpreterPattern {
    public static void main(String[] args) {
        Context context = new Context();

        VariableExpression a = new VariableExpression("a");
        VariableExpression b = new VariableExpression("b");
        ConstantExpression c = new ConstantExpression(6);

        context.addExpression(a, 2);
        context.addExpression(b, 3);

        Expression expression = new PlusExpression(new PlusExpression(a,b),new MinusExpression(a,c));
        System.out.println(expression.toString() + " = " + expression.interpret(context));
    }
}

class Context{
    private Map<Expression, Integer> map = new HashMap<>();

    public void addExpression(Expression expression, int value) {
        map.put(expression, value);
    }

    public int lookup(Expression expression) {
        return map.get(expression);
    }
}
//抽象表达式
abstract class Expression{
    public abstract int interpret(Context context);
    @Override public abstract String toString();
}

//非终结符表达式
class PlusExpression extends Expression{
    private Expression leftExpression;
    private Expression rightExpression;

    public PlusExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override public int interpret(Context context) {
        return leftExpression.interpret(context) + rightExpression.interpret(context);
    }

    @Override public String toString() {
        return leftExpression.toString() + " + " + rightExpression.toString();
    }
}

class MinusExpression extends Expression{
    private Expression leftExpression;
    private Expression rightExpression;

    public MinusExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override public int interpret(Context context) {
        return leftExpression.interpret(context) - rightExpression.interpret(context);
    }

    @Override public String toString() {
        return leftExpression.toString() + " - " + rightExpression.toString();
    }
}

//终结符表达式
class ConstantExpression extends Expression{
    private int value;

    public ConstantExpression(int value) {
        this.value = value;
    }

    @Override public int interpret(Context context) {
        return value;
    }

    @Override public String toString() {
        return Integer.toString(value);
    }
}

class VariableExpression extends Expression{
    private String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override public int interpret(Context context) {
        return context.lookup(this);
    }

    @Override public String toString() {
        return name;
    }
}
