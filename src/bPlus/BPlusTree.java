package bPlus;

/**
 * ʵ��B+��
 *
 * @param <T> ָ��ֵ����
 * @param <V> ʹ�÷��ͣ�ָ����������,����ָ������̳�Comparable
 */
public class BPlusTree <T, V extends Comparable<V>>{
    //B+���Ľ�
    private Integer bTreeOrder;
    //B+���ķ�Ҷ�ӽڵ���Сӵ�е��ӽڵ�������ͬʱҲ�Ǽ�����С������
    //private Integer minNUmber;
    //B+���ķ�Ҷ�ӽڵ����ӵ�еĽڵ�������ͬʱҲ�Ǽ������������
    private Integer maxNumber;

    private Node<T, V> root;

    private LeafNode<T, V> left;

    //�޲ι��췽����Ĭ�Ͻ�Ϊ3
    public BPlusTree(){
        this(3);
    }

    //�вι��췽���������趨B+���Ľ�
    public BPlusTree(Integer bTreeOrder){
        this.bTreeOrder = bTreeOrder;
        //this.minNUmber = (int) Math.ceil(1.0 * bTreeOrder / 2.0);
        //��Ϊ����ڵ�����п��ܳ��ֳ������޵����,��������Ҫ��1
        this.maxNumber = bTreeOrder + 1;
        this.root = new LeafNode<T, V>();  
        this.left = null;
    }

    //��ѯ
    public T find(V key){
        T t = this.root.find(key);
        System.out.println();
        if(t == null){
            System.out.println("������");
        }
        return t;
    }

    //����
    @SuppressWarnings("null")
	public void insert(T value, V key){
        if(key == null)
            return;
        Node<T, V> t = this.root.insert(value, key);
        //root��left������������ 32
        if(t != null)
            this.root = t;                   
        this.left = (LeafNode<T, V>)this.root.refreshLeft();
      
        
        System.out.print("�������,��ǰ���ڵ�Ϊ:");
        for(int j = 0; j < this.root.number; j++) {
            System.out.print((V) this.root.keys[j] + " ");
        }
        System.out.println();
        System.out.println("=======================================");
    }


    /**
     * �ڵ㸸�࣬��Ϊ��B+���У���Ҷ�ӽڵ㲻�ô洢��������ݣ�ֻ��Ҫ��������Ϊ���Ϳ�����
     * ����Ҷ�ӽڵ�ͷ�Ҷ�ӽڵ���಻̫һ���������ֻṫ��һЩ������������Node����Ϊ����,
     * ������ΪҪ�������һЩ���з���������ʹ�ó�����
     *
     * @param <T> ͬBPlusTree
     * @param <V>
     */
    abstract class Node<T, V extends Comparable<V>>{
        //���ڵ�
        protected Node<T, V> parent;
        //�ӽڵ�
        protected Node<T, V>[] childs;
        //�����ӽڵ㣩����
        protected Integer number;  //һ���ڵ������number���ӽڵ㣨ָ������
        //��
        protected Object keys[];

        //���췽��
        public Node(){
            this.keys = new Object[maxNumber];
            this.childs = new Node[maxNumber];
            this.number = 0;
            this.parent = null;
        }

        //����
        abstract T find(V key);

        //����
        abstract Node<T, V> insert(T value, V key);

        abstract LeafNode<T, V> refreshLeft();
    }


    /**
     * ��Ҷ�ڵ���
     * @param <T>
     * @param <V>
     */

    class BPlusNode <T, V extends Comparable<V>> extends Node<T, V>{

        public BPlusNode() {
            super();
        }

