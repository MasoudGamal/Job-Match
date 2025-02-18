package org.springdemo.jobmatch.fileManagment;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.job.entity.Job;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("img")
@RequiredArgsConstructor
public class ImgController {

    private final ImgService imgService;


    @PostMapping
    public Job uploadJobImages(@RequestParam Integer id
            , @RequestParam("files") List<MultipartFile> files) {
        return imgService.uploadJobImages(id, files);
    }

    @GetMapping("/{id}")
    public List<String> findAllImgByJobId(@PathVariable Integer id) {
        return imgService.findAllImgByJobId(id);
    }
}
