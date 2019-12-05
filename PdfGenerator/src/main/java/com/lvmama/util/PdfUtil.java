package com.lvmama.util;

import com.lowagie.text.pdf.BaseFont;
import org.apache.commons.io.IOUtils;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by songjian on 9/28/2018.
 */
public class PdfUtil {

    public static final String PDF_FONT_URL = "/WEB-INF/resources/econtractTemplate/";
    public static final String PDF_TEMPLATE_URL = "/WEB-INF/resources/pdfTemplate/";

    /**
     * 根据文件路径创建PDF文件
     * @param
     * @param toUrl
     */
    public static void createPdfFile(ByteArrayOutputStream baos,final String toUrl) {
        FileOutputStream fs = null;
        try {
            if (null != baos) {
                fs = new FileOutputStream(new File(toUrl));
                baos.writeTo(fs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(baos);
            IOUtils.closeQuietly(fs);
        }
    }

    /**
     * 根据文件路径创建PDF文件
     * @param
     * @param toUrl
     */
    public static void createPdfFileNIO(ByteArrayOutputStream baos,final String toUrl) {
        FileChannel fileChannel = null;
        try {
            if (null != baos) {
                fileChannel = new FileOutputStream(new File(toUrl)).getChannel();
                byte[] bytes = baos.toByteArray();
                ByteBuffer buf = ByteBuffer.wrap(bytes);
                fileChannel.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(baos);
            IOUtils.closeQuietly(fileChannel);
        }
    }

    /**
     * 根据文件路径创建PDF文件
     * @param
     * @param toUrl
     */
    public static void createPdfFileMapper(ByteArrayOutputStream baos,final String toUrl) {
        FileChannel fileChannel = null;
        try {
            if (null != baos) {
                /**
                 * model各个参数详解
                 * r 代表以只读方式打开指定文件
                 * rw 以读写方式打开指定文件
                 * rws 读写方式打开，并对内容或元数据都同步写入底层存储设备
                 * rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备
                 *
                 * **/
                fileChannel = new RandomAccessFile(toUrl,"rw").getChannel();
                byte[] bytes = baos.toByteArray();
                MappedByteBuffer mbuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,bytes.length);
                mbuffer.put(bytes);
                mbuffer.force();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(baos);
            IOUtils.closeQuietly(fileChannel);
        }
    }


    /**
     * 创建PDF文件流
     * @param
     * @param
     */
    public static ByteArrayOutputStream createPdfStream(final String line) {
        try {
            String content = line.replaceAll("&nbsp;", "");
            if(!content.startsWith("<")){
                content = content.substring(content.indexOf('<'));
            }
            content = PdfUtil.parse2Xhtml(line);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            File ttc = ResourceUtil.getResourceFile(PDF_FONT_URL+"simsun.ttc");
//            if (!ttc.exists()) {
//                ttc = ResourceUtil.getResourceFile(PDF_FONT_URL+"SIMSUN.TTC");
//            }
            File ttc = new File("D:\\LvmamaPdfGenerator\\lvMama\\template\\SIMSUN.TTC");
            String fontPath=ttc.getAbsolutePath();
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver fontResolver = renderer.getFontResolver();
            if(null!=fontPath) {
                fontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            }
            renderer.setDocumentFromString(content);
            renderer.layout();
            renderer.createPDF(baos);
            renderer.finishPDF();
            return baos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parse2Xhtml(String html) throws UnsupportedEncodingException {

        ByteArrayInputStream is = new ByteArrayInputStream(html.getBytes("UTF-8"));
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        // 实例化Tidy对象
        Tidy tidy = new Tidy();
        // 设置输入
        tidy.setInputEncoding("UTF-8");
        // 如果是true 不输出注释，警告和错误信息
        tidy.setQuiet(true);
        // 设置输出
        tidy.setOutputEncoding("UTF-8");
        // 不显示警告信息
        tidy.setShowWarnings(false);
        // 缩进适当的标签内容。
        tidy.setIndentContent(true);
        // 是否节点结束后另起一行
        tidy.setSmartIndent(true);
        // 内容缩进
        tidy.setSmartIndent(true);
        // 属性换行
        tidy.setIndentAttributes(false);
        // 是否br在一行中显示
        tidy.setBreakBeforeBR(true);
        // 输出为xhtml
        tidy.setXHTML(true);
        // 去掉没用的标签
        tidy.setMakeClean(false);
        // 一行有多长
        tidy.setWraplen(1000);
        // 清洗word2000的内容
        tidy.setWord2000(true);
        //清洗空标签
        tidy.setTrimEmptyElements(false);
        // 设置错误输出信息
//		tidy.setErrout(new PrintWriter(System.out));
        try {
            tidy.parse(is, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
        return os.toString("UTF-8");
    }
}
