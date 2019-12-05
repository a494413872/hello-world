package com.hello.spring.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileTypeFilter implements FilenameFilter {
    // type为需要过滤的条件，比如如果type=".jpg"，则只能返回后缀为jpg的文件
    private String type;

    public FileTypeFilter(String type) {
        this.type = type;
    }

    @Override
    // 返回true的文件则合格
    public boolean accept(File dir, String name) {
        // 这里如果需要使用文件的功能，则需要先进行封装
        File file = new File(dir,name);

        return name.endsWith(type) && file.isFile();
    }

}
