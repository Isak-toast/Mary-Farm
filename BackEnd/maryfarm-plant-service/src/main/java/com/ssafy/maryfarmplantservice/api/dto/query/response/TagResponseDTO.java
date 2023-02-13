package com.ssafy.maryfarmplantservice.api.dto.query.response;

import com.ssafy.maryfarmplantservice.domain.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseDTO {
    private String tagname;
    public static TagResponseDTO from(Tag tag) {
        TagResponseDTO dto = new TagResponseDTO();
        dto.tagname = tag.getName();
        return dto;
    }
}
