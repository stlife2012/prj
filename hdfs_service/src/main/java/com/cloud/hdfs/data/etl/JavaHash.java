package com.cloud.hdfs.data.etl;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * @Classname JavaHash
 * @Description TODO
 * @Author Stlife
 * @Date 2018/7/28 17:51
 * @Version 1.0
 **/
public class JavaHash {
    public static void main(String args[]) {
//        String str = "abcdrfghijklmnopq";
//        String[] split = str.split("");
//        StringBuffer b = new StringBuffer();
//        for(String v:split){
//            b.append(v);
//            System.out.println(b.toString().hashCode());
//        }
//        System.out.println(Integer.valueOf(12).hashCode());
//        String a = "Aa";
//        String a1 = "BB";
//        System.out.println(a.hashCode());
//        System.out.println(a1.hashCode());
//        System.out.println(a.equals(a1));

//        ByteBuffer b = ByteBuffer.allocateDirect(500);
        directMem();
    }

    public static void directMem() {
        /*
         * Unsafe的构造函数是私有的，不能通过new来获得实例。
         *
         *  通过反射来获取
         */
        Unsafe unsafe = null;
        Field field = null;
        try {
            field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            /*
             * private static final Unsafe theUnsafe = new Unsafe();
             *
             * 因为field的修饰符为 private static final,
             * 需要将setAccessible设置成true，否则会报java.lang.IllegalAccessException
             */
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long oneHundred = 100;
        byte size = 1;
//        String data = "Hello";
        /*
         * 调用allocateMemory分配内存
         */
        long memoryAddress = unsafe.allocateMemory(size);

        /*
         * 将100写入到内存中
         */
//        unsafe.putAddress(memoryAddress, oneHundred);
//        unsafe.putChar(memoryAddress,data.length(),data.ch);
        /*
         * 内存中读取数据
         */
        long readValue = unsafe.getAddress(memoryAddress);
        System.out.println("Val : " + readValue);
//        System.out.println("Val : " + unsafe.getChar(memoryAddress,data.length()));
    }
}
