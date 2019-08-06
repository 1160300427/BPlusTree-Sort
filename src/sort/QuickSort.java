package sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class QuickSort {
	public static int[] arr = new int[62500];
	public static String[][] rows = new String[62500][2];
	public static long[] times = new long[16];
	
	
	public static void  main(String[] args){
		long time1,time2,time;
		arr = ReadData("data1.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[0] = time;
		WriteData("data1.txt");
		System.out.println("data1内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data2.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[1] = time;
		WriteData("data2.txt");
		System.out.println("data2内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data3.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[2] = time;
		WriteData("data3.txt");
		System.out.println("data3内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data4.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[3] = time;
		WriteData("data4.txt");
		System.out.println("data4内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data5.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[4] = time;
		WriteData("data5.txt");
		System.out.println("data5内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data6.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[5] = time;
		WriteData("data6.txt");
		System.out.println("data6内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data7.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[6] = time;
		WriteData("data7.txt");
		System.out.println("data7内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data8.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[7] = time;
		WriteData("data8.txt");
		System.out.println("data8内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data9.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[8] = time;
		WriteData("data9.txt");
		System.out.println("data9内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data10.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[9] = time;
		WriteData("data10.txt");
		System.out.println("data10内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data11.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[10] = time;
		WriteData("data11.txt");
		System.out.println("data11内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data12.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[11] = time;
		WriteData("data12.txt");
		System.out.println("data12内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data13.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[12] = time;
		WriteData("data13.txt");
		System.out.println("data13内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data14.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[13] = time;
		WriteData("data14.txt");
		System.out.println("data14内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data15.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[14] = time;
		WriteData("data15.txt");
		System.out.println("data15内部快排结束，用时"+time+"ms");
		
		arr = ReadData("data16.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[15] = time;
		WriteData("data16.txt");
		System.out.println("data16内部快排结束，用时"+time+"ms");
		
		long totaltime = 0;
		for(int x=0;x<times.length;x++){
			totaltime = totaltime+times[x];
		}
		System.out.println("内部排序结束，总用时"+totaltime+"ms");
	
	}

	public static void WriteData(String filename) {
		// TODO 自动生成的方法存根
		try {
            File writeName = new File(filename); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            String s = "abcdef";
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
            	for (int x=0;x<arr.length;x++) {
            	    // System.out.print(value+" ");
            	     out.write(arr[x] +"\t" + s+"\r\n");
            	 }    
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];
 
        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
 
        }
        //最后将基准为与i和j相等位置的数字交换
         arr[low] = arr[i];
         arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }


	public static int[] ReadData(String filename) {
		// TODO 自动生成的方法存根
		int index = 0;
        BufferedReader br = null;
        try {
           // 读文件了. 路径就是那个txt文件路径
           br  = new BufferedReader(new FileReader(new File(filename)));
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
        
        for(int x=0; x<rows.length; x++){
        	arr[x] = Integer.parseInt(rows[x][0]);
        }
        
		return arr;
	}
}
