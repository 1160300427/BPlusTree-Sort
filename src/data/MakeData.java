package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MakeData {
	
	public static void  main(String[] args){
		MakeDataTxt();
	}
	
	public static void MakeDataTxt(){
		try {
            File writeName = new File("data.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            String s = "abcdef";
            Set<Integer> index = new HashSet<Integer>();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
            	System.out.println("set开始");
            	while(index.size() < 1000000){
            		index.add((int)(1+Math.random()*(10000000-1+1)));
           
            	}
            	System.out.println("set结束"+index.size());
            	for (Integer value : index) {
            	    // System.out.print(value+" ");
            	     out.write(value +"\t" + s+"\r\n");
            	 }    
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
