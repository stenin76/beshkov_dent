package beshkov_dent.controllers;

import beshkov_dent.model.dto.MessageAddDto;
import beshkov_dent.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final MessageService messageService;

    public HomeController(MessageService messageService) {
        this.messageService = messageService;
    }


    @ModelAttribute("messageAddDto")
    public MessageAddDto iniProductAddDto() {
        return new MessageAddDto();
    }

    @GetMapping("/")
    public String showHome(Model model) {

        if (!model.containsAttribute("messageAddDto")) {
            model.addAttribute("messageAddDto", new MessageAddDto());
        }
        return "index";
    }

    @PostMapping("/addMessage")
    public String massageAdd(@Valid MessageAddDto messageAddDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("messageAddDto", messageAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.messageAddDto", bindingResult);

            return "redirect:/#reservation-form";
        }
        this.messageService.addMassage(messageAddDto);

        redirectAttributes
                .addFlashAttribute("successful_message", messageAddDto);

        return "redirect:/#reservation-form";
    }
}
