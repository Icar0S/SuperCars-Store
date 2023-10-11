package br.com.storesupercars.storecar.cars;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRepository extends JpaRepository<CarModel, UUID> {

}
