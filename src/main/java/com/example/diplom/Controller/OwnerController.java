package com.example.diplom.Controller;



import com.example.diplom.entity.Car;
import com.example.diplom.entity.Order;
import com.example.diplom.entity.Owner;
import com.example.diplom.service.CarService;
import com.example.diplom.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Owner> owners = ownerService.findAll();
        model.addAttribute("owners", owners);
        return "owner/owner-list";
    }

    @GetMapping("new")
    public String getAddForm(Model model) {
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "owner/add-owner-form";
    }

    @PostMapping("new")
    public String postAddForm(Owner owner, RedirectAttributes redirectAttributes) {
        Optional<Owner> saved = ownerService.save(owner);
        if (saved.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Владелец " + saved.get() + " успешно добавлен");
        }
        return "redirect:/owner";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Owner> updated = ownerService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("owner", updated.get());
            return "owner/update-owner-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Владелец с id " + id + " не найден");
            return "redirect:/owner";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Owner owner, RedirectAttributes redirectAttributes) {
        Optional<Owner> updated = ownerService.update(owner);
        if (updated.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Владелец " + updated.get() + " успешно обновлен");
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Не получилось выполнить обновление"
            );
        }
        return "redirect:/owner";
    }

    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Owner> owner = ownerService.findById(id);
        if (owner.isPresent()) {
            Car car = new Car();
            car.setOwner(owner.get());
            List<Car> cars = owner.get().getCars()
                    .stream()
                    .toList();
            model.addAttribute("owner", owner.get());
            model.addAttribute("car", car);
            model.addAttribute("cars", cars);
            return "owner/owner-details";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Владелец с id " + id + " не найден");
            return "redirect:/owner";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Owner> deleted = ownerService.deleteById(id);
        if (deleted.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Владелец " + deleted.get() + " успешно удален");
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Владелец с id " + id + " не найден"
            );
        }
        return "redirect:/owner";
    }
}
