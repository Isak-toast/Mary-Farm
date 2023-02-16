package com.ssafy.maryfarmboardservice.api.dto.query.TotalArticleView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "articleGroupByType")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchArticleByTypeDTO {
    @Id
    private String id;
    @Indexed
    private String type;
    private List<ArticleDTO> articles = new ArrayList<>();

}
