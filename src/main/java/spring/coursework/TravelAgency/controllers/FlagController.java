package spring.coursework.TravelAgency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.coursework.TravelAgency.models.CountryFlag;
import spring.coursework.TravelAgency.repositories.FlagRepository;

import java.io.ByteArrayInputStream;

@Controller
public class FlagController {
    private final FlagRepository flagRepository;

    @Autowired
    public FlagController(FlagRepository flagRepository) {
        this.flagRepository = flagRepository;
    }

    @GetMapping("/flags/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Integer id){
        CountryFlag flag = flagRepository.findById(id).orElse(null);
        assert flag != null;
        return ResponseEntity.ok()
                .header("fileName", flag.getFileName())
                .contentType(MediaType.valueOf(flag.getContentType()))
                .contentLength(flag.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(flag.getBytes())));
    }
}
