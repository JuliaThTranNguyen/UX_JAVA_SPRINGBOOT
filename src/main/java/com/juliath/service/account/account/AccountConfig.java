package com.juliath.service.account.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            AccountRepository repository
    ) {
        return args -> {
            Account HienTran = new Account(
                    "Julia TH - Tran Hien",
                    "v1.example@gmail.com",
                    LocalDate.of(1999, Month.JUNE, 23)
            );

            Account Dummy = new Account(
                    "Dummy for testing",
                    "dummy.example@gmail.com",
                    LocalDate.of(2005, Month.JULY, 02)
            );

            repository.saveAll(
                    List.of(HienTran, Dummy)
            );
        };
    }
}
