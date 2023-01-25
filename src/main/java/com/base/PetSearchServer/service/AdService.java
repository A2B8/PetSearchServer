package com.base.PetSearchServer.service;

import com.base.PetSearchServer.entity.Ad;
import com.base.PetSearchServer.repository.AdRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdService {

    private final AdRepository adRepository;

    public void CreateAd(Ad ad) {
        adRepository.save(ad);
    }

    public Ad FindById(long id) {
        return adRepository.findById(id);
    }

    public Page<Ad> FindAll(Pageable pageable){
        return adRepository.findAll(pageable);
    }

    public Page<Ad> findByTypeGenderIsLost(String animal, String gender, boolean isLost, Pageable pageable) {
        return adRepository.findByTypeGenderIsLost(animal,gender,isLost,pageable);
    }

    public Page<Ad> findByType(String animal, Pageable pageable) {
        return adRepository.findByType(animal,pageable);
    }

    public Page<Ad> findByGender(String gender, Pageable pageable) {
        return adRepository.findByGender(gender, pageable);
    }

    public Page<Ad> findByLost(boolean lost, Pageable pageable) {
        return adRepository.findByLost(lost, pageable);
    }

    public Page<Ad> findByAnimalAndGender(String animal, String gender, Pageable pageable) {
        return adRepository.findByAnimalAndGender(animal, gender, pageable);
    }

    public Page<Ad> findByAnimalAndLost(String animal, boolean lost, Pageable pageable) {
        return adRepository.findByAnimalAndLost(animal, lost, pageable);
    }

    public Page<Ad> findByGenderAndLost(String gender, boolean lost, Pageable pageable) {
        return adRepository.findByGenderAndLost(gender, lost, pageable);
    }
}
