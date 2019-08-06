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
		System.out.println("data1�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data2.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[1] = time;
		WriteData("data2.txt");
		System.out.println("data2�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data3.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[2] = time;
		WriteData("data3.txt");
		System.out.println("data3�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data4.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[3] = time;
		WriteData("data4.txt");
		System.out.println("data4�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data5.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[4] = time;
		WriteData("data5.txt");
		System.out.println("data5�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data6.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[5] = time;
		WriteData("data6.txt");
		System.out.println("data6�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data7.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[6] = time;
		WriteData("data7.txt");
		System.out.println("data7�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data8.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[7] = time;
		WriteData("data8.txt");
		System.out.println("data8�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data9.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[8] = time;
		WriteData("data9.txt");
		System.out.println("data9�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data10.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[9] = time;
		WriteData("data10.txt");
		System.out.println("data10�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data11.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[10] = time;
		WriteData("data11.txt");
		System.out.println("data11�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data12.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[11] = time;
		WriteData("data12.txt");
		System.out.println("data12�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data13.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[12] = time;
		WriteData("data13.txt");
		System.out.println("data13�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data14.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[13] = time;
		WriteData("data14.txt");
		System.out.println("data14�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data15.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[14] = time;
		WriteData("data15.txt");
		System.out.println("data15�ڲ����Ž�������ʱ"+time+"ms");
		
		arr = ReadData("data16.txt");
		time1 = System.currentTimeMillis(); 
		quickSort(arr, 0, arr.length-1);
	    time2 = System.currentTimeMillis(); 
		time = time2 - time1;
		times[15] = time;
		WriteData("data16.txt");
		System.out.println("data16�ڲ����Ž�������ʱ"+time+"ms");
		
		long totaltime = 0;
		for(int x=0;x<times.length;x++){
			totaltime = totaltime+times[x];
		}
		System.out.println("�ڲ��������������ʱ"+totaltime+"ms");
	
	}

	public static void WriteData(String filename) {
		// TODO �Զ����ɵķ������
		try {
            File writeName = new File(filename); // ���·�������û����Ҫ����һ���µ�output.txt�ļ�
            writeName.createNewFile(); // �������ļ�,��ͬ�����ļ��Ļ�ֱ�Ӹ���
            String s = "abcdef";
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
            	for (int x=0;x<arr.length;x++) {
            	    // System.out.print(value+" ");
            	     out.write(arr[x] +"\t" + s+"\r\n");
            	 }    
                out.flush(); // �ѻ���������ѹ���ļ�
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
        //temp���ǻ�׼λ
        temp = arr[low];
 
        while (i<j) {
            //�ȿ��ұߣ���������ݼ�
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //�ٿ���ߣ��������ҵ���
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //������������򽻻�
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
 
        }
        //��󽫻�׼Ϊ��i��j���λ�õ����ֽ���
         arr[low] = arr[i];
         arr[i] = temp;
        //�ݹ�����������
        quickSort(arr, low, j-1);
        //�ݹ�����Ұ�����
        quickSort(arr, j+1, high);
    }


	public static int[] ReadData(String filename) {
		// TODO �Զ����ɵķ������
		int index = 0;
        BufferedReader br = null;
        try {
           // ���ļ���. ·�������Ǹ�txt�ļ�·��
           br  = new BufferedReader(new FileReader(new File(filename)));
            String str = null;
            // ���ж�ȡ
            while((str=br.readLine())!=null){
            // ��\t�ָ�
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
