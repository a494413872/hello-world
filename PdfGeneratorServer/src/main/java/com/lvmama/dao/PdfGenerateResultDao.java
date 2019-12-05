package com.lvmama.dao;

import com.lvmama.vo.PdfGenerateResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PdfGenerateResultDao {


    public List<PdfGenerateResult> listResult();

    public int logResult(PdfGenerateResult pdfGenerateResult);
}
