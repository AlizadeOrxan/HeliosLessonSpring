package com.ltc.helioslessonspring.service;

import com.ltc.helioslessonspring.model.AppUserEntity;
import com.ltc.helioslessonspring.repository.AppUserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final AppUserRepository appUserRepository;

    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @PostConstruct
    void init() {
        System.out.println("Databazaya qoshuldu , Sistem Aktivdir ! ");
    }

    @PreDestroy
    void destroy() {
        appUserRepository.deleteAll();
        System.out.println("Databazadan melumatlar silindi , Sistem temizdir ");
    }

    public AppUserEntity createUser(AppUserEntity userEntity){
        return appUserRepository.save(userEntity);
    }

    public List<AppUserEntity> getAll(){
        return appUserRepository.findAll();
    }

    public AppUserEntity getById(Long id){
        return appUserRepository.getById(id);
    }

    public AppUserEntity update(Long id , AppUserEntity userEntity){
        AppUserEntity oldUserEntity = appUserRepository.findById(id).orElseThrow(()->
                new RuntimeException("Bele bir id-e sahib biri yoxdur"));

        oldUserEntity.setUsername(userEntity.getUsername());
        oldUserEntity.setSurname(userEntity.getSurname());

        return appUserRepository.save(oldUserEntity);
    }

    public void deleteById(Long id){
        appUserRepository.deleteById(id);
    }








}
