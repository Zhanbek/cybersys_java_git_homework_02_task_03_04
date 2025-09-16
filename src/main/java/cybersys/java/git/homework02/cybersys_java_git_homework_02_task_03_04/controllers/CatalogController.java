package my.web.app.lesson1.controllers;

import my.web.app.lesson1.dao.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatalogController {
    private static List<Car> cars = new ArrayList<Car>();

    @GetMapping("/catalog")
    public String catalog(Model model) {
        if (cars.isEmpty()) {
            cars.add(new Car(1, "Audi", 2000, "audi.jpg", "В наличии"));
            cars.add(new Car(2, "BMW", 1500, "bmw.jpg", "В наличии"));
            cars.add(new Car(3, "VW", 1300, "vw.jpg", "Под заказ"));
            cars.add(new Car(4, "Skoda", 1200, "skoda.jpg", "В наличии"));
        }
        model.addAttribute("cars", cars);
        return "catalog_v2";
    }

    @GetMapping("/cars/{id}")
    public String carDetail(@PathVariable int id, Model model) {
        Car car = cars.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
        if (car != null) {
            model.addAttribute("car", car);
        }
        return "carDetail";
    }
}
