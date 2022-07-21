package com.mk.backend.assignement.receipes.utils.helper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.mk.backend.assignement.receipes.service.dto.recipe.RecipeDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class HelperPage extends PageImpl<RecipeDto> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public HelperPage(@JsonProperty("content") List<RecipeDto> content,
                            @JsonProperty("number") int number,
                            @JsonProperty("size") int size,
                            @JsonProperty("totalElements") Long totalElements,
                            @JsonProperty("pageable") JsonNode pageable,
                            @JsonProperty("last") boolean last,
                            @JsonProperty("totalPages") int totalPages,
                            @JsonProperty("sort") JsonNode sort,
                            @JsonProperty("first") boolean first,
                            @JsonProperty("numberOfElements") int numberOfElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public HelperPage(List<RecipeDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public HelperPage(List<RecipeDto> content) {
        super(content);
    }
}
