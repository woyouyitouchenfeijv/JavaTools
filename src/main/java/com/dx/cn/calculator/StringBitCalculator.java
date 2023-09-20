package com.dx.cn.calculator;

import java.nio.charset.StandardCharsets;

/**
* @Author w y y t c f j
* @Description 计算一个字符串在 UTF-8 编码下占用的位数
* @Date  2023/9/20
*/
public class StringBitCalculator {

    public static int calculateUTF8Bits(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        return bytes.length * 8; // 字节长度乘以 8 得到位数
    }
    public static int calculateUTF16Bits(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_16);//不停去换就好
        return bytes.length * 8; // 字节长度乘以 8 得到位数
    }

    public static void main(String[] args) {
        String text = "7574E7EC2A7E40B5EFBB049F0B9C22C179E2820CA851A40D9D9649EF69484810"; // 要计算的字符串
        int bits = calculateUTF16Bits(text);
        System.out.println("字符串在UTF-8编码下占用的位数：" + bits + " 位");
    }
}
