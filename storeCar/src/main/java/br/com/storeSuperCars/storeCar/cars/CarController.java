package br.com.storesupercars.storecar.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

  @Autowired
  private ICarRepository carRepository;

  @PostMapping("/")
  public CarModel create(@RequestBody CarModel carModel) {

    var car = this.carRepository.save(carModel);

    return car;
  }

}
