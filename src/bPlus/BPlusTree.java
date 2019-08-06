package bPlus;

/**
 * 实现B+树
 *
 * @param <T> 指定值类型
 * @param <V> 使用泛型，指定索引类型,并且指定必须继承Comparable
 */
public class BPlusTree <T, V extends Comparable<V>>{
    //B+树的阶
    private Integer bTreeOrder;
    //B+树的非叶子节点最小拥有的子节点数量（同时也是键的最小数量）
    //private Integer minNUmber;
    //B+树的非叶子节点最大拥有的节点数量（同时也是键的最大数量）
    private Integer maxNumber;

    private Node<T, V> root;

    private LeafNode<T, V> left;

    //无参构造方法，默认阶为3
    public BPlusTree(){
        this(3);
    }

    //有参构造方法，可以设定B+树的阶
    public BPlusTree(Integer bTreeOrder){
        this.bTreeOrder = bTreeOrder;
        //this.minNUmber = (int) Math.ceil(1.0 * bTreeOrder / 2.0);
        //因为插入节点过程中可能出现超过上限的情况,所以这里要加1
        this.maxNumber = bTreeOrder + 1;
        this.root = new LeafNode<T, V>();  
        this.left = null;
    }

    //查询
    public T find(V key){
        T t = this.root.find(key);
        System.out.println();
        if(t == null){
            System.out.println("不存在");
        }
        return t;
    }

    //插入
    @SuppressWarnings("null")
	public void insert(T value, V key){
        if(key == null)
            return;
        Node<T, V> t = this.root.insert(value, key);
        //root、left来建立新树叉 32
        if(t != null)
            this.root = t;                   
        this.left = (LeafNode<T, V>)this.root.refreshLeft();
      
        
        System.out.print("插入完成,当前根节点为:");
        for(int j = 0; j < this.root.number; j++) {
            System.out.print((V) this.root.keys[j] + " ");
        }
        System.out.println();
        System.out.println("=======================================");
    }


    /**
     * 节点父类，因为在B+树中，非叶子节点不用存储具体的数据，只需要把索引作为键就可以了
     * 所以叶子节点和非叶子节点的类不太一样，但是又会公用一些方法，所以用Node类作为父类,
     * 而且因为要互相调用一些公有方法，所以使用抽象类
     *
     * @param <T> 同BPlusTree
     * @param <V>
     */
    abstract class Node<T, V extends Comparable<V>>{
        //父节点
        protected Node<T, V> parent;
        //子节点
        protected Node<T, V>[] childs;
        //键（子节点）数量
        protected Integer number;  //一个节点最多有number个子节点（指针数）
        //键
        protected Object keys[];

        //构造方法
        public Node(){
            this.keys = new Object[maxNumber];
            this.childs = new Node[maxNumber];
            this.number = 0;
            this.parent = null;
        }

        //查找
        abstract T find(V key);

        //插入
        abstract Node<T, V> insert(T value, V key);

        abstract LeafNode<T, V> refreshLeft();
    }


    /**
     * 非叶节点类
     * @param <T>
     * @param <V>
     */

    class BPlusNode <T, V extends Comparable<V>> extends Node<T, V>{

        public BPlusNode() {
            super();
        }

        /**
         * 递归查找,这里只是为了确定值究竟在哪一块,真正的查找到叶子节点才会查
         * @param key
         * @return
         */
        @SuppressWarnings("unchecked")
		@Override
        T find(V key) {
            int i = 0;
            while(i < this.number){
                if(this.keys[i] != null && key.compareTo((V) this.keys[i]) <= 0)
                    break;
                i++;
            }
            if(this.number == i)
                return null;
           // System.out.print(this.keys[i] + " ");
            return this.childs[i].find(key);
        }

        /**
         * 递归插入,先把值插入到对应的叶子节点,最终讲调用叶子节点的插入类
         * @param value
         * @param key
         */
        @SuppressWarnings("unchecked")
		@Override
        Node<T, V> insert(T value, V key) {
            int i = 0;
            while(i < this.number){
                if((V) this.keys[i]!=null && key.compareTo((V) this.keys[i]) < 0)
                    break;
                i++;
            }
            if( (V)this.keys[this.number - 1] != null&& key.compareTo((V) this.keys[this.number - 1]) >= 0) {
                i--;
//                if(this.childs[i].number + 1 <= bTreeOrder) {
//                    this.keys[this.number - 1] = key;
//                }
            }

 //          System.out.println("152非叶子节点查找key: " + this.keys[i]);

            return this.childs[i].insert(value, key);
        }

