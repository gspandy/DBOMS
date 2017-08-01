package com.tydic.dbs.commons.encrypt;

public class Base64AndMd5 {

    public static void main(String[] args) {
        try {
            String s = base64AndMD5("111111");
            System.out.println(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String base64AndMD5(String inputStr) throws Exception {
        String result = null;
        byte[] default_output = new Base64It().encrypt(ConvertUtil.str2Byte(inputStr, "utf-8"));

        String after_base64 = ConvertUtil.byte2Str(default_output, "utf-8");

        default_output = new MD5It().encrypt(ConvertUtil.str2Byte(after_base64, "utf-8"));

        result = ConvertUtil.bytesToHexString(default_output);
        return result;
    }

}
