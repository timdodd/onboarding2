package com.onboarding;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;

@SpringBootApplication
public class UsersApplication {

  //Things to work on

  //add validation for unique username (hint existsByUsername in repository - inject repository into validator). DONE
  //and write tests in UserValidatorTest for unique username (hint Mockito.mock(UserRepository.class), Mockito.when(repository.existsByUsername)) DONE

  //add a phone table, entity, dto, service, controller, assembler etc and make phone a subresource of user. DONE
  //add controller and validator tests for the phone subresource DONE

  //add a phone 'action' to make a phone number primary DONE

  //come up with a set of REST endpoints that integrate with twillio to verify a cell phone number (don't check in your twillio api key). DONE

  public static final String ACCOUNT_SID = "AC43475bac3a893aa51532dc493cd9ed49";
  public static final String AUTH_TOKEN = "1f9718fc637e177cedba789a5f872d52";
  public static final String SID = "VAdf173e64970fe8cee7faa2164a1aa4e5";

  public static void main(String[] args) {
    SpringApplication.run(UsersApplication.class, args);

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
  }

  @Bean
  public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
    return new MessageSourceAccessor(messageSource);
  }


}
