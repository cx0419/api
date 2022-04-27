package Java.Remoting.Common;

import java.util.Random;

public class Creat_String {
    //生成一个长度为lenth的数字串
    public static String getNum(int length) {

        Random random = new Random();

        StringBuffer valSb = new StringBuffer();

        String charStr = "0123456789";

        int charLength = charStr.length();



        for (int i = 0; i < length; i++) {

            int index = random.nextInt(charLength);

            valSb.append(charStr.charAt(index));

        }

        return valSb.toString();
    }
    //生成一个长度为lenth的字符串(有数字和英文)
    public static String getCharAndNumr(int length) {

        Random random = new Random();

        StringBuffer valSb = new StringBuffer();

        String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";

        int charLength = charStr.length();



        for (int i = 0; i < length; i++) {

            int index = random.nextInt(charLength);

            valSb.append(charStr.charAt(index));

        }

        return valSb.toString();

    }
}
