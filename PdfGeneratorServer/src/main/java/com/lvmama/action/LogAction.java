package com.lvmama.action;

import com.lvmama.dao.PdfGenerateResultDao;
import com.lvmama.vo.PdfGenerateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LogAction {

    @Autowired
    PdfGenerateResultDao pdfGenerateResultDao;

    @RequestMapping(value="/logResult",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String doLog(@RequestBody PdfGenerateResult pdfGenerateResult){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pdfGenerateResult.setUpdateTime(sdf.format(new Date()));
            pdfGenerateResultDao.logResult(pdfGenerateResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping(value="/listResult",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String doLog(Model model){
        model.addAttribute("vos",pdfGenerateResultDao.listResult());
        return "listResult";
    }
}
