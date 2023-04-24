package io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 解压zip文件到/tmp目录
 */
public class ZipFileTest {
    public static void main(String[] args) {
        try {
            ZipInputStream input = new ZipInputStream(new FileInputStream("/Users/xushengbin/Downloads/简历模板.zip"));
            ZipEntry entry;
            byte[] buffer = new byte[1024];
            while ((entry = input.getNextEntry()) != null) {
                File newFile = new File("/tmp/" + entry.getName());
                if (!entry.isDirectory()) {
                    new File(newFile.getParent()).mkdirs();
                    FileOutputStream output = new FileOutputStream("/tmp/" + entry.getName());
                    System.out.println(entry.getName());
                    // 读取当前entry的内容
                    while (input.read(buffer) > 0) {
                        output.write(buffer);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
