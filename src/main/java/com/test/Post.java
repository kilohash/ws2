package com.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Post {
    private String id;
    private String userId;
    private String title;
    private String body;
}
