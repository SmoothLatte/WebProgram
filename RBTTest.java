
import java.util.Random;

public class RBTTest {
	
	public static void main(String[] args)
	{
		long start,end,cost;
		//调用Function1
		Function1();
		
		//调用300000个元素的操作
		//start = System.nanoTime();
		//添加函数调用
		//Function2();
		//end = System.nanoTime();
		//cost = end - start;
		//输出花费时间
		//System.out.println("300000个元素构建的红黑树中查找key=15000的值花费时间为："+
				//Get_Cost(cost)+"seconds");
		
		//调用Function3
		//Function3();
	}

	//生成随机不重复的数字
	public static void RandomArray(int[] array,int range)
	{
		int length = array.length;
		int i=0;
		int num=0;
		Random random=new Random();
		boolean[] exist=new boolean[range];
		for(int j=0;j<range;j++)
		{
			exist[j]=false;
		}
		while(i<length)
		{
			
			num=random.nextInt(range)+1;
			if(exist[num-1]==false)
			{
				exist[num-1]=true;
				array[i] = num;
				i++;
			}
		}
	}
	
	
	
	//计算程序运行所花费的时间
	public static long Get_Cost(long cost)
	{
		long hours = cost/1000/60/60;
		long minutes=cost/1000/60 - hours*60;
		long seconds=cost/1000 - hours*60*60 - minutes*60;
		return seconds;
	}
	//输入 8，11，17，15，6，1，22，25，27，建立红黑树,按照 红黑树信息输出方式 输出整棵红黑树以及黑高
	//删除红黑树中Key=15的节点，按照 红黑树信息输出方式 输出调整后的整棵红黑树以及黑高
	public static void Function1()
	{
		RBT rbt=new RBT();
		RBTStruct RBT_List[] = {new RBTStruct(8),new RBTStruct(11),new RBTStruct(17),
		                                  new RBTStruct(15),new RBTStruct(6),new RBTStruct(1),
		                                  new RBTStruct(22),new RBTStruct(25),new RBTStruct(27)};
		int i=0;
		System.out.println("红黑树插入");
		for(;i<9;i++)
		{
			rbt.Insert(RBT_List[i]);
		}
		System.out.println("插入操作后 红黑树的黑高度为："+rbt.RBT_Get_BlackHeight(rbt.root));
		rbt.RBT_Print(rbt.root);
		
		/*System.out.println("红黑树删除节点15");
		rbt.RBT_Delete(RBT_List[3]);
		System.out.println("删除操作后 红黑树的黑高度为："+rbt.RBT_Get_BlackHeight(rbt.root));
		rbt.RBT_Print(rbt.root);*/
		
		
	}
	
	//300000个元素的操作
	public static void Function2()
	{
		RBT rbt = new RBT();
		RBTStruct[] bstlist=new RBTStruct[300000];
		
		int[] array=new int[300000];
		RandomArray(array,300000);
		for(int i=0;i<300000;i++)
		{
			RBTStruct node=new RBTStruct();
			node.key = array[i];
			bstlist[i] = node;
			rbt.Insert(bstlist[i]);
		}
		if(rbt.RBT_Find(15000,rbt.root)!=rbt.Nil)
			System.out.println("找到结果");
		else
			System.out.println("没找到结果");
	}
	
	//输入 1,2,3,4,5,6,7,8建树，并进行操作
	public static void Function3()
	{
		RBT rbt = new RBT();
		RBTStruct[] bstlist = {new RBTStruct(1),new RBTStruct(2),new RBTStruct(3),new RBTStruct(4),
				new RBTStruct(5),new RBTStruct(6),new RBTStruct(7),new RBTStruct(8)};
		
		int i = 0;
		//建树
		while(i<8)
		{
			rbt.Insert(bstlist[i]);
			i++;
		}
		//输出构建的红黑树
		System.out.println("构建的红黑树如下：");
		rbt.RBT_Print(rbt.root);
		//计算每个节点的size值
		rbt.RBT_SetSize(rbt.root);
		//查找key=6的节点的秩
		System.out.println("key值为6的节点的秩为："+rbt.OS_Key_Rank(rbt.root, 6));
	}
	
}
