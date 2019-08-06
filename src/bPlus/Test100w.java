package bPlus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Test100w {
	
   public static String[][] rows = new String[1000000][2];
   public static ArrayList<Integer> indexlist = new ArrayList<Integer>();
   
   public static void  main(String[] args){
		indexlist = readFileData();	
		BPlusTree<Product, Integer> b = new BPlusTree<>(4);
        long time1 = System.currentTimeMillis(); ;
     
       for(int i=0;i<indexlist.size();i++){
       	 Product p = new Product(indexlist.get(i), "test100w", "abcdef");
       	 System.out.println("插入"+indexlist.get(i));
         b.insert(p, p.getId());
       }
     long time2 =System.currentTimeMillis(); ;
     System.out.println("插入结束，耗时: " + (time2 - time1)+"ms");
     System.out.println();
     
     String ny = "Y";
     while(!ny.equals("N")){
   	  System.out.println("请输入需要查找的数据：");
   	  long time3 = System.currentTimeMillis(); ;
         Scanner scan = new Scanner(System.in);
         int read = scan.nextInt();
         Product p1 = b.find(read);
         long time4 = System.currentTimeMillis(); 
         System.out.println("查询结束，耗时: " + (time4 - time3)+"ms");
         
         System.out.println("\n是否结束查询Y/N");
         Scanner scan1 = new Scanner(System.in);
         ny = scan1.nextLine();
     }
	}
   
   
    public static ArrayList<Integer> readFileData() {
    	  // 初始化一个用于存储txt数据的数组
        int index = 0;
        BufferedReader br = null;
        try {
           // 读文件了. 路径就是那个txt文件路径
            br  = new BufferedReader(new FileReader(new File("data.txt")));
            String str = null;
            // 按行读取
            while((str=br.readLine())!=null){
            // 用\t分隔
                rows[index] = str.split("\t");
                index++;
                
            }
            
                                          
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //获得indexlist
      for(int x=0; x<rows.length; x++) {
          indexlist.add(Integer.parseInt(rows[x][0]));
          //System.out.println(rows[x][0]+"  ");
         // System.out.println();
      }

        return indexlist;
    }

}
