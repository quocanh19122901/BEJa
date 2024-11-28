package com.example.demo.response;
import com.example.demo.entity.CourseEntity;
import lombok.Data;

import java.util.List;
import java.util.Objects;
@Data
public class CourseResponse {
    private Long id;
    private String courseName;
    private String desc;
    private List<AccountResponse> accounts;

    public static CourseResponse toDomain(CourseEntity entity) {
        CourseResponse response = new CourseResponse();
        if (Objects.isNull(entity)) return response;
        response.setId(entity.getId());
        response.setCourseName(entity.getCourseName());
        response.setDesc(entity.getDescription());
        List<AccountResponse> accountResponses = entity.getAccounts().stream()
                .map(AccountResponse::toDomain)
                .toList();
        response.setAccounts(accountResponses);
        return response;
    }
    public static void main(String[] args) {
    }
}
