package com.dx.cn.calculator;

/**
 * @Author w y y t c f j
 * @Description 输出一个数字的32位
 * @Date 2023/9/20
 */
public class BinaryConverter {
    public static String intToBinaryString(int number) {
        StringBuilder binaryString = new StringBuilder(32);

        for (int i = 31; i >= 0; i--) {
            int bit = (number >> i) & 1;
            binaryString.append(bit);
        }

        return binaryString.toString();
    }

    public static void main(String[] args) {
        int number = 6; // 要转换的整数
        String binary = intToBinaryString(number);
        System.out.println("整数：" + number + "\n32位二进制表示形式是：" + binary);
    }
}
