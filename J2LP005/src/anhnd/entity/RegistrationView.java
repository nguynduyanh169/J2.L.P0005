/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.entity;

import java.util.Vector;

/**
 *
 * @author anhnd
 */
public class RegistrationView {

    private String registrationId;
    private String fullname;
    private String phone;
    private String address;
    private Integer age;
    private String gender;

    public RegistrationView() {
    }

    public RegistrationView(String registrationId, String fullname, String phone, String address, Integer age, String gender) {
        this.registrationId = registrationId;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.gender = gender;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Vector toVector(){
        Vector vector = new Vector();
        vector.add(this.registrationId);
        vector.add(this.fullname);
        vector.add(this.age);
        vector.add(this.gender);
        vector.add(this.phone);
        vector.add(this.address);
        return vector;
    }
}
