package com.ltc.helioslessonspring.controller;

import com.ltc.helioslessonspring.model.AppUserEntity;
import com.ltc.helioslessonspring.service.UserService;
import com.ltc.helioslessonspring.validation.ValidName;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /// localhost:8080/user/create -> url bu qaydada yazilsin ve Postmanda POST sechilsin
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUserEntity createUser(@Valid @RequestBody AppUserEntity userEntity){
        return userService.createUser(userEntity);
    }

    /// localhost:8080/user/getAll -> url bu qaydada yazilsin ve Postmanda GET sechilsin
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public  List<AppUserEntity> getAll(){
        return userService.getAll();
    }

    /// localhost:8080/user/getById/1 -> url bu qaydada yazilsin ve Postmanda GET sechilsin
    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserEntity getById(@PathVariable Long id){
        return userService.getById(id);
    }

    /// localhost:8080/user/1 -> url bu qaydada yazilsin ve Postmanda PUT sechilsin
    /// raw yazilan yerde deyishilecek ad yazilsin
    /// meselen evvel username: Orxan idise indi yazin Ali
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserEntity update(@PathVariable Long id ,@RequestBody AppUserEntity userEntity){
        return userService.update(id, userEntity);
    }

    /// localhost:8080/user/1 -> url bu qaydada yazilsin ve Postmanda DELETE sechilsin
    /// ID-i 1 olan user silinecek
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }













}
