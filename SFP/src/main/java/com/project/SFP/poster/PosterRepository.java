package com.project.SFP.poster;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PosterRepository extends CrudRepository<Poster, Long> {

    List<Poster> findByTitle(String title);

}
