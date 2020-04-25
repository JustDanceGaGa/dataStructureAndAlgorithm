package com.mianshi.recursion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 
 * 
 * @author 12209
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 *
 */
public class KuoHao {
   
	public static  List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        add(list,"",0,0,n);
        return list;
    }
 
    public static void add(List<String> list,String a,int count1,int count2,int n){
        if(count1 > n || count2>n){
            return ;
        }
        if(count1 == n && count2 ==n){
            list.add(a);
        }
        if(count1 >= count2){
            String s = new String(a);
            add(list,a+"(",count1+1,count2,n);
            add(list,s+")",count1,count2+1,n);
        }
 
    }
    
	public static void main(String[] args) {
          List<String> res = generateParenthesis(3);
          for (String string : res) {
			System.out.println(string);
		  }
	}

}

