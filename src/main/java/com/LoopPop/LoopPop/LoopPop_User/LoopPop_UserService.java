package com.LoopPop.LoopPop.LoopPop_User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class LoopPop_UserService {
    public List<LoopPop_User> GetLoopPopUser() {
        return List.of(
                new LoopPop_User(
                        1L, "Michael", "Michael@gmail.com"
                        , LocalDate.of(2000, Month.APRIL, 29)
                        , 21
                )
        );
    }
}