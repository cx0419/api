package Java.Remoting.Common;

import org.apache.commons.mail.HtmlEmail;

public class SendEmail_Register {
    public static boolean sendEmail(String to,String test) {
        if(to.equals("3207242935@qq.com")){
            return false;
        }
        try {
            //创建网页邮箱对象
            HtmlEmail email = new HtmlEmail();

            //基本设置
            email.setDebug(true);

            //设置为QQ邮箱作为发送主邮箱
            email.setHostName("SMTP.qq.com");
            email.setSmtpPort(587);

            //qq邮箱的验证信息
            email.setAuthentication("3207242935@qq.com", "iwrvxtsjxhdydggc");

            //设置邮件发送人
            email.setFrom("3207242935@qq.com");

            //设置邮件接收人
            email.addTo(to);

            //设置发送的内容
            email.setMsg("尊敬的用户:"+to+",您本次申请注册的的验证码为:"+test);

            //设置邮箱标题
            email.setSubject("注册申请");

            //执行邮件发送
            email.send();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 测试qq邮箱发送
     * @param
     */
    public static void main(String[] args) {
        boolean b = sendEmail("1299459723@qq.com","123");
        System.out.println("发送"+(b?"成功":"失败"));
    }
}