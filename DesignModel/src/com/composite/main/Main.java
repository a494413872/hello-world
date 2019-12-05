package com.composite.main;

import com.composite.composite.AbstractPath;
import com.composite.composite.File;
import com.composite.composite.Folder;

/**
 * Created by songjian on 9/18/2018.
 */
public class Main {
    public static void main(String[] args) {
        AbstractPath folder1 = new Folder("folder1");
        AbstractPath file1 = new File("file1.txt");
        AbstractPath folder2 = new Folder("folder2");
        folder1.addPath(file1);
        folder1.addPath(folder2);

        AbstractPath file2 = new File("file2.png");
        folder2.addPath(file2);

        AbstractPath foder3 = new Folder("foder3");
        folder2.addPath(foder3);

        AbstractPath file3 = new File("file3.rmvb");
        foder3.addPath(file3);

        folder1.printPath();
        System.out.println("***********");

        folder2.printPath();
        System.out.println("***********");

        file1.printPath();

    }
}
