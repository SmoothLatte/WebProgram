
import java.util.Random;

public class RBTTest {
	
	public static void main(String[] args)
	{
		long start,end,cost;
		//����Function1
		Function1();
		
		//����300000��Ԫ�صĲ���
		//start = System.nanoTime();
		//��Ӻ�������
		//Function2();
		//end = System.nanoTime();
		//cost = end - start;
		//�������ʱ��
		//System.out.println("300000��Ԫ�ع����ĺ�����в���key=15000��ֵ����ʱ��Ϊ��"+
				//Get_Cost(cost)+"seconds");
		
		//����Function3
		//Function3();
	}

	//����������ظ�������
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
	
	
	
	//����������������ѵ�ʱ��
	public static long Get_Cost(long cost)
	{
		long hours = cost/1000/60/60;
		long minutes=cost/1000/60 - hours*60;
		long seconds=cost/1000 - hours*60*60 - minutes*60;
		return seconds;
	}
	//���� 8��11��17��15��6��1��22��25��27�����������,���� �������Ϣ�����ʽ ������ú�����Լ��ڸ�
	//ɾ���������Key=15�Ľڵ㣬���� �������Ϣ�����ʽ �������������ú�����Լ��ڸ�
	public static void Function1()
	{
		RBT rbt=new RBT();
		RBTStruct RBT_List[] = {new RBTStruct(8),new RBTStruct(11),new RBTStruct(17),
		                                  new RBTStruct(15),new RBTStruct(6),new RBTStruct(1),
		                                  new RBTStruct(22),new RBTStruct(25),new RBTStruct(27)};
		int i=0;
		System.out.println("���������");
		for(;i<9;i++)
		{
			rbt.Insert(RBT_List[i]);
		}
		System.out.println("��������� ������ĺڸ߶�Ϊ��"+rbt.RBT_Get_BlackHeight(rbt.root));
		rbt.RBT_Print(rbt.root);
		
		/*System.out.println("�����ɾ���ڵ�15");
		rbt.RBT_Delete(RBT_List[3]);
		System.out.println("ɾ�������� ������ĺڸ߶�Ϊ��"+rbt.RBT_Get_BlackHeight(rbt.root));
		rbt.RBT_Print(rbt.root);*/
		
		
	}
	
	//300000��Ԫ�صĲ���
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
			System.out.println("�ҵ����");
		else
			System.out.println("û�ҵ����");
	}
	
	//���� 1,2,3,4,5,6,7,8�����������в���
	public static void Function3()
	{
		RBT rbt = new RBT();
		RBTStruct[] bstlist = {new RBTStruct(1),new RBTStruct(2),new RBTStruct(3),new RBTStruct(4),
				new RBTStruct(5),new RBTStruct(6),new RBTStruct(7),new RBTStruct(8)};
		
		int i = 0;
		//����
		while(i<8)
		{
			rbt.Insert(bstlist[i]);
			i++;
		}
		//��������ĺ����
		System.out.println("�����ĺ�������£�");
		rbt.RBT_Print(rbt.root);
		//����ÿ���ڵ��sizeֵ
		rbt.RBT_SetSize(rbt.root);
		//����key=6�Ľڵ����
		System.out.println("keyֵΪ6�Ľڵ����Ϊ��"+rbt.OS_Key_Rank(rbt.root, 6));
	}
	
}
