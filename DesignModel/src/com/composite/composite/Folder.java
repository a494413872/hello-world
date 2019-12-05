package com.composite.composite;

/**
 * Created by songjian on 9/18/2018.
 */
public class Folder extends AbstractPath{
    public Folder(String name){
        super.name=name;
    }

    @Override
    public void addPath(AbstractPath path) {
        super.pathList.add(path);
    }

    @Override
    public void printPath() {
        System.out.print(name);
        System.out.print("\\");
        for (AbstractPath abstractPath : pathList) {
            abstractPath.printPath();
        }
    }
}
