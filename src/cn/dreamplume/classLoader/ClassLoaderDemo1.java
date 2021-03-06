package cn.dreamplume.classLoader;

import java.io.*;
import java.net.URL;

/**
 * @Classname ClassLoaderDemo1
 * @Description TODO
 * @Date 2021/3/6 19:39
 * @Created by 翊
 */
public class ClassLoaderDemo1 {
    public static void main(String[] args) throws IOException {
        Class clazz = ClassLoaderDemo1.class;  // 获得 ClassLoaderDemo1 的字节码对象
        ClassLoader classLoader = clazz.getClassLoader();  // 获得类加载器对象
        // 使用类加载器中的getResource()方法获得 classes(src) 路径下的任何资源（路径是相对于 src）
        URL path = classLoader.getResource("cn/dreamplume/classLoader/Demo1");
        BufferedReader reader = new BufferedReader(new InputStreamReader(path.openStream(),"utf-8"));
        String content = "";
        while ((content = reader.readLine()) != null) {
            System.out.println(content);
        }
        // 使用类加载器中的 getResourceAsStream() 方法可以直接获得对应 url 资源下的输入流
        InputStream input = classLoader.getResourceAsStream("cn/dreamplume/classLoader/Demo1");
        byte[] rush = new byte[1024];
        int length;
        while ((length = input.read(rush)) != -1) {
            String content2 = new String(rush,0,length);
            System.out.println(content2);
        }
    }
}
