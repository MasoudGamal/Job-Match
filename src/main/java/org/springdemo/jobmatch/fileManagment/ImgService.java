package org.springdemo.jobmatch.fileManagment;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.job.entity.Job;
import org.springdemo.jobmatch.job.exception.JobNotFundException;
import org.springdemo.jobmatch.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImgService {

    private final JobRepository jobRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;


    public Job uploadJobImages(Integer id, List<MultipartFile> files) {


        List<String> imagePaths = new ArrayList<>();

        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFundException("Job Not Fund :  "));


        for (MultipartFile file : files) {

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path filePath = Paths.get(uploadDir + File.separator + fileName);


            try {
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            imagePaths.add(fileName);


        }

        job.getImagePaths().addAll(imagePaths);
        return jobRepository.save(job);
    }

    public List<String> findAllImgByJobId(Integer id) {


        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFundException("Job Not Fund :  "));

        return job.getImagePaths().stream().map(imagePath -> uploadDir + File.separator + imagePath).toList();

    }
}