        @Override
        LeafNode<T, V> refreshLeft() {
        	
            return this.childs[0].refreshLeft();
        }

        /**
         * 当叶子节点插入成功完成分解时,递归地向父节点插入新的节点以保持平衡
         * @param node1  裂开之后的左节点
         * @param node2  裂开之后的右节点
         * @param key    原始存在的父节点的key
         */
        Node<T, V> insertNode(Node<T, V> node1, Node<T, V> node2, V key){

//            System.out.println("170非叶子节点,递归地向父节点插入新的节点以保持平衡：" );
//            System.out.println("171左节点右侧 ： "+ node1.keys[node1.number - 1]+" 右节点右侧 ： "+ node2.keys[node2.number - 1]);
        	 System.out.print("拆分后左节点:");
             for(int j = 0; j < node1.number; j++) {
                 System.out.print((V) node1.keys[j] + " ");
             }
             System.out.println();
             System.out.print("拆分后右节点:");
             for(int j = 0; j < node2.number; j++) {
                 System.out.print((V) node2.keys[j] + " ");
             }
             System.out.println();
             
        	
            V oldKey = null;
            if(this.number > 0)
                oldKey = (V) this.keys[this.number - 1];
            //如果原有key为null,（节点裂开时往上没有父节点此时oldkey = null）说明这个非叶子节点是空的,直接放入两个节点即可
            if(key == null || this.number <= 0){
 //               System.out.println("177非叶子节点,插入左右节点: " + node1.keys[node1.number - 1] + " " + node2.keys[node2.number - 1] + "直接插入");
                this.keys[0] = node1.keys[node1.number - 1];
                this.keys[1] = node2.keys[node2.number - 1];
                this.childs[0] = node1;
                this.childs[1] = node2;
                this.number += 2;
                
                System.out.print("新产生的父节点值为:");
                for(int j = 0; j < this.number; j++)
                    System.out.print(this.keys[j] + " ");
                System.out.println();
                return this;
                
            }
        
            
            //原有节点不为空,则应该先寻找原有节点的位置,然后将新的节点插入到原有节点中
            int i = 0;
            while((V)this.keys[i]!=null && key.compareTo((V)this.keys[i]) != 0){
                i++;
            }
            System.out.println("i = "+i);
//            System.out.print("原来的产生的父节点值为:");
//            for(int j = 0; j < this.number; j++)
//                System.out.print(this.keys[j] + " ");
//            System.out.println();
            
            int oldparentmax = Integer.parseInt(this.keys[this.number-1].toString());
            Object oldparentmax1 = this.keys[this.number-1];
//            System.out.println("oldparentmax = "+oldparentmax);
            Object tempKeys[] = new Object[maxNumber];
            Object tempChilds[] = new Node[maxNumber];
            if(i+1<this.number){
            	for(int j=i;j<this.number;j++){
            		 tempKeys[j-i] = this.keys[j];
            		 tempChilds[j-i] =  this.childs[j];
            		 
            		 System.out.println(" tempKeys[j-i] = "+ tempKeys[j-i]);
            	}
            	int i1 = i+1;
            	System.out.println("i+1 = "+i1+" this.number = "+this.number);
            	 System.arraycopy(tempKeys, 0, this.keys, i1, this.number+1);
                 System.arraycopy(tempChilds, 0, this.childs, i1, this.number+1);
            }
            //左边节点的最大值可以直接插入,右边的要挪一挪再进行插入
            this.keys[i] = node1.keys[node1.number - 1];
            this.childs[i] = node1;
//            System.out.println("左节点插入node1.keys = "+ this.keys[i]+" i = "+i);
//           
//                     
//            System.out.print("原数组为:");
//            for(int j = 0; j < this.number; j++)
//                System.out.print(this.keys[j] + " ");
//            System.out.println();
            
//            System.arraycopy(this.keys, 0, tempKeys, 0, i + 1);
//            System.arraycopy(this.childs, 0, tempChilds, 0, i + 1);
//            System.arraycopy(this.keys, i + 1, tempKeys, 0, this.number - i - 1);
//            System.arraycopy(this.childs, i + 1, tempChilds, 0, this.number - i - 1);
            System.arraycopy(this.keys, 0, tempKeys, 0, this.number);
           System.arraycopy(this.childs, 0, tempChilds, 0, this.number);
//            System.out.print("249tempKeys:");
//            for(int j = 0; j < tempKeys.length; j++)
//                System.out.print(tempKeys[j] + " ");
//            System.out.println();
//            System.out.println("Integer.parseInt(node2.keys[node2.number - 1].toString()"+Integer.parseInt(node2.keys[node2.number - 1].toString()));
            if(Integer.parseInt(node2.keys[node2.number - 1].toString()) >= oldparentmax){
            	 tempKeys[this.number] = node2.keys[node2.number - 1];
                 tempChilds[this.number] = node2;
 //                System.out.println("if");
            }
            else{
            	 tempKeys[this.number] = oldparentmax;
                 tempChilds[this.number] = node2;
//                 System.out.println("else");
            }
           
//            System.out.println("右节点插入node2.keys = "+ tempKeys[i + 1]+" i = "+i);
//            System.out.print("264tempKeys:");
//            for(int j = 0; j < tempKeys.length; j++)
//                System.out.print(tempKeys[j] + " ");
//            System.out.println();
            this.number++;
            

            //判断是否需要拆分
            //如果不需要拆分,把数组复制回去,直接返回
            if(this.number <= bTreeOrder){
                System.arraycopy(tempKeys, 0, this.keys, 0, this.number);
                System.arraycopy(tempChilds, 0, this.childs, 0, this.number);

              // System.out.println("212非叶子节点,插入左右节点: " + node1.keys[node1.number - 1] + " " + node2.keys[node2.number - 1] + ", 不需要拆分");

                System.out.print("新产生的父节点值为:");
                for(int j = 0; j < this.number; j++)
                    System.out.print(this.keys[j] + " ");
                System.out.println();
                return null;
            }

           System.out.println("非叶子节点需要拆分");

            //如果需要拆分,和拆叶子节点时类似,从中间拆开
            Integer middle = this.number / 2;

            //新建非叶子节点,作为拆分的右半部分
            BPlusNode<T, V> tempNode = new BPlusNode<T, V>();
            //非叶节点拆分后应该将其子节点的父节点指针更新为正确的指针
            tempNode.number = this.number - middle;
            tempNode.parent = this.parent;
            //如果父节点为空,则新建一个非叶子节点作为父节点,并且让拆分成功的两个非叶子节点的指针指向父节点
            if(this.parent == null) {

                //System.out.println("230非叶子节点,插入左右节点: " + node1.keys[node1.number - 1] + " " + node2.keys[node2.number - 1] + ",新建父节点");

                BPlusNode<T, V> tempBPlusNode = new BPlusNode<>();
                tempNode.parent = tempBPlusNode;
                this.parent = tempBPlusNode;
                oldKey = null;
            }
            System.arraycopy(tempKeys, middle, tempNode.keys, 0, tempNode.number);
            System.arraycopy(tempChilds, middle, tempNode.childs, 0, tempNode.number);
            for(int j = 0; j < tempNode.number; j++){
                tempNode.childs[j].parent = tempNode;
            }
            //让原有非叶子节点作为左边节点
            this.number = middle;
            this.keys = new Object[maxNumber];
            this.childs = new Node[maxNumber];
            System.arraycopy(tempKeys, 0, this.keys, 0, middle);
            System.arraycopy(tempChilds, 0, this.childs, 0, middle);

            //叶子节点拆分成功后,需要把新生成的节点插入父节点
            BPlusNode<T, V> parentNode = (BPlusNode<T, V>)this.parent;
            return parentNode.insertNode(this, tempNode, oldKey);
        }

    }

