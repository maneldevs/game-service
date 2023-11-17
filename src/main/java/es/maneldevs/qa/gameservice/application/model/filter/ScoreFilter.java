package es.maneldevs.qa.gameservice.application.model.filter;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreFilter {
    @NotNull
    @Size(min = 1)
    private List<String> targetCodes;
}
