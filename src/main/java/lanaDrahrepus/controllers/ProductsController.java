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
    public String edit(@RequestParam Model model,
                       Object editor,
                       @PathVariable
                               int code) {
        Product product = new Product(code, editor.name,editor.price,editor.origin);
        products.update(code,)

    }
}
