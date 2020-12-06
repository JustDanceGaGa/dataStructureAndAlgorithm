package com.stack;

import java.util.Stack;

/**
 * 计算器表达式 传入表达式：3+2*6-5 返回结果：10
 * 
 * @author win7
 *
 */
public class CalculationExpression {

	/**
	 * 判断字符是否是运算符，默认只有 + - * /
	 * 
	 * @param ele
	 * @return
	 */
	public static boolean isOperOrNum(char ele) {
		if (ele == '+' || ele == '-' || ele == '*' || ele == '/') {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 比较两个运算符的优先级
	 * 
	 * @param ele1
	 * @param ele2
	 * @return
	 */
	public static boolean operPriorityCompare(char ele1, char ele2) {
		int flag1 = -1;
		int flag2 = -1;
		if (ele1 == '+' || ele1 == '-') {
			flag1 = 0;
		} else {
			flag1 = 1;
		}
		if (ele2 == '+' || ele2 == '-') {
			flag2 = 0;
		} else {
			flag2 = 1;
		}
		return flag1 > flag2;
	}

	/**
	 * 表达式计算
	 * 
	 * @param ele1
	 * @param ele2
	 * @param oper
	 * @return
	 */
	public static Integer calculate(Integer ele1, Integer ele2, char oper) {
		Integer res = 0;
		if (oper == '+') {
			res = ele1 + ele2;
		}
		if (oper == '-') {
			res = ele1 - ele2;
		}
		if (oper == '*') {
			res = ele1 * ele2;
		}
		if (oper == '/') {
			res = ele1 / ele2;
		}
		return res;
	}

	public static int calculate(String exp) {
		// 数值栈
		Stack<Integer> numStack = new Stack<Integer>();
		// 操作符栈
		Stack<Character> operStack = new Stack<Character>();

		char[] expCharArr = exp.toCharArray();

		for (int i = 0; i < expCharArr.length; i++) {
			// 如果是数值，直接入栈 numStack
			if (!isOperOrNum(expCharArr[i])) {
				numStack.push(Integer.valueOf(expCharArr[i]+""));
			} else {
				// 如果是操作符，则分两种情况处理
				// pop出操作符栈中的最近一个操作符，比较两者的优先级，
				// 1.如果当前操作符的优先级小于或者等于栈中取出的操作符的优先级，那么需要取出数值
				// 栈中的两个元素，使用栈中的操作符计算表达式，结果push进数值栈，当前操作符push
				// 进操作符栈；
				if (operStack.empty()) {
					operStack.push(expCharArr[i]);
				} else {
					// 栈中的操作符,pop出来的元素会在栈中删掉，如果不想被删除，需要将元素重新入栈
					char op1 = operStack.peek();
					if (operPriorityCompare(op1, expCharArr[i])) {
						
						Integer num2 = numStack.pop();
						Integer num1 = numStack.pop();
						Integer res = calculate(num1, num2, op1);
						
						numStack.push(res);
						
						operStack.pop();//移除优先级较高的op1;
						operStack.push(expCharArr[i]);

					} else {// 2.如果当前操作符优先级大于栈中pop出的操作符优先级，那么直接将当前操作符入栈
						operStack.push(expCharArr[i]);
					}

				}
			}
		}

		
		//依次取出操作符栈中的元素，计算；为了方便遍历，先取出数值栈中的一个元素赋值给result
		System.out.println("operStack="+operStack);
		System.out.println("numStack="+numStack);
		

		while(!operStack.isEmpty()){
			Integer num2 = numStack.pop();
			Integer num1 = numStack.pop();
			Character op1 = operStack.pop();//移除优先级较高的op1;
			Integer res = calculate(num1, num2, op1);
			numStack.push(res);
		}
		Integer result = numStack.pop();
		return result;
	}

	public static void main(String[] args) {//"1-4*3+6/2"
         System.out.println(calculate("1-4*3+6/2"));
	}

}
