package com.ssafy.maryfarmconsumer.query_dto.TagSearchView;

import com.ssafy.maryfarmconsumer.query_dto.MyFarmView.FarmDiaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "TagSearchView")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagSearchDTO {
    @Id
    private String id;
    @Indexed
    private String tagName;
    private List<TagSearchDiaryDTO> diaries = new ArrayList<>();
}
