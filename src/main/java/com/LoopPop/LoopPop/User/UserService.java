package com.LoopPop.LoopPop.User;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class UserService {
    public List<User> GetUser(){
        return List.of(
                new User(
                         1L, "Michael", "Michael@gmail.com"
                        , LocalDate.of(2000, Month.APRIL, 29)
                        , 21
                )
        );
    }
}
