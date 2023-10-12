package br.com.storesupercars.storecar.cars;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cars")
public class CarController {

  @Autowired
  private ICarRepository carRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody CarModel carModel, HttpServletRequest request) {

    var idUser = request.getAttribute("idUser");
    carModel.setIdUser((UUID) idUser);

    var currentDate = LocalDateTime.now();
    if (currentDate.isAfter(carModel.getStartAt()) || currentDate.isAfter(carModel.getSoldAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Data de cadastro / data de venda deve ser maior que a data atual.");
    }

    if (carModel.getStartAt().isAfter(carModel.getSoldAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Data de cadastro deve ser menor que a data de venda.");
    }

    var car = this.carRepository.save(carModel);
    return ResponseEntity.status(HttpStatus.OK).body(car);
  }

}
