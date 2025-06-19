package beshkov_dent.service;

import beshkov_dent.model.Message;
import beshkov_dent.model.dto.MessageAddDto;
import beshkov_dent.repository.MessageRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class MessageService {

    private final MessageRepo messageRepo;

    private final ModelMapper modelMapper;


    public MessageService(MessageRepo messageRepo, ModelMapper modelMapper) {
        this.messageRepo = messageRepo;
        this.modelMapper = modelMapper;
    }

    public void addMassage(MessageAddDto messageAddDto) {
        Message messageToAdd = modelMapper.map(messageAddDto, Message.class);
        this.messageRepo.save(messageToAdd);
    }

    public void removeMessageById(Long id) {
        messageRepo.deleteById(id);
    }
}