    /**
     * 叶节点类
     * 左半部分，右半部分，方便存储使用
     * @param <T>
     * @param <V>
     */
    class LeafNode <T, V extends Comparable<V>> extends Node<T, V> {

        protected Object values[];  
        protected LeafNode left;
        protected LeafNode right;

        public LeafNode(){
            super();
            this.values = new Object[maxNumber];
            this.left = null;
            this.right = null;
        }

        /**
         * 进行查找,经典二分查找,不多加注释
         * @param key
         * @return
         */
        @Override
        T find(V key) {
            if(this.number <=0)
                return null;

//            System.out.println("284叶子节点查找");

            Integer left = 0;
            Integer right = this.number;

            Integer middle = (left + right) / 2;

            while(left < right){
                V middleKey = (V) this.keys[middle];
                System.out.print(middleKey+" ");
                if(middleKey != null && key.compareTo(middleKey) == 0)
                    return (T) this.values[middle];
                else if(middleKey!=null && key.compareTo(middleKey) < 0)
                    right = middle;
                else
                    left = middle;
                middle = (left + right) / 2;
            }
            return null;
        }

        /**
         *
         * @param value
         * @param key
         */
        @Override
        Node<T, V> insert(T value, V key) {

//            System.out.println("313叶子节点,插入key: " + key);

            //保存原始存在父节点的key值
            V oldKey = null;
            if(this.number > 0)
                oldKey = (V) this.keys[this.number - 1];
            //先插入数据
            int i = 0;
           //插入索引大于当前集合。集合元素升序排列，确定插入的位置
            while(i < this.number){
                if((V) this.keys[i]!=null && key.compareTo((V) this.keys[i]) < 0)  
                    break;
                i++;
            }
            
//            System.out.println("342叶节点插入检查当前key为:");
//            for(int j = 0; j < this.number; j++)
//                System.out.print(this.keys[j] + " ");
//            System.out.println();
            //复制数组,完成添加————原数组i位置添加新key进去
            //public static void arraycopy(原数组, 原数组起始位置, 目标数组, 目标数组起始位置, 目标数组长度)
            Object tempKeys[] = new Object[maxNumber];
            Object tempValues[] = new Object[maxNumber];
            System.arraycopy(this.keys, 0, tempKeys, 0, i);
            System.arraycopy(this.values, 0, tempValues, 0, i);
            System.arraycopy(this.keys, i, tempKeys, i + 1, this.number - i);
            System.arraycopy(this.values, i, tempValues, i + 1, this.number - i);
            tempKeys[i] = key;
            tempValues[i] = value;

            this.number++;  //此时节点中包含的索引数+1

            System.out.println("叶节点插入完成后的节点值:");
            for(int j = 0; j < this.number; j++)
                System.out.print(tempKeys[j] + " ");
            System.out.println();

            //判断是否需要拆分————当前节点索引大小小于树的要求
            //如果不需要拆分完成复制后直接返回
            if(this.number <= bTreeOrder){
            	//更新当前节点的key信息
                System.arraycopy(tempKeys, 0, this.keys, 0, this.number);
                System.arraycopy(tempValues, 0, this.values, 0, this.number);

                //有可能虽然没有节点分裂，但是实际上插入的值大于了原来的最大值，所以所有父节点的边界值都要进行更新
                Node node = this;
                while (node.parent != null){
                    V tempkey = (V)node.keys[node.number - 1];   //当前节点最右边的值，最大值
                    //当前节点的最大值大于了父节点的最大值
                    if((V)node.parent.keys[node.parent.number - 1]!=null &&tempkey.compareTo((V)node.parent.keys[node.parent.number - 1]) > 0){
                        node.parent.keys[node.parent.number - 1] = tempkey;
                        node = node.parent;
                        //System.out.println("361叶子节点,插入key: " + key + ",不需要拆分&&更新父节点边界值");
                    }
                    else
                    {
                    	break;
                    }
                }
                //System.out.println("363叶子节点,插入key: " + key + ",不需要拆分");

                return null;
            }
          
            
            System.out.println("叶子节点需要拆分");

            //如果需要拆分,则从中间把节点拆分差不多的两部分
            Integer middle = this.number / 2;

            //新建叶子节点,作为拆分的右半部分
            LeafNode<T, V> tempNode = new LeafNode<T, V>();
            tempNode.number = this.number - middle;
            tempNode.parent = this.parent;
            //如果父节点为空,则新建一个非叶子节点作为父节点,并且让拆分成功的两个叶子节点的指针指向父节点
            if(this.parent == null) {

                //System.out.println("380叶子节点,插入key: " + key + ",父节点为空 新建父节点");

                BPlusNode<T, V> tempBPlusNode = new BPlusNode<>();
                tempNode.parent = tempBPlusNode;
                this.parent = tempBPlusNode;
                oldKey = null;
            }
            //复制，建成右半部分
            System.arraycopy(tempKeys, middle, tempNode.keys, 0, tempNode.number);
            System.arraycopy(tempValues, middle, tempNode.values, 0, tempNode.number);

            //让原有叶子节点作为拆分的左半部分
            this.number = middle;
            this.keys = new Object[maxNumber];
            this.values = new Object[maxNumber];
            System.arraycopy(tempKeys, 0, this.keys, 0, middle);
            System.arraycopy(tempValues, 0, this.values, 0, middle);

            this.right = tempNode;
            tempNode.left = this;

            //叶子节点拆分成功后,需要把新生成的节点（左节点、右节点）插入父节点
            BPlusNode<T, V> parentNode = (BPlusNode<T, V>)this.parent;
            //System.out.println("411 oldkey = "+oldKey);
            return parentNode.insertNode(this, tempNode, oldKey);
        }

        @Override
        LeafNode<T, V> refreshLeft() {
            if(this.number <= 0){ 
            	return null;
            }
            return this;
        }
    }
}

