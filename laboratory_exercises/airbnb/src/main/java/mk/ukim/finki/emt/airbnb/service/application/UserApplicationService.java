package mk.ukim.finki.emt.airbnb.service.application;


import mk.ukim.finki.emt.airbnb.dto.CreateUserDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayUserDto;
import mk.ukim.finki.emt.airbnb.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
