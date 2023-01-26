package com.base.PetSearchServer.controllers;

import com.base.PetSearchServer.entity.Ad;
import com.base.PetSearchServer.request.AdRequest;
import com.base.PetSearchServer.service.AdService;
import com.base.PetSearchServer.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/ads")
@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class AdsController {
    private StorageService storageService;

    private final AdService adService;

    @PostMapping("/new")
    public void CreateAd(@ModelAttribute AdRequest adRequest,
                         @ModelAttribute MultipartFile img
                        //, @AuthenticationPrincipal AppUser appUser
                         )
        throws IOException {
        Ad ad = new Ad(
                adRequest.getIsLost(),
                adRequest.getAnimal(),
                adRequest.getGender(),
                LocalDate.parse(adRequest.getDate()),
                adRequest.getAddress(),
                adRequest.getGeoLat(),
                adRequest.getGeoLon(),
                adRequest.getDescription(),
                adRequest.getName(),
                adRequest.getTel(),
                adRequest.getEmail()
                //, appUser
        );

        if(img != null){
            ad.setFilename(storageService.uploadImageToFileSystem(img));
        }

        adService.CreateAd(ad);
    }

    @GetMapping("/all")
    public Page<Ad> getPosters(
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable)
    {
        return adService.FindAll(pageable);
    }

    @GetMapping("/id")
    public Ad getPoster(@RequestParam("id") long PosterId) {
        return adService.FindById(PosterId);
    }

    @GetMapping("all/agl")
    public Page<Ad> getPosterByAnimalAndGenderAndLost(
            @RequestParam(value = "animal", required = false) String animal ,
            @RequestParam(value = "gender",required = false) String gender,
            @RequestParam(value = "isLost",required = false) boolean isLost,
            @PageableDefault(sort = { "date" },
                    direction = Sort.Direction.DESC) Pageable pageable)
    {
        return adService.findByTypeGenderIsLost(animal, gender, isLost, pageable);
    }

    @GetMapping("all/a")
    public Page<Ad> getPosterByType(
            @RequestParam("animal") String animal,
            @PageableDefault(sort = { "date" },
                    direction = Sort.Direction.DESC) Pageable pageable)
    {
        return adService.findByType(animal,pageable);
    }

    @GetMapping("all/g")
    public Page<Ad> getPosterByGender(
            @RequestParam("gender") String gender,
            @PageableDefault(sort = { "date" },
                    direction = Sort.Direction.DESC) Pageable pageable)
    {
        return adService.findByGender(gender,pageable);
    }

    @GetMapping("all/l")
    public Page<Ad> getPosterByLost(
            @RequestParam("isLost") boolean lost,
            @PageableDefault(sort = { "date" },
                    direction = Sort.Direction.DESC) Pageable pageable)
    {
        return adService.findByLost(lost, pageable);
    }

    @GetMapping("all/ag")
    public Page<Ad> getPosterByAnimalAndGender(
            @RequestParam("animal") String animal,
            @RequestParam("gender") String gender,
            @PageableDefault(sort = { "date" },
                    direction = Sort.Direction.DESC) Pageable pageable)
    {
        return adService.findByAnimalAndGender(animal, gender, pageable);
    }

    @GetMapping("all/al")
    public Page<Ad> getPosterByAnimalAndLost(
            @RequestParam("animal") String animal,
            @RequestParam("isLost") boolean lost,
            @PageableDefault(sort = { "date" },
                    direction = Sort.Direction.DESC) Pageable pageable)
    {
        return adService.findByAnimalAndLost(animal, lost, pageable);
    }

    @GetMapping("all/gl")
    public Page<Ad> getPosterByGenderAndLost(
            @RequestParam("gender") String gender,
            @RequestParam("isLost") boolean lost,
            @PageableDefault(sort = { "date" },
                    direction = Sort.Direction.DESC) Pageable pageable)
    {
        return adService.findByGenderAndLost(gender, lost, pageable);
    }

    @GetMapping("/file")
    public ResponseEntity<?> downloadImageFromFileSystem(@RequestParam String fileName) throws IOException {
        byte[] imageData=storageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
