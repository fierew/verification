package com.weigh.verification.utils;

import com.deepoove.poi.XWPFTemplate;
import com.power.common.util.UUIDUtil;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

/**
 * @author xuyang
 */
@Component
public class WordUtil {
    /**
     * 填充Word
     *
     * @param templateFileName 模板文件路径
     * @param data             填充的数据
     * @return 临时文件路径
     * @throws IOException 错误
     */
    public String fill(String templateFileName, Map<String, Object> data) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile(templateFileName)
                .render(data);

        // 临时文件目录
        String destDirName = "public/word/";
        // 临时文件名
        String fileName = UUIDUtil.getUuid();

        File dir = new File(destDirName);
        // 检查是否存在路径目录
        if (!dir.getParentFile().exists()) {
            // 新建目录
            dir.getParentFile().mkdirs();
        }

        String tempFilePath = destDirName + fileName + ".docx";
        // 文件流
        FileOutputStream out = new FileOutputStream(tempFilePath);
        template.write(out);
        out.flush();
        out.close();
        template.close();

        return tempFilePath;
    }

    public String getText(String fileName) {
        try {
            InputStream is = new FileInputStream(fileName);
            XWPFDocument doc = new XWPFDocument(is);

            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            String text = extractor.getText();
            // System.out.println(text);
            POIXMLProperties.CoreProperties coreProps = extractor.getCoreProperties();

            // 分类
            // System.out.println(coreProps.getCategory());

            // 创建者
            // System.out.println(coreProps.getCreator());

            // 创建时间
            // System.out.println(coreProps.getCreated());

            // 标题
            // System.out.println(coreProps.getTitle());

            // 关闭输入流
            is.close();

            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
