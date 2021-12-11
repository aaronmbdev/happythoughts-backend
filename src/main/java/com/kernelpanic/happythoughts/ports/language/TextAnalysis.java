package com.kernelpanic.happythoughts.ports.language;

import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.Sentiment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class TextAnalysis {
    @NonNull Sentiment sentiment;
    @NonNull List<Entity> entityList;
    @NonNull String veredict;
}
