package com.example.diplom.Controller;


import com.example.diplom.entity.Order;
import com.example.diplom.entity.Owner;
import com.example.diplom.service.CarService;
import com.example.diplom.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.example.diplom.entity.Car;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("car")
public class CarController {

private final CarService carService;
    private final OwnerService ownerService;

public CarController(CarService carService, OwnerService ownerService) {
    this.ownerService = ownerService;
    this.carService = carService;
}

    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Car> cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "car/car-list";
    }

    @GetMapping("new")
    public String getAddForm(Model model) {
        Car car = new Car(); // объект для заполнения в форме
        model.addAttribute("car", car);
        return "car/add-car-form";
    }

    @PostMapping("new")
    public String postAddForm(Car car, RedirectAttributes redirectAttributes) {
        Optional<Car> saved = carService.save(car);
        if (saved.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Автомобиль " + saved.get() + " успешно добавлен");
        }
        return "redirect:/car";
    }

    @GetMapping("new/{id}")
    public String getAddForm(@PathVariable Integer id, Model model) {
        Car car = new Car();
        Optional<Owner> owner = ownerService.findById(id);
        model.addAttribute("car", car);
        model.addAttribute("owners", new Owner[]{owner.get()});
        return "review/add-car-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Car> deleted = carService.deleteById(id);
        if (deleted.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Машина " + deleted.get() + " успешно удалена");
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Машина с id " + id + " не найдена"
            );
        }
        return "redirect:/car";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Car> updated = carService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("car", updated.get());
            return "car/update-car-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Машина с id " + id + " не найдена");
            return "redirect:/car";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Car car, RedirectAttributes redirectAttributes) {
        Optional<Car> updated = carService.update(car);
        if (updated.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Машина " + updated.get() + " успешно обновлена");
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Не получилось выполнить обновление"
            );
        }
        return "redirect:/car";
    }

    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Car> car = carService.findById(id);
        if (car.isPresent()) {
            Order order = new Order();
            order.setCar(car.get());
            List<Order> orders = car.get().getOrders()
                    .stream()
                    .toList();
            model.addAttribute("car", car.get());
            model.addAttribute("order", order);
            model.addAttribute("orders", orders);
            return "car/car-details";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Автомобиль с id " + id + " не найден");
            return "redirect:/car";
        }
    }
}
