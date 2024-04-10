package com.aresky.tourservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyPageable {
    private Integer size;
    private Integer page;
}
