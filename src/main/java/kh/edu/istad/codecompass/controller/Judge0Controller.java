package kh.edu.istad.codecompass.controller;

import kh.edu.istad.codecompass.dto.CodeRequest;
import kh.edu.istad.codecompass.judge0.Judge0Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/code-compass/judge0")
public class Judge0Controller {

    private final Judge0Service judge0Service;

    public Judge0Controller(Judge0Service judge0Service) {
        this.judge0Service = judge0Service;
    }

    @PostMapping("/execute")
    public String execute(@RequestBody CodeRequest request) {
        // Just forward request to service
        return judge0Service.executeCode(request);
    }
}
