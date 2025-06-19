package beshkov_dent.controllers;


import beshkov_dent.model.dto.UserLoginDto;
import beshkov_dent.service.UserService;
import beshkov_dent.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    public LoginController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("userLoginDto")
    public UserLoginDto iniLoginDto() {
        return new UserLoginDto();
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }

    @GetMapping("/login")
    public String login() {
        if (this.loggedUser.isLogged()) {
            return "redirect:/message-panel";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginDto", userLoginDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto", bindingResult);

            return "redirect:/login";
        }

        boolean validCredentials = this.userService.checkCredentials(userLoginDto.getUsername(), userLoginDto.getPassword());

        if (!validCredentials) {
            redirectAttributes
                    .addFlashAttribute("userLoginDto", userLoginDto)
                    .addFlashAttribute("validCredentials", false);
            return "redirect:/login";
        }
        this.userService.login(userLoginDto.getUsername());
        return "redirect:/message-panel";
    }

    @GetMapping("/user/logout")
    public String logout() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/login";
        }
        this.userService.logout();
        return "redirect:/login";
    }
}
