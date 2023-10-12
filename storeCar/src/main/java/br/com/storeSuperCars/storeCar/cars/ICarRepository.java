package br.com.storesupercars.storecar.cars;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRepository extends JpaRepository<CarModel, UUID> {

  List<CarModel> findByIdUser(UUID idUser);
}
