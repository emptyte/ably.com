package team.emptyte.ably;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
  public static void main(final @NotNull String @NotNull [] args) {
    SpringApplication.run(Application.class, args);
  }
}
