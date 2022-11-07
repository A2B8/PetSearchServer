package com.project.SFP;

import com.project.SFP.poster.Poster;
import com.project.SFP.poster.PosterRepository;
import com.project.SFP.user.PUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private PosterRepository posterRepository;

    @GetMapping(path = "/main")
    public String main(Map<String, Object> model) {
        Iterable<Poster> posters = posterRepository.findAll();

        model.put("posters", posters);

        return "main";
    }
    @PostMapping(path = "/main")
    public String add(@AuthenticationPrincipal PUser user, @RequestParam(name = "text", required = false) String text, @RequestParam(name = "title", required = false) String title, Map<String, Object> model) {
        Poster poster = new Poster(text, title, user);

        posterRepository.save(poster);

        Iterable<Poster> posters = posterRepository.findAll();

        model.put("posters", posters);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Poster> posters;

        if (filter != null && !filter.isEmpty()) {
            posters = posterRepository.findByTitle(filter);
        } else {
            posters = posterRepository.findAll();
        }

        model.put("posters", posters);

        return "main";
    }
}
