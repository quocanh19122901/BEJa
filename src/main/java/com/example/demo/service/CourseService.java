package com.example.demo.service;

import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.CourseEntity;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.request.CourseRequest;
import com.example.demo.response.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<CourseResponse> getListCourse() {
        return courseRepository.findAll().stream()
                .map(CourseResponse::toDomain)
                .collect(Collectors.toList());
    }

    public String addAccount(CourseRequest c_req) {

        CourseEntity course = courseRepository.findById(c_req.getCourse_id())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + c_req.getCourse_id()));
        List<AccountEntity> listAccount = accountRepository.findAllById(c_req.getAccount_id());

        List<AccountEntity> existingAccounts = course.getAccounts();
        List<Long> existingIds = existingAccounts.stream().map(AccountEntity::getId).toList();

        List<String> addedIds = new ArrayList<>();
        List<String> skippedIds = new ArrayList<>();

        for (AccountEntity account : listAccount) {
            if (existingIds.contains(account.getId())) {
                skippedIds.add(String.valueOf(account.getId()));
                continue;
            }
            existingAccounts.add(account);
            addedIds.add(String.valueOf(account.getId()));
        }

        course.setAccounts(existingAccounts);

        courseRepository.save(course);

        String addedMessage = addedIds.isEmpty()
                ? "No accounts were added."
                : "Account ID " + String.join(", ", addedIds) + " was successfully added.";
        String skippedMessage = skippedIds.isEmpty()
                ? "No accounts were skipped."
                : "Account ID " + String.join(", ", skippedIds) + " already exists and was skipped.";

        return addedMessage + "\n" + skippedMessage;
    }
    public CourseResponse getDetailCourse (Long id)
    {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return CourseResponse.toDomain(course);
    }
}
