package com.ssafy.maryfarmplantservice.kafka.dto.diary;

import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import com.ssafy.maryfarmplantservice.kafka.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaDiaryDTO {
    private Status status;
    private Diary diary;
}
