

public class RBTStruct {
	public int color=0;//colorֵ��0��ʾ��ɫ��1��ʾ��ɫ
	public RBTStruct parent=null; //���ڵ�
	public RBTStruct left_child=null;//���ӽڵ�
	public RBTStruct right_child=null;//�Һ��ӽڵ�
	public int key=0;
	public int size=0;
	
	public RBTStruct()
	{}
	public RBTStruct(int key)
	{
		this.key=key;
	}
	public RBTStruct(int color, RBTStruct parent, RBTStruct left_child, RBTStruct right_child,int key,int size)
	{
		this.color=color;
		this.parent=parent;
		this.left_child=left_child;
		this.right_child=right_child;
		this.key=key;
		this.size = size;
	}

}
