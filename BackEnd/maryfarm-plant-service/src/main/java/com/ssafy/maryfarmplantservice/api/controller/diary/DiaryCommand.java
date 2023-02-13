package com.ssafy.maryfarmplantservice.api.controller.diary;

import com.ssafy.maryfarmplantservice.api.dto.diary.*;
import com.ssafy.maryfarmplantservice.client.service.user.UserServiceClient;
import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import com.ssafy.maryfarmplantservice.domain.diary.DiaryComment;
import com.ssafy.maryfarmplantservice.domain.diary.DiaryLike;
import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import com.ssafy.maryfarmplantservice.kafka.producer.diary.DiaryProducer;
import com.ssafy.maryfarmplantservice.kafka.producer.plant.PlantProducer;
import com.ssafy.maryfarmplantservice.service.DiaryCService;
import com.ssafy.maryfarmplantservice.service.PlantCService;
import com.ssafy.maryfarmplantservice.util.file.dto.FileDetail;
import com.ssafy.maryfarmplantservice.util.file.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class DiaryCommand {
    private final DiaryCService diaryCService;
    private final PlantCService plantCService;
    private final FileUploadService fileUploadService;
    private final UserServiceClient userServiceClient;
    private final DiaryProducer diaryProducer;
    private final PlantProducer plantProducer;

    @Operation(summary = "일지 시작", description = "작물을 등록함과 동시에 일지를 시작합니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/diary/init")
    public ResponseEntity<?> initDiary(@RequestPart MultipartFile image, @RequestPart InitDiaryRequestDTO dto) throws IOException {
        /*
            saveDiary에 Diary 객체를 넘겨주는 게 아닌 Diary 객체를 만드는 데에 필요한 정보를 넘겨주는 게
            의존성 관리에 더 바람직함. Diary 객체를 넘겨주려면 Diary 객체에 대한 의존성이 생김.
         */
        Plant savePlant = plantCService.savePlant(dto.getUserId(),dto.getTitle(),dto.getName());
        FileDetail saveFile = fileUploadService.save(image);
        Diary saveDiary = diaryCService.saveDiary(savePlant.getId(),dto.getContent(), saveFile.getPath(),
                dto.getUserId(),dto.getUserName(),dto.getProfilePath());
        return ResponseEntity.ok(saveDiary.getId());
    }

    @Operation(summary = "일지 추가", description = "일지를 추가로 등록합니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/diary/add")
    public ResponseEntity<?> addDiary(@RequestPart MultipartFile image, @RequestPart AddDiaryRequestDTO dto) throws IOException {
        FileDetail saveFile = fileUploadService.save(image);
        Diary saveDiary = diaryCService.saveDiary(dto.getPlantId(), dto.getContent(), saveFile.getPath(),
                dto.getUserId(),dto.getUserName(),dto.getProfilePath());
        return ResponseEntity.ok(saveDiary.getId());
    }

    @Operation(summary = "일지 수정", description = "일지를 수정합니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/diary/modify")
    public ResponseEntity<?> modifyDiary(@RequestPart MultipartFile image, @RequestPart ModifyDiaryRequestDTO dto) throws IOException {
        // 사진을 바꾸지 않는다면 image가 null값이 들어오므로 따로 처리안해줘도 됨.
        Diary updateDiary = null;
        if(!image.isEmpty()) {
            FileDetail saveFile = fileUploadService.save(image);
            updateDiary = diaryCService.updateDiaryContentAndImage(dto.getDiaryId(), dto.getContent(), saveFile.getPath());
        } else {
            updateDiary = diaryCService.updateDiaryContent(dto.getDiaryId(), dto.getContent());
        }
        return ResponseEntity.ok(updateDiary.getId());
    }

    @Operation(summary = "일지 좋아요 등록", description = "일지에 좋아요를 등록합니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/diary/like")
    public ResponseEntity<?> giveDiaryLike(@RequestBody DiaryLikeRequestDTO dto) {
        DiaryLike diaryLike = diaryCService.saveDiaryLike(dto.getDiaryId(),dto.getUserId());
        diaryCService.addLike(dto.getDiaryId());
        return ResponseEntity.ok(diaryLike.getId());
    }

    @Operation(summary = "일지 댓글 등록", description = "일지에 댓글을 등록합니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/diary/comment")
    public ResponseEntity<?> giveDiaryComment(@RequestBody DiaryCommentRequestDTO dto) {
        DiaryComment diaryComment = diaryCService.saveDiaryComment(dto.getDiaryId(),dto.getUserId(),dto.getContent());
        return ResponseEntity.ok(diaryComment.getId());
    }
}
