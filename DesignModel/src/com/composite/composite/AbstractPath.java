package com.composite.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songjian on 9/18/2018.
 */
public abstract class AbstractPath {

    String name;

    List<AbstractPath> pathList = new ArrayList<AbstractPath>();

    public abstract void addPath(AbstractPath path);

    public abstract void printPath();

}
