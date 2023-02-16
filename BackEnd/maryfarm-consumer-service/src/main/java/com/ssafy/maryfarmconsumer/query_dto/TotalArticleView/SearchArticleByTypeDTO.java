package com.ssafy.maryfarmconsumer.query_dto.TotalArticleView;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "ArticleGroupByType")
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