        /**
         * �ݹ����,����ֻ��Ϊ��ȷ��ֵ��������һ��,�����Ĳ��ҵ�Ҷ�ӽڵ�Ż��
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
         * �ݹ����,�Ȱ�ֵ���뵽��Ӧ��Ҷ�ӽڵ�,���ս�����Ҷ�ӽڵ�Ĳ�����
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

 //          System.out.println("152��Ҷ�ӽڵ����key: " + this.keys[i]);

            return this.childs[i].insert(value, key);
        }

        @Override
        LeafNode<T, V> refreshLeft() {
        	
            return this.childs[0].refreshLeft();
        }

        /**
         * ��Ҷ�ӽڵ����ɹ���ɷֽ�ʱ,�ݹ���򸸽ڵ�����µĽڵ��Ա���ƽ��
         * @param node1  �ѿ�֮�����ڵ�
         * @param node2  �ѿ�֮����ҽڵ�
         * @param key    ԭʼ���ڵĸ��ڵ��key
         */
        Node<T, V> insertNode(Node<T, V> node1, Node<T, V> node2, V key){

//            System.out.println("170��Ҷ�ӽڵ�,�ݹ���򸸽ڵ�����µĽڵ��Ա���ƽ�⣺" );
//            System.out.println("171��ڵ��Ҳ� �� "+ node1.keys[node1.number - 1]+" �ҽڵ��Ҳ� �� "+ node2.keys[node2.number - 1]);
        	 System.out.print("��ֺ���ڵ�:");
             for(int j = 0; j < node1.number; j++) {
                 System.out.print((V) node1.keys[j] + " ");
             }
             System.out.println();
             System.out.print("��ֺ��ҽڵ�:");
             for(int j = 0; j < node2.number; j++) {
                 System.out.print((V) node2.keys[j] + " ");
             }
             System.out.println();
             
        	
            V oldKey = null;
            if(this.number > 0)
                oldKey = (V) this.keys[this.number - 1];
            //���ԭ��keyΪnull,���ڵ��ѿ�ʱ����û�и��ڵ��ʱoldkey = null��˵�������Ҷ�ӽڵ��ǿյ�,ֱ�ӷ��������ڵ㼴��
            if(key == null || this.number <= 0){
 //               System.out.println("177��Ҷ�ӽڵ�,�������ҽڵ�: " + node1.keys[node1.number - 1] + " " + node2.keys[node2.number - 1] + "ֱ�Ӳ���");
                this.keys[0] = node1.keys[node1.number - 1];
                this.keys[1] = node2.keys[node2.number - 1];
                this.childs[0] = node1;
                this.childs[1] = node2;
                this.number += 2;
                
                System.out.print("�²����ĸ��ڵ�ֵΪ:");
                for(int j = 0; j < this.number; j++)
                    System.out.print(this.keys[j] + " ");
                System.out.println();
                return this;
                
            }
        
            
            //ԭ�нڵ㲻Ϊ��,��Ӧ����Ѱ��ԭ�нڵ��λ��,Ȼ���µĽڵ���뵽ԭ�нڵ���
            int i = 0;
            while((V)this.keys[i]!=null && key.compareTo((V)this.keys[i]) != 0){
                i++;
            }
            System.out.println("i = "+i);
//            System.out.print("ԭ���Ĳ����ĸ��ڵ�ֵΪ:");
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
            //��߽ڵ�����ֵ����ֱ�Ӳ���,�ұߵ�ҪŲһŲ�ٽ��в���
            this.keys[i] = node1.keys[node1.number - 1];
            this.childs[i] = node1;
//            System.out.println("��ڵ����node1.keys = "+ this.keys[i]+" i = "+i);
//           
//                     
//            System.out.print("ԭ����Ϊ:");
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
           
//            System.out.println("�ҽڵ����node2.keys = "+ tempKeys[i + 1]+" i = "+i);
//            System.out.print("264tempKeys:");
//            for(int j = 0; j < tempKeys.length; j++)
//                System.out.print(tempKeys[j] + " ");
//            System.out.println();
            this.number++;
            

            //�ж��Ƿ���Ҫ���
            //�������Ҫ���,�����鸴�ƻ�ȥ,ֱ�ӷ���
            if(this.number <= bTreeOrder){
                System.arraycopy(tempKeys, 0, this.keys, 0, this.number);
                System.arraycopy(tempChilds, 0, this.childs, 0, this.number);

              // System.out.println("212��Ҷ�ӽڵ�,�������ҽڵ�: " + node1.keys[node1.number - 1] + " " + node2.keys[node2.number - 1] + ", ����Ҫ���");

                System.out.print("�²����ĸ��ڵ�ֵΪ:");
                for(int j = 0; j < this.number; j++)
                    System.out.print(this.keys[j] + " ");
                System.out.println();
                return null;
            }

           System.out.println("��Ҷ�ӽڵ���Ҫ���");

            //�����Ҫ���,�Ͳ�Ҷ�ӽڵ�ʱ����,���м��
            Integer middle = this.number / 2;

            //�½���Ҷ�ӽڵ�,��Ϊ��ֵ��Ұ벿��
            BPlusNode<T, V> tempNode = new BPlusNode<T, V>();
            //��Ҷ�ڵ��ֺ�Ӧ�ý����ӽڵ�ĸ��ڵ�ָ�����Ϊ��ȷ��ָ��
            tempNode.number = this.number - middle;
            tempNode.parent = this.parent;
            //������ڵ�Ϊ��,���½�һ����Ҷ�ӽڵ���Ϊ���ڵ�,�����ò�ֳɹ���������Ҷ�ӽڵ��ָ��ָ�򸸽ڵ�
            if(this.parent == null) {

                //System.out.println("230��Ҷ�ӽڵ�,�������ҽڵ�: " + node1.keys[node1.number - 1] + " " + node2.keys[node2.number - 1] + ",�½����ڵ�");

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
            //��ԭ�з�Ҷ�ӽڵ���Ϊ��߽ڵ�
            this.number = middle;
            this.keys = new Object[maxNumber];
            this.childs = new Node[maxNumber];
            System.arraycopy(tempKeys, 0, this.keys, 0, middle);
            System.arraycopy(tempChilds, 0, this.childs, 0, middle);

            //Ҷ�ӽڵ��ֳɹ���,��Ҫ�������ɵĽڵ���븸�ڵ�
            BPlusNode<T, V> parentNode = (BPlusNode<T, V>)this.parent;
            return parentNode.insertNode(this, tempNode, oldKey);
        }

    }

