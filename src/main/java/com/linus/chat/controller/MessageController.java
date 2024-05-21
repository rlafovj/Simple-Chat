package com.linus.chat.controller;

import com.linus.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
  private final SimpMessageSendingOperations sendingOperations;
  private final MessageService messageService;

}
