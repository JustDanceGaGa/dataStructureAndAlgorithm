 package com.my.array;

import java.util.HashMap;

/**
 * 数组相关的算法
 * @author 12209
 *
 */
public class ArrayOne {
   /**
    * 1.选定两数相加
    * 
    * 给定一个整数数组 nums 和一个目标值 target，
    * 请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
                你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素

	给定 nums = [2, 7, 11, 15], target = 9
	因为 nums[0] + nums[1] = 2 + 7 = 9
	所以返回 [0, 1]
    * @param args
    */
	public static int[] twoSum(int[] array,int target){
		int[] res = new int[2];
		//1.双重for循坏，暴力破解
/*		
		for(int i=0;i<array.length-1;i++){
			for(int j=i+1;j<array.length+1;j++){
				if(array[i]+array[j] == target	){
					res[0] = array[i];
					res[1] = array[j];
					System.out.println(array[i]+"=="+array[j]);
					return res;
				}
			}
		}*/
		
		//2.哈希法
		/**
		 * 使用一个HashMap存储 数组元素的值和下标
		 * 
		 * key:数组值
		 * value:数组下标
		 */
		HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
		for (int i = 0; i < array.length; i++) {
			m.put(array[i],i);
		}
		for(int j=0;j<array.length;j++){
			//获取当前值的匹配加和的另外一个数值，判断是否在集合中
			//边界:在集合中时，不能是当前值
			int other = target-array[j];
			if(m.containsKey(other)&&m.get(other)!=j){
				res[0] = array[j];
				res[1] = other;
				System.out.println(j+"=="+m.get(other));
			}
		}
		return null;
	}
	public static void main(String[] args) {
		//1.两数之和
		int[] num ={2, 7, 2, 15};
		int target = 4;
		twoSum(num,target);

	}

}
