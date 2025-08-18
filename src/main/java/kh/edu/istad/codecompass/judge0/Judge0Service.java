package kh.edu.istad.codecompass.judge0;

import kh.edu.istad.codecompass.dto.CodeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class Judge0Service {

    private static final Logger log = LoggerFactory.getLogger(Judge0Service.class);

    private final WebClient webClient;

    public Judge0Service() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:2358")
                .build();
    }

    public String executeCode(CodeRequest request) {
        log.info("=== Judge0Service Request ===");
        log.info("source_code: {}", request.sourceCode());
        log.info("language_id: {}", request.languageId());
        log.info("stdin: {}", request.stdin());
        log.info("============================");

        try {
            // Send POST request
            String response = webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/submissions")
                            .queryParam("base64_encoded", "false")
                            .queryParam("wait", "true")
                            .build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();  // Block to get the response synchronously

            log.info("Judge0 Response: {}", response);
            return response;

        } catch (WebClientResponseException e) {
            log.error("Failed to execute code: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return "{\"error\": \"Failed to execute code: " + e.getResponseBodyAsString() + "\"}";
        } catch (Exception e) {
            log.error("Failed to execute code", e);
            return "{\"error\": \"Failed to execute code: " + e.getMessage() + "\"}";
        }
    }
}
