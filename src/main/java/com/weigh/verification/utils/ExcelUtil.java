package com.weigh.verification.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.power.common.util.UUIDUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author xuyang
 */
public class ExcelUtil {
    /**
     * 填充Excel
     * @param templateFileName 模板文件路径
     * @param data 填充的数据
     * @return 临时文件路径
     * @throws IOException 错误
     */
    public String fill(String templateFileName, Map<String, Object> data) throws IOException {

        // 临时文件目录
        String destDirName = "public/excel/";
        // 临时文件名
        String fileName = UUIDUtil.getUuid();

        File dir = new File(destDirName);
        // 检查是否存在路径目录
        if (!dir.getParentFile().exists()) {
            // 新建目录
            dir.getParentFile().mkdirs();
        }

        String tempFilePath = destDirName + fileName + ".xlsx";
        ExcelWriter excelWriter = EasyExcel.write(tempFilePath).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();

        // 写入流
        excelWriter.fill(data, writeSheet);

        // 关闭流
        excelWriter.finish();

        return tempFilePath;
    }
}
