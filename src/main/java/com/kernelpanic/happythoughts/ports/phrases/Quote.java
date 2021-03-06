package com.kernelpanic.happythoughts.ports.phrases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    @NonNull
    @JsonProperty("a")
    String author;

    @NonNull
    @JsonProperty("q")
    String quote;
}
