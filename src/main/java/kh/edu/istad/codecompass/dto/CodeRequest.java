package kh.edu.istad.codecompass.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CodeRequest(
        @JsonProperty("source_code") String sourceCode,
        @JsonProperty("language_id") Integer languageId,
        String stdin
) {}
