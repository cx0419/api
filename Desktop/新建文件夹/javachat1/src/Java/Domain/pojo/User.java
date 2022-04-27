package Java.Domain.pojo;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int Id;
    private String Name;
    private String AccountNumber;
    private String AccountPassword;
    private String Age;
    private String Gender;
    private Date Birthday;
    private Blob Headportrait;//头像
    private String PersonalSignature;//个性签名
    private String School;
    private String Email;
    private String Message;//消息类型
    public User (){
        Message = null;
    }
    public User(String accountNumber, String accountPassword) {
        AccountNumber = accountNumber;
        AccountPassword = accountPassword;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getAccountPassword() {
        return AccountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        AccountPassword = accountPassword;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public Blob getHeadportrait() {
        return Headportrait;
    }

    public void setHeadportrait(Blob headportrait) {
        Headportrait = headportrait;
    }

    public String getPersonalSignature() {
        return PersonalSignature;
    }

    public void setPersonalSignature(String personalSignature) {
        PersonalSignature = personalSignature;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

