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
            File writeName = new File("data.txt"); // ���·�������û����Ҫ����һ���µ�output.txt�ļ�
            writeName.createNewFile(); // �������ļ�,��ͬ�����ļ��Ļ�ֱ�Ӹ���
            String s = "abcdef";
            Set<Integer> index = new HashSet<Integer>();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
            	System.out.println("set��ʼ");
            	while(index.size() < 1000000){
            		index.add((int)(1+Math.random()*(10000000-1+1)));
           
            	}
            	System.out.println("set����"+index.size());
            	for (Integer value : index) {
            	    // System.out.print(value+" ");
            	     out.write(value +"\t" + s+"\r\n");
            	 }    
                out.flush(); // �ѻ���������ѹ���ļ�
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
