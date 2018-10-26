package lanaDrahrepus.controllers;

import lanaDrahrepus.model.Product;
import lanaDrahrepus.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductsController {

    @Autowired
    ProductsService products;

    @GetMapping("/products")
    public String getWorkSpace(Model model) {
        model.addAttribute("products", products.list());
        return "workspace";
    }

    @GetMapping("/products/edit/{code}")
    public String getEditPage(@PathVariable int code, Model model) {
        Product product = products.find(code);
        model.addAttribute("product", product);
        return "edit";
    }

    @GetMapping("/products/view/{code}")
    public String getViewPage(@PathVariable int code, Model model) {
        Product product = products.find(code);
        model.addAttribute("product", product);
        return "view";
    }

    @PostMapping("/products/edit/{code}")
    public String edit(@RequestParam
                               String name,
                       String price,
                       String origin,
                       String picture,
                       Model model,
                       RedirectAttributes redirect,
                       @PathVariable int code) {
        Product oldProduct = products.find(code);
        try {
            Double.parseDouble(price);
        } catch (Throwable t) {
            String error ="giá không hợp lệ";
            model.addAttribute("error", error);
            model.addAttribute("product", oldProduct);
            return "redirect:/products/edit/"+code;
        }
        Product product = new Product(code, name, Double.parseDouble(price), origin, picture);
        if (products.update(code, product)) {
            return "redirect:/products";
        }
        return "error404";
    }
}
