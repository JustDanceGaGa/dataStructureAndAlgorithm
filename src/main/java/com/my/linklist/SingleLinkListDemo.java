package com.my.linklist;
/**
 * 单链表
 * @author 12209
 *
 */
/**
 * 链表节点数据
 */
class HeroNode{
	public int num;
	public String name;
	public String nickname;
	public HeroNode next ;
	
	public HeroNode(int num, String name, String nickname) {
		this.num = num;
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "name:"+name+",nickname:"+nickname+",num:"+num;
	}

	
}

//
class SingleLinkList{
	//初始化一个头结点，头结点不存储数据，用来遍历链表
	private HeroNode head = new HeroNode(0,"","");
	
	/**
	 * 添加一个节点到链表的尾部
	 * @param node
	 */
	public void add(HeroNode node){
		//使用辅助引用移动链表，头结点不移动
		HeroNode temp = head; 
		//遍历链表，找到最后一个元素，将新元素添加到后面
		while(true){
			if(temp.next == null){
				temp.next = node;
				break;
			}
			temp = temp.next;
		}
	}
	
	/**
	 * 按顺序添加元素到链表
	 * 
	 * 先找到要添加的元素的位置，位置是遍历的当前节点的下一个节点
	 */
	public void addByOrder(HeroNode node){
		//使用辅助引用移动链表，头结点不移动
		HeroNode temp = head; 
		//遍历链表，找到最后一个元素，将新元素添加到后面
		while(true){
			if(temp.next == null){
				temp.next = node;
				break;
			}else{
				//...A，B... -->A，node，B  三个节点，
				//找到A节点，用A.next(B节点) 判断是否比node大
				if(temp.next.num==node.num){
					System.out.println("当前英雄已经存在");
					break;
				}
				if(temp.next.num>node.num){
					node.next = temp.next;//将B设置为node的下一个节点
					temp.next = node;//将A的下一个节点设置成node
					break;
				}
				temp = temp.next;
			}
			
		}
	}
	
	/**
	 * 删除一个指定的节点
	 * @param node
	 */
	public void delete(HeroNode node){
		if(head.next == null){
			System.out.println("链表为空");
		}
		
		HeroNode temp = head;
		
		while(true){
		  //没找到节点，退出循环
		  if(temp == null){
			System.out.println("没找到要删除的元素");	  
			break;
		  }
		  //定位到要删除的节点的上一个节点
		  if(temp.next.num == node.num){
			  //需要删除的节点
			  HeroNode delete =  temp.next; 
			  
			  //将要删除的节点 delete 上一个节点的 next 设置为要 delete 节点的下一个节点
			  temp.next  = temp.next.next;
			  
			  //将 delete 的 next设置为空
			  delete.next = null;
			  
			  break;
		  }
		  temp = temp.next;
		}
		
		
	}
	/**
	 * 遍历显示列表
	 */
	public void list(){
		//1.链表为空
		//使用辅助引用移动链表，头结点不移动
		HeroNode temp = head.next; 
		if(temp == null){
			System.out.println("链表为空");
			return;
		}
		while(true){
			//最后一个元素，跳出循环
			if(temp == null){
				break;
			}
			System.out.println(temp);
			//移动链表
			temp = temp.next;
		}
	}
	
	//链表反转
	/**
	 * 改变当前链表元素的指向关系，然后更新当前链表头结点head的指向
	 * @param list
	 */
	public void reverseList(){
    //非空判断
    if(head.next==null||head.next.next==null){
    	System.out.println("链表为空");
    	
    }
		
	/**	
     *  1 --> 2 --> 3
		1 <-- 2 <-- 3
		
	      考虑尾结点为空和初始 preNode为空
	  (假设空的 preNode指向第一个)null --> 1 --> 2 --> 3 --> null
	                       null <-- 1 <-- 2 <-- 3 <-- preNode
		
		如上图，单链表反转的核心是改变相邻元素的指向关系
		取三个变量
		把：
		preNode.next = curNode 
		变成
		curNode.next = preNode
		这是一次指针指向改变
		而nextNode 是作为临时变量存储 curNode 下一个节点curNode.next的，为了方便向前移动链表
	*/
		//前一个节点指针
		HeroNode preNode = null;
		//当前节点指针
		HeroNode curNode = head.next;
		//下一个节点指针
		HeroNode nextNode = null;//（作为移动当前指针的临时变量）
		
		while(curNode !=null){
			//1.首先要取出当前节点curNode的下一个节点保存在 nextNode，
			//因为后面要改变curNode引用的指向
			nextNode = curNode.next;
			
			//2.然后将当前节点curNode的下一个节点改成前一个节点 preNode,即反转相邻指针指向
			//第一个节点认为是一个空节点指向curNode
			curNode.next = preNode;
			//3.分别向前移动 preNode和curNode
			preNode = curNode;
			curNode = nextNode;
		}
		//将头结点重新赋值，赋值为最后一个节点
		head.next = preNode;
	}
	
	/**
	 * 合并连个有序单链表单链表
	 * @param h1
	 * @param h2
	 * @return
	 * 
	 *  输入：1->2->4, 1->3->4
		输出：1->1->2->3->4->4
		
		1.因为合并的链表是有序的，所以依次找出两个链表中共同最小的元素
	 */
	public static HeroNode mergeSingleList(HeroNode h1,HeroNode h2){
		/**
		 * 1.使用递归的方法合并
		 * 
		 */
		
		return null;
	}
	
}
public class SingleLinkListDemo {

	public static void main(String[] args) {
          HeroNode h1 = new HeroNode(1,"宋江","及时雨");
          HeroNode h4 = new HeroNode(4,"林冲","豹子头");
          HeroNode h2 = new HeroNode(2,"卢俊义","玉麒麟");
          HeroNode h3 = new HeroNode(3,"吴用","智多星");
          HeroNode h5 = new HeroNode(5,"李逵","黑旋风");
          HeroNode h6 = new HeroNode(6,"花荣","小李广");
          
          SingleLinkList sl = new SingleLinkList();
          /*sl.add(h1);
          sl.add(h2);
          sl.add(h3);*/
          
          sl.addByOrder(h1);
          sl.addByOrder(h4);
          sl.addByOrder(h5);
          sl.addByOrder(h6);
          sl.addByOrder(h3);
          sl.addByOrder(h2);
          sl.list();
          System.out.println("**反转后******************");
          sl.reverseList();
          sl.list();
          
          /*System.out.println("###############");
          HeroNode hd3 = new HeroNode(3,"吴用","智多星");
          sl.delete(hd3);
          sl.list();*/
          
          
	}

}
