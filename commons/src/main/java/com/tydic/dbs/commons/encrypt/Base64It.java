/**
 * 无密钥Base64实现类ܡ�������
 * 
 * @author dawn
 */

package com.tydic.dbs.commons.encrypt;

public final class Base64It {

    /**
     * 解密方法
     */
    public byte[] decrypt(byte[] bInputArr) throws Exception {
        char[] cInputArr = new char[bInputArr.length];
        for (int loop = 0; loop < bInputArr.length; loop++) {
            cInputArr[loop] = (char) bInputArr[loop];
        }
        return Base64.decode(cInputArr);
    }

    /**
     * 加密方法
     */
    public byte[] encrypt(byte[] bInputArr) throws Exception {
        char[] cOutputArr = Base64.encode(bInputArr);
        String temp = new String(cOutputArr);
        return temp.getBytes();
    }

}
