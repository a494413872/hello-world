package com.composite.composite;

/**
 * Created by songjian on 9/18/2018.
 */
public class File extends AbstractPath {

    public File(String name){
        super.name=name;
    }


    @Override
    public void addPath(AbstractPath path) {
        System.out.println("do not support");
    }

    @Override
    public void printPath() {
        System.out.println(name);
    }
}
