package com.stack;

/**
 * 数组实现栈
 * @author win7
 *
 */
class ArrayStack{
	private int maxSize;//栈最大容量
	private int[] stack;//存储数据的数组
	
	private int top = -1;//最顶端元素下标
	
	public ArrayStack(int maxSize){
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	
	//判断栈是否已满
	public boolean isFull(){
		return top == maxSize-1;
	}
	
	//判断栈是否是空
	public boolean isEmpty(){
		return top == -1;
	}
	
	//添加元素
	public boolean push(int ele){
		if(isFull()){
			System.out.println("栈已满....");
			return false;
		}else{
			top++;
			stack[top] = ele;
			return true;
		}
	}
	
	//取出元素
	public int pop(){
		if(isEmpty()){
			throw new RuntimeException("栈已为空");
		}else{
			return stack[top--];
		}
	}
}
public class StackDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
