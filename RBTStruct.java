

public class RBTStruct {
	public int color=0;//color值，0表示红色，1表示黑色
	public RBTStruct parent=null; //父节点
	public RBTStruct left_child=null;//左孩子节点
	public RBTStruct right_child=null;//右孩子节点
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
