


public class RBT {
	public RBTStruct Nil=new RBTStruct(1,null,null,null,-1,0);
	//定义根节点
	public RBTStruct root=Nil;
	//构造函数，也相当于构建了一个空红黑树
	public RBT()
	{}
	//查找 指定节点
	public RBTStruct RBT_Find(int key,RBTStruct rbtroot)
	{
		RBTStruct result = rbtroot;
		while(result != Nil && result.key != key)
		{
			if(result.key < key)
				result = result.right_child;
			else
				result = result.left_child;
		}
		return result;
	}
	//确定各节点的size属性
	public int RBT_SetSize(RBTStruct node)
	{
		if(node == Nil)
			return 0;
		else
		{
			node.size=RBT_SetSize(node.left_child)+RBT_SetSize(node.right_child)+1;
			return node.size;
		}
	}
	//OS-Key-Rank
	public int OS_Key_Rank(RBTStruct Troot, int k)
	{
		if(Troot.key == k)
			return Troot.left_child.size+1;
		else
		{
			if(Troot.key > k)
				return OS_Key_Rank(Troot.left_child,k);
			else
				return OS_Key_Rank(Troot.right_child,k);
		}
	}
	//计算给定节点的黑高度
	public int RBT_Get_BlackHeight(RBTStruct node)
	{
		int bh=0;
		RBTStruct temp = node;
		while(temp != Nil)
		{
			if(temp.color == 1)
				bh++;
			temp = temp.left_child;
		}
		return bh;
	}
	//插入节点
 	public RBTStruct Insert(RBTStruct node)
	{
		RBTStruct temp_x=root;
		RBTStruct temp_y=Nil;
		//找到节点应插入的根节点
		while(temp_x != Nil)
		{
			temp_y=temp_x;
			if(node.key<temp_y.key)
				temp_x=temp_x.left_child;
			else
				temp_x=temp_x.right_child;
		}
		//将插入节点的父节点赋值为temp_y
		node.parent=temp_y;
		//判断key值大小以决定node应该为temp_y的左子树还是右子树
		if(temp_y == Nil)
		{
			root = node;
		}
		else
		{
		    if(node.key<temp_y.key)
		    	temp_y.left_child=node;
		    else
			    temp_y.right_child=node;
		}
		//设定node的各个属性值
		node.left_child=Nil;
		node.right_child=Nil;
		node.color=0;
		//若破坏了红黑树的性质，则进行修复
		RBT_Insert_FixUp(node);
		return root;
	}
	//删除节点
	public RBTStruct RBT_Delete(RBTStruct node)
	{
		RBTStruct temp_x = null;
		RBTStruct temp_y = null;
		if(node.left_child == Nil|| node.right_child == Nil)
		{
			temp_y=node;
		}
		else
			temp_y = Get_RBT_Successor(node);
		if(temp_y.left_child != Nil)
			temp_x = temp_y.left_child;
		else
			temp_x = temp_y.right_child;
		temp_x.parent = temp_y.parent;
		
		if(temp_y.parent == Nil)
			root = temp_x;
		else
		{
			if(temp_y == temp_y.parent.left_child)
				temp_y.parent.left_child = temp_x;
			else
				temp_y.parent.right_child = temp_x;
		}
		
		if(temp_y != node)
		{
			node.key = temp_y.key;
		}
		
		if(temp_y.color ==1)
			RBT_Delete_FixUp(temp_x);
		return temp_y;
	}
	//获取根的子树中最小元素的节点
	public RBTStruct Get_RBT_Min(RBTStruct node)
	{
		RBTStruct min=node;
		while(min.left_child != Nil)
			min = min.left_child;
		return min;
	}
	//返回红黑树中的指定节点的后继
	public RBTStruct Get_RBT_Successor(RBTStruct node)
	{
		if(node.right_child != Nil)
			return Get_RBT_Min(node.right_child);
		RBTStruct temp_y=node.parent;
		while(temp_y != Nil && node == temp_y.right_child)
		{
			node = temp_y;
			temp_y = temp_y.parent;
		}
		return temp_y;
	}
	//左旋
	public void Left_Rotate(RBTStruct node)
	{
		RBTStruct temp_right=node.right_child;
		//将node的右孩子指定为temp_right的左孩子
		node.right_child=temp_right.left_child;
		//判断temp_right的左孩子是否为空，不为空则可以将其父节点赋值为node
		if(temp_right.left_child!=Nil)
			temp_right.left_child.parent=node;
		//将temp_right的父节点改为node的父节点
		temp_right.parent=node.parent;
		//判断node的父节点是否为空，为空则表示node为根节点
		if(node.parent == Nil)
			root=temp_right;
		else
		{
			if(node == node.parent.left_child)
				node.parent.left_child=temp_right;
			else
				node.parent.right_child=temp_right;
		}
		//将node作为temp_right的左孩子
		temp_right.left_child=node;
		node.parent=temp_right;
	}
	//右旋
	public void Right_Rotate(RBTStruct node)
	{
		RBTStruct temp_left=node.left_child;
		node.left_child=temp_left.right_child;
		if(temp_left.right_child != Nil)
			temp_left.right_child.parent=node;
		temp_left.parent=node.parent;
		if(node.parent == Nil)
			root=temp_left;
		else
		{
			if(node == node.parent.left_child)
				node.parent.left_child = temp_left;
			else
				node.parent.right_child = temp_left;
		}
		temp_left.right_child = node;
		node.parent = temp_left;
	}
	//插入修复
	public void RBT_Insert_FixUp(RBTStruct node)
	{
		RBTStruct temp_y=null;
		while(node.parent.color == 0)
		{
			//处理左子树
			if(node.parent == node.parent.parent.left_child)
			{
				temp_y=node.parent.parent.right_child;
				if(temp_y.color == 0)
				{
					node.parent.color =1;
					temp_y.color = 1;
					node.parent.parent.color = 0;
					node = node.parent.parent;
				}
				else
				{
					if(node == node.parent.right_child)
					{
						node = node.parent;
						Left_Rotate(node);
					}
					node.parent.color = 1;
					node.parent.parent.color = 0;
					Right_Rotate(node.parent.parent);
				}
			}
			//处理右子树
			else
			{
				temp_y = node.parent.parent.left_child;
				if(temp_y.color == 0)
				{
					node.parent.color = 1;
					temp_y.color = 1;
					node.parent.parent.color = 0;
					node = node.parent.parent;
				}
				else
				{
					if(node == node.parent.left_child)
					{
						node = node.parent;
						Right_Rotate(node);
					}
					node.parent.color = 1;
					node.parent.parent.color = 0;
					Left_Rotate(node.parent.parent);
				}
			}
		}
		root.color = 1;
	}
	//删除修复
	public void RBT_Delete_FixUp(RBTStruct node)
	{
		RBTStruct temp_w = null;
		while(node != root && node.color == 1)
		{
			if(node == node.parent.left_child)
			{
				temp_w = node.parent.right_child;
				if(temp_w.color == 0)
				{
					temp_w.color = 1;
					node.parent.color = 0;
					Left_Rotate(node.parent);
					temp_w = node.parent.right_child;
				}
				if(temp_w.left_child.color == 1 && temp_w.right_child.color == 1)
				{
					temp_w.color = 0;
					node = node.parent;
				}
				else
				{
					if(temp_w.right_child.color == 1)
					{
						temp_w.left_child.color = 1;
						temp_w.color = 0;
						Right_Rotate(temp_w);
						temp_w = node.parent.right_child;
					}
					temp_w.color = node.parent.color;
					node.parent.color = 1;
					temp_w.right_child.color = 1;
					Left_Rotate(node.parent);
					node = root;
				}
			}
			else
			{
				temp_w = node.parent.left_child;
				if(temp_w.color == 0)
				{
					temp_w.color = 1;
					node.parent.color = 0;
					Right_Rotate(node.parent);
					temp_w = node.parent.left_child;
				}
				if(temp_w.right_child.color == 1 && temp_w.left_child.color == 1)
				{
					temp_w.color = 0;
					node = node.parent;
				}
				else
				{
					if(temp_w.left_child.color == 1)
					{
						temp_w.right_child.color = 1;
						temp_w.color = 0;
						Left_Rotate(temp_w);
						temp_w = node.parent.left_child;
					}
					temp_w.color = node.parent.color;
					node.parent.color = 1;
					temp_w.left_child.color = 1;
					Right_Rotate(node.parent);
					node = root;
				}
			}
		}
		node.color =1;
	}
	//打印红黑树
	public void RBT_Print(RBTStruct node)
	{
		char temp_color;
		if(node == Nil)
			System.out.println("Nil");
		else
		{
			if(node.color == 0)
				temp_color='R';
			else
				temp_color='B';
			System.out.println("("+node.key+temp_color+",");
			RBT_Print(node.left_child);
			System.out.println(",");
			RBT_Print(node.right_child);
			System.out.println(")");
		}
	}

}
