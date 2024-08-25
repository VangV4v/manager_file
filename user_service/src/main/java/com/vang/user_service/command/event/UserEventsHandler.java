package com.vang.user_service.command.event;

import com.vang.user_service.data.UserRepository;
import com.vang.user_service.data.Users;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserEventsHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    public void handle(UserCreatedEvent event) {

        Users users = new Users();
        BeanUtils.copyProperties(event, users);
        userRepository.save(users);
    }

    @EventHandler
    public void handle(UserUpdatedEvent event) {
        Users users = new Users();
        BeanUtils.copyProperties(event, users);
        userRepository.save(users);
    }

    @EventHandler
    public void handle(UserDeletedEvent event) {

        userRepository.deleteById(event.getUserId());
    }
}