package com.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;

@SpringBootApplication
public class UsersApplication {

  //Things to work on

  //add validation for unique username (hint existsByUsername in repository - inject repository into validator).
  //and write tests in UserValidatorTest for unique username (hint Mockito.mock(UserRepository.class), Mockito.when(repository.existsByUsername))

  //add a phone table, entity, dto, service, controller, assembler etc and make phone a subresource of user.
  //add controller and validator tests for the phone subresource

  //add a phone 'action' to make a phone number primary

  //come up with a set of REST endpoints that integrate with twillio to verify a cell phone number (don't check in your twillio api key).



  public static void main(String[] args) {
    SpringApplication.run(UsersApplication.class, args);
  }

  @Bean
  public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
    return new MessageSourceAccessor(messageSource);
  }
}
