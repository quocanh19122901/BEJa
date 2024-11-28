package com.example.demo.controller;

import com.example.demo.request.CourseRequest;
import com.example.demo.response.CourseResponse;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<CourseResponse> getListCourse() {
        return courseService.getListCourse();
    }

    @PostMapping("/courses/add")
    public ResponseEntity<String> addAccount(@RequestBody CourseRequest req) {
        String resultMessage = courseService.addAccount(req);
        return ResponseEntity.ok(resultMessage);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> getDetail(@PathVariable Long id) {
        CourseResponse course = courseService.getDetailCourse(id);
        return ResponseEntity.ok(course);
    }
}