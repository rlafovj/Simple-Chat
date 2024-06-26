package com.linus.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class StompHandler implements ChannelInterceptor {
  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    log.info("Stomp Handler 실행됨");
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
    //헤더 토큰 얻기
    //String authorizationHeader = String.valueOf(headerAccessor.getNativeHeader("Authorization"));
    return message;
  }

  @EventListener
  public void handleWebSocketConnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    String sessionId = headerAccessor.getSessionId();
    log.info("Connect sessionId: {}", sessionId);
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    String sessionId = headerAccessor.getSessionId();
    log.info("Disconnect sessionId: {}", sessionId);
  }
}
