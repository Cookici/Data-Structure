package _01_SparseArray;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @ProjectName: Data Structure
 * @Package: _01_稀疏数组
 * @ClassName: SparseArray
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/5/30 15:15
 */

public class SparseArray {

    public static void main(String[] args) throws IOException {

        // 创建原始的二维数组
        // 0表示没有数值
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[3][4] = 3;

        //遍历原始的二维数组
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //思路：将二维数组转化为稀疏数组
        //1.遍历原始的二维数组，得到有效数据的个数sum
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.根据sum 创建稀疏数组sparseArr int[sum+1][3]
        int sparseArr[][] = new int[sum + 1][3];


        //3.将二维数组的有效数据存入稀疏数组
        int count = 0;
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }


        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为如下形式：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }


        //稀疏数组恢复为原始的二维数组
        // 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2.在读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        writeFile(sparseArr);

    }

    public static void writeFile(int[][] array) throws IOException {

        FileOutputStream fos = new FileOutputStream("src/_01_SparseArray/SparseArray.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);

        for (int[] row : array) {
            for (int data : row) {
                bw.write(data + "\t");
            }
            bw.write("\n");
        }

        //注意关闭顺序 先开启的后关闭 后开启的先关闭
        bw.close();
        osw.close();
        fos.close();

    }

    @Test
    public void readFile() throws IOException {
        File file  = new File("src/_01_SparseArray/SparseArray.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        br.mark(( int )file.length() + 1 );

        String line = "";
        int[][] array = null;
        int i = 0;
        int j = 0;
        while ((line = br.readLine()) != null) {
            String[] row = line.split("\n");
            for (String stringLine : row) {
                String[] split = stringLine.split("\t");
                for (String data : split) {
                    j++;
                }
                i++;
            }
        }

        br.reset();

        array = new int[i][j / i];


        int k = 0;
        int l = 0;


        while ((line = br.readLine()) != null) {
            String[] row = line.split("\n");
            for (String stringLine : row) {
                String[] split = stringLine.split("\t");
                for (String data : split) {
                    l %= (j / i);
                    array[k][l] = Integer.parseInt(data);
                    l++;
                }
                k++;
            }
        }




        for (int[] row : array) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        br.close();
        isr.close();
        fis.close();


    }
}

