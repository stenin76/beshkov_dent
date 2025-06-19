package beshkov_dent.controllers;

import beshkov_dent.model.dto.MessageAddDto;
import beshkov_dent.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
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
    public String showHome() {
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

        return "redirect:/#fh5co-contact";
    }
}
