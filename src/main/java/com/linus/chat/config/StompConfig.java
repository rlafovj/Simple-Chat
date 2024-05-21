package com.linus.chat.config;

import com.linus.chat.controller.StompHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class StompConfig implements WebSocketMessageBrokerConfigurer {
  private final StompHandler stompHandler;
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    //처음 webSocket에 접속할 때 HandShake와 통신을 담당할 엔드포인트를 .addEndpoint("/chat") 지정
    registry.addEndpoint("/chat")
            .setAllowedOrigins("*")
            //WebSocket을 지원하지 않는 브라우저의 경우 SockJS를 통해 다른 방식으로 채팅이 이뤄질 수 있게 구현
            .withSockJS();
  }
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
  }
  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    //StompHandler 가 Websocket 앞에서 token을 확인하도록 설정
      registration.interceptors(stompHandler);
  }
}
