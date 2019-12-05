package com.lvmama.vo;

import java.util.Date;

public class PdfGenerateResult {
    private int id;
    private String generateTime;
    private String dataCount;
    private String pdfCount;
    private String content;
    private String updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime;
    }

    public String getDataCount() {
        return dataCount;
    }

    public void setDataCount(String dataCount) {
        this.dataCount = dataCount;
    }

    public String getPdfCount() {
        return pdfCount;
    }

    public void setPdfCount(String pdfCount) {
        this.pdfCount = pdfCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
