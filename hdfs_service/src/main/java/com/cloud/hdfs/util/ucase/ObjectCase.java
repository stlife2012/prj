package com.cloud.hdfs.util.ucase;

/**
 * @Classname ObjectCase
 * @Description TODO
 * @Author Stlife
 * @Date 2018/8/10 9:56
 * @Version 1.0
 **/
public class ObjectCase {
    public static void main(String[] args){
        U a = new U();
        U b = a;
        System.out.println(a == b);
    }
}

class U{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
