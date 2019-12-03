package com.example.javaweb.music_center.util;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Jieba {

    public static HashSet<String> set;

    static {
        try {
            set = getWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    获得自定义分词
    private static HashSet<String> getWords() throws IOException{
        StringBuilder buffer = new StringBuilder();
        BufferedReader bf = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:dicts/words.txt")));

//        InputStreamReader isr = new InputStreamReader(new FileInputStream("/dicts/words.txt"), StandardCharsets.UTF_8);
//        BufferedReader bf = new BufferedReader(isr);
        String s = null;
        while((s = bf.readLine()) != null){
            //使用readLine方法，一次读一行
            buffer.append(s.trim());
        }

        String xml = buffer.toString();
        bf.close();
        String [] str = xml.split("\\s");		//以空格分割
        HashSet<String> set = new HashSet<>(Arrays.asList(str));	//将数组转换为set集合，去重
        System.out.println("Jieba: " + set);	//输出set中元素
        return set;
    }

//    分词功能
    public static List<String> splitWords(String text) throws IOException {

        List<String> words = new ArrayList<>();

        int i = text.length();
        while(0 != text.length()){
            String a = text.substring(0, i);
            if(set.contains(a)){
                words.add(a);
                System.out.println(a);
                text = text.substring(i);
                i = text.length();
            }
            else{
                i--;
            }

            if(a.length() == 1){
                System.out.println(a);
                text = text.substring(1);
                i = text.length();
            }
        }
        return words;
    }
}
