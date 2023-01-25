package com.base.PetSearchServer.repository;

import com.base.PetSearchServer.entity.Ad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AdRepository extends CrudRepository<Ad, Long> {
    Ad findById(long id);

    Page<Ad> findAll(Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ad WHERE animal = :animal"
    )
    Page<Ad> findByType(@Param("animal") String animal, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ad WHERE animal = :animal AND gender = :gender AND is_Lost = :isLost"
    )
    Page<Ad> findByTypeGenderIsLost(
            @Param("animal") String animal,
            @Param("gender") String gender,
            @Param("isLost") boolean isLost,
            Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ad WHERE gender = :gender"
    )
    Page<Ad> findByGender(
            @Param("gender") String gender,
            Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ad WHERE is_Lost = :isLost"
    )
    Page<Ad> findByLost(
            @Param("isLost") boolean isLost,
            Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ad WHERE animal = :animal AND gender = :gender"
    )
    Page<Ad> findByAnimalAndGender(
            @Param("animal") String animal,
            @Param("gender") String gender,
            Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ad WHERE animal = :animal AND is_Lost = :isLost"
    )
    Page<Ad> findByAnimalAndLost(
            @Param("animal") String animal,
            @Param("isLost") boolean isLost,
            Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ad WHERE gender = :gender AND is_Lost = :isLost"
    )
    Page<Ad> findByGenderAndLost(
            @Param("gender") String gender,
            @Param("isLost") boolean isLost,
            Pageable pageable);
}
