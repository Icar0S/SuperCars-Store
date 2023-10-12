package br.com.storesupercars.storecar.cars;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
  public CarModel create(@RequestBody CarModel carModel, HttpServletRequest request) {

    var idUser = request.getAttribute("idUser");
    carModel.setIdUser((UUID) idUser);
    var car = this.carRepository.save(carModel);

    return car;
  }

}
