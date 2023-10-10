package br.com.storesupercars.storecar.user;

import lombok.Data;

@Data
public class UserModel {

  private String name;
  private String email;
  private Number age;
  private String password;

  // private Date birthDay;
  // private Number phone;
  // private String city;

  // Construtor
  // public UserModel(String name, String email, Number age, String password) {
  // this.name = name;
  // this.email = email;
  // this.age = age;
  // this.password = password;
  // }

  // @Override
  // public String toString() {
  // return "UserModel [name=" + name + ", email=" + email + ", age=" + age + ",
  // password=" + password + "]";
  // }

}
