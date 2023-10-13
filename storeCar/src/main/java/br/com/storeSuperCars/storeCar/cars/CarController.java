package br.com.storesupercars.storecar.cars;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.storesupercars.storecar.utils.Utils;
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

  @GetMapping("/")
  public List<CarModel> list(HttpServletRequest request) {

    var idUser = request.getAttribute("idUser");
    var cars = this.carRepository.findByIdUser((UUID) idUser);

    return cars;
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody CarModel carModel, HttpServletRequest request, @PathVariable UUID id) {

    var car = this.carRepository.findById(id).orElse(null);

    if (car == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Carro não encontrado");
    }

    var idUser = request.getAttribute("idUser");

    if (!car.getIdUser().equals(idUser)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Usuário não tem permissão para editar esse carro.");
    }

    Utils.copyNonNullProperties(carModel, car);
    var carUpdated = this.carRepository.save(car);
    return ResponseEntity.ok().body(this.carRepository.save(carUpdated));
  }

}
