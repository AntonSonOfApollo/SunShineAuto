package com.example.diplom.Controller;


import com.example.diplom.entity.Car;
import com.example.diplom.entity.Order;
import com.example.diplom.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order/order-list";
    }

    @GetMapping("new")
    public String getAddForm(Model model) {
        Order order = new Order(); // объект для заполнения в форме
        model.addAttribute("order", order);
        return "order/add-order-form";
    }

    @PostMapping("new")
    public String postAddForm(Order order, RedirectAttributes redirectAttributes) {
        Optional<Order> saved = orderService.save(order);
        if (saved.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Заказ " + saved.get() + " успешно добавлен");
        }
        return "redirect:/order";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Order> updated = orderService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("order", updated.get());
            return "order/update-order-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Заказ с id " + id + " не найден");
            return "redirect:/order";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Order order, RedirectAttributes redirectAttributes) {
        Optional<Order> updated = orderService.update(order);
        if (updated.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Заказ " + updated.get() + " успешно обновлен");
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Не получилось выполнить обновление"
            );
        }
        return "redirect:/car";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Order> deleted = orderService.deleteById(id);
        if (deleted.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Заказ " + deleted.get() + " успешно удален");
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Заказ с id " + id + " не найден"
            );
        }
        return "redirect:/order";
    }
}
