package beshkov_dent.controllers;

import beshkov_dent.model.Message;
import beshkov_dent.repository.MessageRepo;
import beshkov_dent.service.MessageService;
import beshkov_dent.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MessageController {

    private final LoggedUser loggedUser;
    private final MessageRepo messageRepo;
    private final MessageService messageService;

    public MessageController(LoggedUser loggedUser, MessageRepo messageRepo, MessageService messageService) {
        this.loggedUser = loggedUser;
        this.messageRepo = messageRepo;
        this.messageService = messageService;
    }

    @GetMapping("/message-panel")
    public String homeIni(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }
        List<Message> allMessages = this.messageRepo.findAll();
        model.addAttribute("allMessages", allMessages);
        return "message-panel";
    }

    @GetMapping("/message/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.messageService.removeMessageById(id);
        return "redirect:/message-panel";
    }
}
