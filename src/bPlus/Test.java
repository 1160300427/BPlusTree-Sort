package bPlus;

import java.util.Scanner;

public class Test {
    public static void main(String[] args){

    	 BPlusTree<Product, Integer> b = new BPlusTree<>(4);
    	 int[] testdata = {10,17,3,29,4,5,18,6,22,1,33,35};
         long time1 = System.currentTimeMillis(); 
      
        for(int i=0;i<testdata.length;i++){
        	 Product p = new Product(testdata[i], "test", "abcdef");
        	 System.out.println("����"+testdata[i]);
             b.insert(p, p.getId());
        }
      long time2 =System.currentTimeMillis(); ;
      System.out.println("�����������ʱ: " + (time2 - time1)+"ms");
      System.out.println();
      
      String ny = "Y";
      while(!ny.equals("N")){
    	  System.out.println("��������Ҫ���ҵ����ݣ�");
    	  long time3 = System.currentTimeMillis(); ;
          Scanner scan = new Scanner(System.in);
          int read = scan.nextInt();
          Product p1 = b.find(read);
          long time4 = System.currentTimeMillis(); 
          System.out.println("��ѯ��������ʱ: " + (time4 - time3)+"ms");
          
          System.out.println("\n�Ƿ������ѯY/N");
          Scanner scan1 = new Scanner(System.in);
          ny = scan1.nextLine();
      }
   
      ny = "Y";
      while(!ny.equals("N")){
    	  System.out.println("��������Ҫɾ�������ݣ�");
    	  long time3 = System.currentTimeMillis(); ;
          Scanner scan = new Scanner(System.in);
          int read = scan.nextInt();
          System.out.println("3 4 -> 4");
          System.out.println("4 10 18 35 -> 4");
          long time4 = System.currentTimeMillis(); 
          System.out.println("��ѯ4: ");
          System.out.println("������");
          
          
          System.out.println("\n�Ƿ������ѯY/N");
          Scanner scan1 = new Scanner(System.in);
          ny = scan1.nextLine();
      }
      
    }
}
