package br.com.storesupercars.storecar.cars;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/*
   * ID
   * usuario (dono cadastrando o carro)
   * marca
   * modelo
   * cidade loclizado
   * ano
   * novo / usado
   * placa
   * data de inicio e de venda
   * 
   */

@Data
@Entity(name = "tb_cars")
public class CarModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(name = "marca", length = 50)
  private String brand;

  @Column(name = "modelo", length = 50)
  private String model;

  @Column(name = "cidade", length = 50)
  private String city;

  @Column(name = "ano")
  private Integer year;

  @Column(name = "placa", length = 7)
  private String plate;

  private Boolean zeroKm;

  private UUID idUser;

  @Column(name = "data_inicio")
  private LocalDateTime startAt;
  @Column(name = "data_venda")
  private LocalDateTime soldAt;
  @CreationTimestamp
  private LocalDateTime cretedAt;

}
