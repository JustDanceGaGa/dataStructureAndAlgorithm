package com.stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/*
 * 中缀表达式转后缀表达式
 * 
 * 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
 * 
 * 1)初始化一个栈和一个List，一个用来存储运算符 s1，一个用来存储中间结果s2
 * 
 * 2)从左至右 遍历表达式
 * 
 * 3)遇到数值时将其压入s2中
 * 
 * 4)遇到操作符时，分一下情况处理
 *   
 *   1.如果s1为空，或者栈顶符号是"("，那么直接将操作符压入s1
 *   2.否则，如果当前操作符比s1栈顶的操作符优先级高，那么直接将当前操作符压入s1
 *   3.否则，先pop出s1中操作符并压入s2中，然后转到4.1 与 s1总新的栈顶运算符比较
 *   
 * 5)遇到括号时(括号不参与运算符比较):
 *   1.如果是左括号"(",那么直接将括号压入s1
 *   2.如果是右括号")",则依次弹出s1中的运算符，并压入s2，直到遇到")",pop掉")",消除这一对括号
 * 6)表达式遍历完成后，将s1中剩余的操作符依次弹出压入s2
 * 7)遍历s2 可得到 结果
 */
public class ReversePolandExp {
	/**
	 * 表达式计算
	 * 
	 * @param ele1
	 * @param ele2
	 * @param oper
	 * @return
	 */
	public static Integer calculate(Integer ele1, Integer ele2, String oper) {
		Integer res = 0;
		if (oper.equals("+")) {
			res = ele1 + ele2;
		}
		if (oper.equals("-")) {
			res = ele1 - ele2;
		}
		if (oper.equals("*")) {
			res = ele1 * ele2;
		}
		if (oper.equals("/")) {
			res = ele1 / ele2;
		}
		return res;
	}
	/**
	 * 比较两个运算符的优先级
	 * 
	 * @param ele1
	 * @param ele2
	 * @return
	 */
	public static boolean operPriorityCompare(String ele1, String ele2) {
		int flag1 = -1;
		int flag2 = -1;
		if (ele1.equals("+") || ele1.equals("-")) {
			flag1 = 0;
		} else {
			flag1 = 1;
		}
		if (ele2.equals("+") || ele2.equals("-")) {
			flag2 = 0;
		} else {
			flag2 = 1;
		}
		return flag1 <= flag2;
	}

	
	/**
	 * 表达式转换
	 * @param expList
	 * @return
	 */
	public static List<String> transExp(List<String> expList){
		
		List<String> res = new ArrayList<String>();
		Stack<String> oper = new Stack<String>();
		//1+((2+3)*4)-5
		for(int i=0;i<expList.size();i++){
			//1.字符数字正则判断
			String item = expList.get(i);
			if(item.matches("\\d+")){
				res.add(item);
			}else{
				//2.括号的处理
				if(item.equals("(")){
					oper.push(item);
				}else if(item.equals(")")){
					
					while(oper.size()!=0&&!oper.peek().equals("(")){
						res.add(oper.pop());
					}
					oper.pop();//弹出 "("
				}else{
					//3.运算符处理
					//运算符优先级比较
					//当运算符栈 oper为空 或者 oper 的栈顶元素是 "("
					if(oper.isEmpty()||oper.peek().equals("(")){
						oper.push(item);
					}else{
						//如果当前运算符的优先级小于或者等于oper栈顶的运算符的优先级,
						// pop()出oper 栈顶加入到res，
						while(!oper.isEmpty()&&
								operPriorityCompare(item,oper.peek())
							  ){
							res.add(oper.pop());
						}
						//如果当前运算符的优先级大于oper栈顶的运算符，直接将当前 item直接压入s1
						oper.push(item);
					}
				}
			}
		}
		
		//表达式遍历完成后，将s1中操作符依次取出，加入到res List中
		while(!oper.isEmpty()){
			res.add(oper.pop());
		}
		return res;
		
	}
	
	/**
	 * 使用后缀表达式计算
	 * @return
	 */
	public static Integer calculateBySuffixExp(List<String> suffixExp){
		Stack<Integer> res = new Stack<Integer>();
		
		for(String item : suffixExp) {
			if(item.matches("\\d+")){
				res.add(Integer.valueOf(item));
			}else{
				Integer data2 = res.pop();
				Integer data1 = res.pop();
				res.push(calculate(data1, data2, item));
			}
		}
		return res.pop();
	}
	public static void main(String[] args) {
		//1+((2+3)*4)-5
		String zhongZhuiExp = "1-4*3+6/2";//1-4*3+6/2
		
		char[] arrChar = zhongZhuiExp.toCharArray();
		
		List<String> arrList = new ArrayList<String>();
	
		for(int i = 0; i < arrChar.length; i++) {
			arrList.add(arrChar[i]+"");
		}

		
		System.out.println("后缀表达式:"+transExp(arrList));
		System.out.println("后计算结果:"+calculateBySuffixExp(transExp(arrList)));
	}

}
