package com.jack.app.mysql.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: JackWu
 * @create: 2018-08-06 13:50
 **/
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;

    @Column(name = "pass_word",nullable = false)
    private String passWord;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(nullable = true,length = 65535,columnDefinition="Text")
    private String introduction;

    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Column(name = "last_modify_time",nullable = false)
    private Date lastModifyTime;

    @Column(name = "out_date")
    private Date outDate;

    @Column(name = "validata_code")
    private String validataCode;

    @Column(name = "background_picture")
    private String backgroundPicture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getValidataCode() {
        return validataCode;
    }

    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", introduction='" + introduction + '\'' +
                ", createTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                ", outDate=" + outDate +
                ", validataCode='" + validataCode + '\'' +
                ", backgroundPicture='" + backgroundPicture + '\'' +
                '}';
    }
}
