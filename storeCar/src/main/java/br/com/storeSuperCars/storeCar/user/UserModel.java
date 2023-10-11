package br.com.storesupercars.storecar.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(name = "nome", unique = true)
  private String name;
  private String email;
  @Column(name = "idade")
  private Integer age;
  @Column(name = "senha")
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;

  // private Date birthDay;
  // @Column(name = "telefone")
  // private Number phone;
  // @Column(name = "cidade")
  // private String city;

  // Construtor

  // @Override
  // public String toString() {
  // return "UserModel [name=" + name + ", email=" + email + ", age=" + age + ",
  // password=" + password + "]";
  // }

}
