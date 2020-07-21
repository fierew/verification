package com.weigh.verification.utils;

import com.deepoove.poi.XWPFTemplate;
import com.power.common.util.UUIDUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author xuyang
 */
public class WordUtil {
    /**
     * 填充Word
     * @param templateFileName 模板文件路径
     * @param data 填充的数据
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
        if(!dir.getParentFile().exists()){
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
}
