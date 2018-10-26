package lanaDrahrepus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    @GetMapping("/")
    public void toLogin(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/login");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String accountHandle(@RequestParam
                                String password,
                                String username,
                                Model model,
                                HttpServletResponse resp) throws IOException {
        boolean isLoginable = (username.equals("toandz") && password.equals("123456"));

        if (isLoginable) {
            resp.sendRedirect("/products");
        }

        model.addAttribute("error", "wrong username or password !");
        return "login";
    }
}
