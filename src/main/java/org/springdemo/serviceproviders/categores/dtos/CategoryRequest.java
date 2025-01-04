package org.springdemo.serviceproviders.categores.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class CategoryRequest {

    private int id;

    @NotBlank
    private String name;

}
