package com.my.array.lesson;

import java.util.Scanner;

/**
 * 数组实现环形队列
 * @author 12209
 *
 */
class CircleArray{
	private int maxSize;//队列最大容量
	private int front;//队列的头部
	private int rear;//队列的尾部
	private int count;//元素计数
	private int[] arr;//存储元素的数组
	
	public CircleArray(int maxSize){
		this.maxSize = maxSize;
		arr = new int[maxSize];
	}
	
	/**
	 * 1、为了有效的处理队列，我们需要两个指针，
	 * 一个用于指向队首元素所在的位置（front），
	 * 一个用于指示队尾待插入的位置(rear)。
       2、将数组想象成一个循环的圆形以此克服对空间的无效利用。
       3、边界条件判断
                    由于在循环数组中，入队时队尾追赶队首，出队时队首追赶队尾，
                    当队空或队满时都有front == rear, 故无法用此作为判定标准
	 */
	/**
	 * 
	 * 解决办法:
	 * 循环，需要rear对maxSize取余，用取余的值和front比较，
	 * 如果  front == rear/maxSize ，在队列为空和满了的时候，需要区分开来
	 * 1.留空位，当队列还有一个空位时就认为队列已满，避免上面的判断,
	 * 2.标记。引进一个新的变量，可以是bool型，当队尾正好在队头前面时，指示队列已满；
	 *   或者用一个整形变量 来计算队列中元素的个数
	 * @return
	 */
	public boolean isFull(){
		boolean full = false;
		//1.留空位的方式
		//full = (rear+1)%maxSize==front;
		//2.以元素数量判断
	     full =  count==maxSize;
        return full;
    }
	
	/**
	 * 判断是否为空
	 * @return
	 */
	public boolean isEmpty(){
		boolean empty = false;
	    //1.空出一个位置
		//empty = rear == front;
		//2.计算数量
		empty = count==0;
		return empty;
	}
    
    public void add(int n){
        //判断队列是否为满
        if(isFull()){
            System.out.println("队列满 ，不能加入");
            return;
        }
        arr[rear]=n;//直接将数据添加
        rear=(rear+1)%maxSize;
        count++;
    }
  
	  /**
	   * 返回数据
	   * @return
	   */
	  public int get(){
		  if(isEmpty()){
			 throw new RuntimeException("队列空，不能取数据");  
		  }
		//这里需要分析front是指向队列的第一个元素

        //1.先把front的值保留到临时变量 2.将front后移 3.将临时变量的值返回
        int value=arr[front];
        front=(front+1)%maxSize;
        count--;
        return value;
	  }  
	  
	  public int size(){
		  return count;
	  }
	  
	  public void show(){
		  if(isEmpty()){
			  System.out.println("队列为空，没有数据");
		  }else {
			  for (int i = 0; i < arr.length; i++) {
				System.out.println(i+"="+arr[i]);
			  }
		  }
	  }
	  
	    public int headQueue(){
          //判断
          if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
          }
          return arr[front];
	  }
}
public class LessonArray {

	public static void main(String[] args) {
		 System.out.println("测试数组模拟环形队列");
		 CircleArray aq = new CircleArray(5);
         char key=' ';//接收用户输入
         Scanner scanner =new Scanner(System.in);
         boolean loop=true;
         
         while(loop){
        	 key=scanner.next().charAt(0);//接受一个字符
        	 switch (key){

             case 's':

                 aq.show();

                 break;

             case 'a':

                 System.out.println("输出一个数");

                 int value=scanner.nextInt();

                 aq.add(value);

                 break;

             case 'g':

                 try{

                     int res=aq.get();

                     System.out.printf("取出的数据是%d",res);

                 }catch (Exception e){

                     System.out.println(e.getMessage());

                 }

                 break;

             case 'h'://查看队列头的数据

                 try{

                     int res =aq.headQueue();

                     System.out.printf("队列头的数据是%dn",res);

                 }catch (Exception e){
                     System.out.println(e.getMessage());
                 }

                 break;

             case 'e'://退出
                 scanner.close();
                 loop=false;
                 break;
             default:
                 break;

            }

         }
	}

}
