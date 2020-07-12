package com.my.linklist;
/**
 * 循环单链表 约瑟夫问题
 * @author win7
 * 
 * Josephu  问题为：
	设编号为1，2，… n的n个人围坐一圈，
	约定编号为k（1<=k<=n）的人从1开始报数，
	数到m 的那个人出列，它的下一位又从1开始报数，
	数到m的那个人又出列，依次类推，
	直到所有人出列为止，
	由此产生一个出队编号的序列
	
	提示：
	用一个不带头结点的循环链表来处理Josephu 问题：
	先构成一个有n个结点的单循环链表（单向环形链表），
	然后由k结点起从1开始计数，计到m时，对应结点从链表中删除，
	然后再从被删除结点的下一个结点又从1开始计数，
	直到最后一个结点从链表中删除算法结束
 *
 */

/**
 * 
 *节点
 */
class NodeJ{
	//节点编号
	public int num;
	public NodeJ next;
	
	public NodeJ(int num) {
		super();
		this.num = num;
	}

	@Override
	public String toString() {
		return "NodeJ [num=" + num + "]";
	}
	
	
}

/**
 * 循环单链表
 */
class CircleSingleLinkList{
	public NodeJ first;//头节点
	
	
	/**
	 * 按照约瑟夫问题需求，添加指定数量节点。构成环状链表
	 */
	public void  addNodeJs(int numNodes){
		if(numNodes < 1){
			System.out.println("节点数需要大于1");
			return;
		}
		
		//循环添加
		NodeJ temp = null;
		NodeJ cur = null;//最新添加的节点
		for(int i=1;i<=numNodes;i++){
			temp = new NodeJ(i);
			//形成黄庄链表
			if(first == null){
				first = temp;
				first.next = first;
				cur = first;
			} else{
				cur.next = temp;
				temp.next = first;
				cur = temp;
			}
		}
	}

  /**
   * 遍历打印链表
   */
  public void showList(){
	  if(first == null){
		 System.out.println("链表为空。。。。。。");
		 return;
	  }
	  
	  NodeJ cur = first;
	  while(true){
		  System.out.println(cur);
		  if(cur.next == first){
			  break;
		  }
		  cur = cur.next;
	  }
  }
  
  /**
   * 链表不能随机访问，只能逐个移动
   * 问题中从第k个数开始，移动m次，然后移除当前元素，循环以上操作
   * 这些操作中暗含着前后节点需要关联，而且节点的链接关系应该是环状的，
   * 所以考虑使用环形链表解决
   * 
   * 约瑟夫问题分三步
   * 1.构建一个节点数为nums的环形链表，取一个辅助指针temp指向当前环形节点的最后一个节点
   *   即：temp.next = first
   * 2.将first 和 temp 移动 k-1 次，此时first节点指向第k个元素
   * 
   * 3.将 first 和 temp 移动 m-1 次，然后将first当前指向的元素删除，
   *   first 指向当前元素的下一个 ，temp.next 指向新的first
   *   
   *   while 循环 第3步 直到最后一个节点删除
   * 
   * @param nums 
   * @param k
   * @param m
   */
  public void JosephuOrder(int nums,int k,int m){
	  //校验
	  if(nums <1 || k >nums || m<1 ||k <1 ||first==null){
		  System.out.println("参数输入有误。。。");
		  return;
	  }
	  
	  //辅助指针temp指向最后一个节点
	  NodeJ temp = first;
	  while(true){
		  if(temp.next == first){
			  break;
		  }
		  temp = temp.next;
	  }
	  
	  //先将头结点first和temp移动k-1次。。
	  for(int i =0;i<k-1;i++){
		  first = first.next;
		  temp = temp.next;
	  }
	  
	  //循环移动操作，直到只剩一个元素
	  while(true){
		  if(temp == first){
			  break;
		  }
		  
		  //从  第 k 个节点开始，向前移动 m-1次，到达 m 节点，然后删除 m 节点
		  for(int i =0;i<m-1;i++){
			  first = first.next;
			  temp = temp.next;
		  }
		  System.out.println("移除的节点是:"+first.num);
		  first = first.next;
		  temp.next = first;
	  }
	  System.out.println("最后剩下的节点是:"+first.num);
	  
  }
	
}
public class CircleSingleListDemo {

	public static void main(String[] args) {
		CircleSingleLinkList csl = new CircleSingleLinkList();
		csl.addNodeJs(5);
		csl.showList();
		
		csl.JosephuOrder(5, 1, 2);
	}

}
