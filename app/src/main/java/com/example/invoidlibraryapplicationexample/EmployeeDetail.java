package com.example.invoidlibraryapplicationexample;

public class EmployeeDetail {

    private String name;
    private String mobile;
    private String email;
    private String aboutYourself;
    private String imageURL;

    public EmployeeDetail(){
    }

    public EmployeeDetail(String Name,String Mobile,String Email,String AboutYourself,String ImageURL){
        this.name=Name;
        this.mobile=Mobile;
        this.email=Email;
        this.aboutYourself=AboutYourself;
        this.imageURL=ImageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