    /**
     * Ҷ�ڵ���
     * ��벿�֣��Ұ벿�֣�����洢ʹ��
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
         * ���в���,������ֲ���,�����ע��
         * @param key
         * @return
         */
        @Override
        T find(V key) {
            if(this.number <=0)
                return null;

//            System.out.println("284Ҷ�ӽڵ����");

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

//            System.out.println("313Ҷ�ӽڵ�,����key: " + key);

            //����ԭʼ���ڸ��ڵ��keyֵ
            V oldKey = null;
            if(this.number > 0)
                oldKey = (V) this.keys[this.number - 1];
            //�Ȳ�������
            int i = 0;
           //�����������ڵ�ǰ���ϡ�����Ԫ���������У�ȷ�������λ��
            while(i < this.number){
                if((V) this.keys[i]!=null && key.compareTo((V) this.keys[i]) < 0)  
                    break;
                i++;
            }
            
//            System.out.println("342Ҷ�ڵ�����鵱ǰkeyΪ:");
//            for(int j = 0; j < this.number; j++)
//                System.out.print(this.keys[j] + " ");
//            System.out.println();
            //��������,�����ӡ�������ԭ����iλ�������key��ȥ
            //public static void arraycopy(ԭ����, ԭ������ʼλ��, Ŀ������, Ŀ��������ʼλ��, Ŀ�����鳤��)
            Object tempKeys[] = new Object[maxNumber];
            Object tempValues[] = new Object[maxNumber];
            System.arraycopy(this.keys, 0, tempKeys, 0, i);
            System.arraycopy(this.values, 0, tempValues, 0, i);
            System.arraycopy(this.keys, i, tempKeys, i + 1, this.number - i);
            System.arraycopy(this.values, i, tempValues, i + 1, this.number - i);
            tempKeys[i] = key;
            tempValues[i] = value;

            this.number++;  //��ʱ�ڵ��а�����������+1

            System.out.println("Ҷ�ڵ������ɺ�Ľڵ�ֵ:");
            for(int j = 0; j < this.number; j++)
                System.out.print(tempKeys[j] + " ");
            System.out.println();

            //�ж��Ƿ���Ҫ��֡���������ǰ�ڵ�������СС������Ҫ��
            //�������Ҫ�����ɸ��ƺ�ֱ�ӷ���
            if(this.number <= bTreeOrder){
            	//���µ�ǰ�ڵ��key��Ϣ
                System.arraycopy(tempKeys, 0, this.keys, 0, this.number);
                System.arraycopy(tempValues, 0, this.values, 0, this.number);

                //�п�����Ȼû�нڵ���ѣ�����ʵ���ϲ����ֵ������ԭ�������ֵ���������и��ڵ�ı߽�ֵ��Ҫ���и���
                Node node = this;
                while (node.parent != null){
                    V tempkey = (V)node.keys[node.number - 1];   //��ǰ�ڵ����ұߵ�ֵ�����ֵ
                    //��ǰ�ڵ�����ֵ�����˸��ڵ�����ֵ
                    if((V)node.parent.keys[node.parent.number - 1]!=null &&tempkey.compareTo((V)node.parent.keys[node.parent.number - 1]) > 0){
                        node.parent.keys[node.parent.number - 1] = tempkey;
                        node = node.parent;
                        //System.out.println("361Ҷ�ӽڵ�,����key: " + key + ",����Ҫ���&&���¸��ڵ�߽�ֵ");
                    }
                    else
                    {
                    	break;
                    }
                }
                //System.out.println("363Ҷ�ӽڵ�,����key: " + key + ",����Ҫ���");

                return null;
            }
          
            
            System.out.println("Ҷ�ӽڵ���Ҫ���");

            //�����Ҫ���,����м�ѽڵ��ֲ���������
            Integer middle = this.number / 2;

            //�½�Ҷ�ӽڵ�,��Ϊ��ֵ��Ұ벿��
            LeafNode<T, V> tempNode = new LeafNode<T, V>();
            tempNode.number = this.number - middle;
            tempNode.parent = this.parent;
            //������ڵ�Ϊ��,���½�һ����Ҷ�ӽڵ���Ϊ���ڵ�,�����ò�ֳɹ�������Ҷ�ӽڵ��ָ��ָ�򸸽ڵ�
            if(this.parent == null) {

                //System.out.println("380Ҷ�ӽڵ�,����key: " + key + ",���ڵ�Ϊ�� �½����ڵ�");

                BPlusNode<T, V> tempBPlusNode = new BPlusNode<>();
                tempNode.parent = tempBPlusNode;
                this.parent = tempBPlusNode;
                oldKey = null;
            }
            //���ƣ������Ұ벿��
            System.arraycopy(tempKeys, middle, tempNode.keys, 0, tempNode.number);
            System.arraycopy(tempValues, middle, tempNode.values, 0, tempNode.number);

            //��ԭ��Ҷ�ӽڵ���Ϊ��ֵ���벿��
            this.number = middle;
            this.keys = new Object[maxNumber];
            this.values = new Object[maxNumber];
            System.arraycopy(tempKeys, 0, this.keys, 0, middle);
            System.arraycopy(tempValues, 0, this.values, 0, middle);

            this.right = tempNode;
            tempNode.left = this;

            //Ҷ�ӽڵ��ֳɹ���,��Ҫ�������ɵĽڵ㣨��ڵ㡢�ҽڵ㣩���븸�ڵ�
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

