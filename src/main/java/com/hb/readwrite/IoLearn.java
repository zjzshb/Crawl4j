package com.hb.readwrite;

import java.io.*;
import java.nio.Buffer;

public class IoLearn {
    public static void main(String[] args) {
//        testCharRead();
        testCharRead2();
    }

    //字符流
    private static void testCharRead2() {
        //            writeFromFileByChar();
//            readFromFileByChar();
        try {
//            readFromFileByCharP();
            writeFromFileByCharP();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///    字符流写(完整
    private static void writeFromFileByCharP() throws IOException {
        File f = new File("bcd.txt");
        Writer writer = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(writer);

        StringBuffer sb = new StringBuffer("王德发");
        bw.write(sb.toString());

        bw.close();
        writer.close();
    }

    //    字符流读(完整
    private static void readFromFileByCharP() throws IOException {
        StringBuffer sb = new StringBuffer();

         /*------读(完整-------------------------------------------------------*/
        Reader reader = new FileReader("bcd.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str + "/n");
            System.out.println(str);
        }
        bufferedReader.close();
        reader.close();
    }


    //    字符流读
    private static void readFromFileByChar() throws IOException {
        File file = new File("bcd.txt");
        Reader reader = new FileReader(file);

        char[] cs = new char[1024];
        reader.read(cs, 0, (int) file.length());


        System.out.println(cs);
        reader.close();
    }

    //字符流写
    private static void writeFromFileByChar() throws IOException {
        File file = new File("bcd.txt");
        Writer writer = new FileWriter(file);

        Writer writer2 = new FileWriter(file, true);//改为追加

        String str = "今天天气好";
        writer.write(str);

        writer.close();
    }

    //字节流读取
    private static void testCharRead() {
        try {
//            IoLearn.readFromFileByByte();
            IoLearn.writeFromFileByByte();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字节流的写
    private static void writeFromFileByByte() throws IOException {
 /*------1-------------------------------------------------------*/

        File file = new File("abc.txt");
        OutputStream outputStream = null;
        if (!file.exists()) {
            file.createNewFile();
        }
/*-------2覆盖------------------------------------------------------*/

        outputStream = new FileOutputStream(file);

        String str = "hello Java, 你好世界";
        byte[] bs = str.getBytes();

        outputStream.write(bs);
/*-------2追加------------------------------------------------------*/
        OutputStream outToFileEnd = null;
        outToFileEnd = new FileOutputStream(file, true);
        str = "追加信息，阿里里";
        bs = str.getBytes();
        outToFileEnd.write(bs);

/*-------3------------------------------------------------------*/

        outputStream.close();
    }

    //字节流的读
    private static void readFromFileByByte() throws IOException {
 /*-------------------------------------------------------------*/

        File file = new File("abc.txt");
        File file2 = new File("d" + File.separator + "abc.txt");//文件分割符
        if (!file.exists()) {//不存在则创建
            file.createNewFile();
        }
        if (!file2.exists()) {//不存在则创建
            file2.createNewFile();
        }
/*-------------------------------------------------------------*/
        byte[] bs = new byte[1024];//每次读取1024个字节
        int len = 0;
        InputStream inputStream = new FileInputStream(file);
        while ((len = inputStream.read(bs)) != -1) {
            System.out.println(new String(bs, 0, len));
        }
/*-------------------------------------------------------------*/

        inputStream.close();

/*-------------------------------------------------------------*/

    }
}
