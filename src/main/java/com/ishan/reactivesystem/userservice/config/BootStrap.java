package com.ishan.reactivesystem.userservice.config;

import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import com.github.javafaker.Superhero;
import com.ishan.reactivesystem.userservice.dao.User;
import com.ishan.reactivesystem.userservice.dao.UserRepository;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
@Slf4j
public class BootStrap implements CommandLineRunner {

  @Value("classpath:h2/init.sql")
  private Resource resource;

  @Autowired
  private R2dbcEntityTemplate r2dbcEntityTemplate;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {

    log.info("DDL | ---------- Starting...");

    String sql = StreamUtils.copyToString(
        resource.getInputStream(),
        StandardCharsets.UTF_8
    );

    r2dbcEntityTemplate.getDatabaseClient()
        .sql(sql)
        .then()
        .subscribe();

    log.info("DDL | ---------- Done...");

    log.info("Test Data Generation | ---------- Starting...");

    List<User> users = new ArrayList<>();
    Superhero userGenerator = Faker.instance().superhero();
    Commerce commerce = Faker.instance().commerce();

    int batch = 1;
    int batchSize = 100;
    int totalUsers = 1000;
    for (int i = 1; i <= totalUsers; i++) {
      User user = new User();
      user.setName(userGenerator.name());
      user.setBalance(new BigDecimal(commerce.price(100, 100_000)));
      users.add(
          user
      );

      if (i % batchSize == 0) {
        log.info("Test Data Generation | ---------- Saving Batch: " + batch + "/"
            + totalUsers / batchSize);
        userRepository.saveAll(users)
            .subscribe();
        users.clear();
        ++batch;
      }

    }

    log.info("Test Data Generation | ---------- Done");
  }

}
