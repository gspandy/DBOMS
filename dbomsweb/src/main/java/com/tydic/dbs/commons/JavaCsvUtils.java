package com.tydic.dbs.commons;

import java.beans.PropertyDescriptor;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.csvreader.CsvWriter;
import com.tydic.commons.utils.DataTransUtils;
import com.tydic.commons.utils.DateUtils;

public class JavaCsvUtils {

    private static final Logger logger = Logger.getLogger(JavaCsvUtils.class);

    /**
     * 默认CSV文件编码
     */
    public static final String DEFAULT_FILENAME_ENCODE = "UTF-8";

    /**
     * 默认CSV文件内容编码
     */
    public static final String DEFAULT_CONTENT_ENCODE = "UTF-8";

    /**
     * CSV内容分隔符
     */
    private static final char CSV_CONTENT_SPLIT = ',';

    /**
     * 默认CSV文件后缀
     */
    public static final String DEFAULT_FILE_SUFFIX = "_" + DateUtils.getYmdhms() + ".csv";

    /**
     * 写入csv（参数header传null时,不写入列头标题信息）
     * @param buff
     *            输出流
     * @param header
     *            数据标头
     * @param propertys
     *            参数列
     * @param dataList
     *            数据列表
     * @return Result 结果对象
     */
    public static Result writerCsv(BufferedOutputStream buff, String[] header, int columnLen, List<Object[]> list) {

        Result result = new Result();
        if (buff == null) {
            return result;
        }

        CsvWriter writer = null;
        int dataSize = 0;
        try {
            writer = new CsvWriter(buff, CSV_CONTENT_SPLIT, Charset.forName(DEFAULT_CONTENT_ENCODE));
            if (header != null) { // 写文件头
                writer.writeRecord(header);
            }
            if (CollectionUtils.isNotEmpty(list)) { // 写文件内容
                dataSize = list.size();
                for (Object[] objects : list) {
                    String[] data = new String[columnLen];
                    for (int j = 0; j < columnLen; j++) {
                        if (null == objects[j]) {
                            data[j] = "";
                        }
                        else {
                            data[j] = String.valueOf(objects[j]);
                        }
                    }
                    System.out.println(data);
                    writer.writeRecord(data);
                    result.successCount++;
                }
            }
        } catch (IOException e) {
            logger.error("写csv文件出错!", e);
        } finally {
            if (writer != null)
                writer.close();
            result.failCount = dataSize - result.successCount;
        }
        return result;
    }

    /**
     * 写入csv（参数header传null时,不写入列头标题信息）
     * @param buff
     *            输出流
     * @param header
     *            数据标头
     * @param propertys
     *            参数列
     * @param dataList
     *            数据列表
     * @return Result 结果对象
     */
    public static Result writerCsv(BufferedOutputStream buff, String[] header, String[] propertys, List<?> dataList) {

        Result result = new Result();
        if (buff == null || propertys == null || propertys.length < 1) {
            return result;
        }

        CsvWriter writer = null;
        int dataSize = 0;
        try {
            writer = new CsvWriter(buff, CSV_CONTENT_SPLIT, Charset.forName("GBK"));
            if (header != null) { // 写文件头
                writer.writeRecord(header);
            }
            if (CollectionUtils.isNotEmpty(dataList)) { // 写文件内容
                dataSize = dataList.size();
                for (int i = 0; i < dataSize; i++) {
                    String[] data = new String[propertys.length];
                    for (int j = 0; j < propertys.length; j++) {
                        data[j] = getMethod(dataList.get(i), propertys[j]);
                    }
                    writer.writeRecord(data);
                    result.successCount++;
                }
            }
        } catch (IOException e) {
            logger.error("写csv文件出错!", e);
        } finally {
            if (writer != null)
                writer.close();
            result.failCount = dataSize - result.successCount;
        }
        return result;
    }

    /**
     * 获取对象属性的get方法的返回值
     * @param obj
     *            实体对象
     * @param filed
     *            属性名称
     */
    public static String getMethod(Object obj, String filed) {

        if (obj == null || StringUtils.isEmpty(filed)) {
            return "";
        }

        Object column = "";
        Class<?> methodClass = null;
        try {
            Class<?> clazz = obj.getClass();
            PropertyDescriptor pd = new PropertyDescriptor(filed, clazz);
            Method getMethod = pd.getReadMethod(); // 获得get方法

            methodClass = getMethod.getReturnType();

            column = getMethod.invoke(obj); // 执行get方法返回一个Object
        } catch (Exception e) {
            logger.error("获取对象属性值出错!", e);
        }

        if (methodClass == Date.class) {
            column = DataTransUtils.date2Str((Date) column, "yyyy-MM-dd HH:mm:ss");
        }

        return column == null ? "" : column.toString();
    }

    /**
     * 转换文件名
     * @param fileName
     *            文件名
     * @param isUrlEncode
     *            是否进行URL转码
     * @return 转换后文件名
     */
    public static String convertFileName(String fileName, boolean isUrlEncode) {
        if (isUrlEncode) {
            try {
                fileName = URLEncoder.encode(fileName, DEFAULT_FILENAME_ENCODE);
            } catch (UnsupportedEncodingException e) {
                logger.error("CSV文件名进行URL转码失败!", e);
            }
        }
        return fileName + DEFAULT_FILE_SUFFIX;
    }

    /**
     * @description CSV导出结果
     */
    public static class Result {

        /**
         * successCount
         */
        public int successCount;

        /**
         * failCount
         */
        public int failCount;

    }

}