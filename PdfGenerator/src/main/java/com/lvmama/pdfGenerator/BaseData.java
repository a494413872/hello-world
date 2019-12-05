package com.lvmama.pdfGenerator;

public class BaseData {
    private String columnOne;
    private String columnTwo;
    private String columnThree;
    //第四列为生成二维码的元素
    private String columnFour;
    //第五列为展示的元素，和第四列可能一样可能不一样
    private String columnFive;

    public String getColumnOne() {
        return columnOne;
    }

    public void setColumnOne(String columnOne) {
        this.columnOne = columnOne;
    }

    public String getColumnTwo() {
        return columnTwo;
    }

    public void setColumnTwo(String columnTwo) {
        this.columnTwo = columnTwo;
    }

    public String getColumnThree() {
        return columnThree;
    }

    public void setColumnThree(String columnThree) {
        this.columnThree = columnThree;
    }

    public String getColumnFour() {
        return columnFour;
    }

    public void setColumnFour(String columnFour) {
        this.columnFour = columnFour;
    }

    public String getColumnFive() {
        return columnFive;
    }

    public void setColumnFive(String columnFive) {
        this.columnFive = columnFive;
    }
}
