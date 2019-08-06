package sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class CutData {
	public static String[][] rows = new String[1000000][2];
	
	public static void  main(String[] args){
		rows = ReadData();
		CutData();
	}
/*
 * 内存要求1MB，分割成16个文件
 */
	@SuppressWarnings("resource")
	public static void CutData() {
		// TODO 自动生成的方法存根
		try {
            File writeName1 = new File("data1.txt"); 
            writeName1.createNewFile(); 
            File writeName2 = new File("data2.txt"); 
            writeName2.createNewFile(); 
            File writeName3 = new File("data3.txt"); 
            writeName3.createNewFile(); 
            File writeName4 = new File("data4.txt"); 
            writeName4.createNewFile(); 
            File writeName5 = new File("data5.txt"); 
            writeName5.createNewFile(); 
            File writeName6 = new File("data6.txt"); 
            writeName6.createNewFile(); 
            File writeName7 = new File("data7.txt"); 
            writeName7.createNewFile(); 
            File writeName8 = new File("data8.txt"); 
            writeName8.createNewFile(); 
            File writeName9 = new File("data9.txt"); 
            writeName9.createNewFile(); 
            File writeName10 = new File("data10.txt"); 
            writeName10.createNewFile(); 
            File writeName11 = new File("data11.txt"); 
            writeName11.createNewFile(); 
            File writeName12 = new File("data12.txt"); 
            writeName12.createNewFile(); 
            File writeName13 = new File("data13.txt"); 
            writeName13.createNewFile(); 
            File writeName14 = new File("data14.txt"); 
            writeName14.createNewFile(); 
            File writeName15 = new File("data15.txt"); 
            writeName15.createNewFile(); 
            File writeName16 = new File("data16.txt"); 
            writeName16.createNewFile(); 

            FileWriter writer1 = new FileWriter(writeName1);
            BufferedWriter out1 = new BufferedWriter(writer1);
            FileWriter writer2 = new FileWriter(writeName2);
            BufferedWriter out2 = new BufferedWriter(writer2);
            FileWriter writer3 = new FileWriter(writeName3);
            BufferedWriter out3 = new BufferedWriter(writer3);
            FileWriter writer4 = new FileWriter(writeName4);
            BufferedWriter out4 = new BufferedWriter(writer4);
            FileWriter writer5 = new FileWriter(writeName5);
            BufferedWriter out5 = new BufferedWriter(writer5);
            FileWriter writer6 = new FileWriter(writeName6);
            BufferedWriter out6 = new BufferedWriter(writer6);
            FileWriter writer7 = new FileWriter(writeName7);
            BufferedWriter out7 = new BufferedWriter(writer7);
            FileWriter writer8 = new FileWriter(writeName8);
            BufferedWriter out8 = new BufferedWriter(writer8);
            FileWriter writer9 = new FileWriter(writeName9);
            BufferedWriter out9 = new BufferedWriter(writer9);
            FileWriter writer10 = new FileWriter(writeName10);
            BufferedWriter out10 = new BufferedWriter(writer10);
            FileWriter writer11 = new FileWriter(writeName11);
            BufferedWriter out11 = new BufferedWriter(writer11);
            FileWriter writer12 = new FileWriter(writeName12);
            BufferedWriter out12 = new BufferedWriter(writer12);
            FileWriter writer13 = new FileWriter(writeName13);
            BufferedWriter out13 = new BufferedWriter(writer13);
            FileWriter writer14 = new FileWriter(writeName14);
            BufferedWriter out14 = new BufferedWriter(writer14);
            FileWriter writer15 = new FileWriter(writeName15);
            BufferedWriter out15 = new BufferedWriter(writer15);
            FileWriter writer16 = new FileWriter(writeName16);
            BufferedWriter out16 = new BufferedWriter(writer16);
            
            //分割成16组数据，%16
          for(int x=0; x<rows.length; x++) {
             int mod = x%16;
             switch(mod){
             case 0:
                 out1.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");            
            	 break;
             case 1:
            	 out2.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");            
            	 break;
             case 2:
            	 out3.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 3:
            	 out4.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 4:
            	 out5.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 5:
            	 out6.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 6:
            	 out7.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 7:
            	 out8.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 8:
            	 out9.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 9:
            	 out10.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 10:
            	 out11.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 11:
            	 out12.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 12:
            	 out13.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;            	 
             case 13:
            	 out14.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 14:
            	 out15.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             case 15:
            	 out16.write(rows[x][0] +"\t" + rows[x][1]+"\r\n");
            	 break;
             default:
            		
             }
          }

          out1.flush();
          out2.flush();
          out3.flush();
          out4.flush();
          out5.flush();
          out6.flush();
          out7.flush();
          out8.flush();
          out9.flush();
          out10.flush();
          out11.flush();
          out12.flush();
          out13.flush();
          out14.flush();
          out15.flush();
          out16.flush();
          
		} catch (FileNotFoundException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	      } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	      }
           
	}

	public static String[][] ReadData() {
		// TODO 自动生成的方法存根
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
        return rows;

	}
}
