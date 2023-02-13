package com.ssafy.maryfarmplantservice.api.controller.diary;

import com.ssafy.maryfarmplantservice.api.dto.diary.DiaryCommentResponseDTO;
import com.ssafy.maryfarmplantservice.api.dto.diary.SearchByTagRequestDTO;
import com.ssafy.maryfarmplantservice.api.dto.query.TagSearchView.TagSearchDTO;
import com.ssafy.maryfarmplantservice.api.dto.query.response.DetailDiaryResponseDTO;
import com.ssafy.maryfarmplantservice.api.dto.query.response.DiarySearchResponseDTO;
import com.ssafy.maryfarmplantservice.api.dto.query.response.DiaryToHomeResponseDTO;
import com.ssafy.maryfarmplantservice.api.dto.query.response.FollowingDiaryResponseDTO;
import com.ssafy.maryfarmplantservice.api.dto.query.DetailDiariesPerPlantView.DetailDiariesPerPlantDTO;
import com.ssafy.maryfarmplantservice.client.dto.user.UserResponseDTO;
import com.ssafy.maryfarmplantservice.client.service.user.UserServiceClient;
import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import com.ssafy.maryfarmplantservice.domain.diary.DiaryComment;
import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import com.ssafy.maryfarmplantservice.mongo_repository.DetailDiariesPerPlantView.DetailDiariesPerPlantDTORepository;
import com.ssafy.maryfarmplantservice.mongo_repository.TagSearchView.TagSearchDTORepository;
import com.ssafy.maryfarmplantservice.service.DiaryCService;
import com.ssafy.maryfarmplantservice.service.PlantCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class DiaryQuery {
    private final DiaryCService diaryCService;
    private final PlantCService plantCService;
    private final UserServiceClient userServiceClient;
    private final DetailDiariesPerPlantDTORepository detailDiariesPerPlantDTORepository;
    private final TagSearchDTORepository tagSearchDTORepository;

    @Operation(summary = "일지 태그 검색", description = "일지 태그를 검색합니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = DiarySearchResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/diary/tag/search")
    public ResponseEntity<?> searchPlant(@RequestBody SearchByTagRequestDTO dto) {
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        KomoranResult analyze = komoran.analyze(dto.getText());
        String name = dto.getText();
        List<Token> tokenList = analyze.getTokenList();
        for(Token token : tokenList) {
            if(token.getPos().matches("NN.")) {
                name = token.getMorph();
                break;
            }
        }
        Optional<TagSearchDTO> resultDto = tagSearchDTORepository.findByTagName(name);
        return ResponseEntity.ok(resultDto.get());
    }

    @Operation(summary = "팔로우 일지 조회", description = "팔로우한 사람들의 일지를 가져옵니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = FollowingDiaryResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/diary/follower/{userId}")
    public ResponseEntity<?> FollowerDiary(@PathVariable("userId") String userId) {
        List<UserResponseDTO> userDtos = userServiceClient.searchFollower(userId);
        List<FollowingDiaryResponseDTO> resultDtos = new ArrayList<>();
        for(UserResponseDTO u : userDtos) {
            List<Diary> list = diaryCService.searchDiaryByUserId(u.getUserId());
            for(Diary d : list) {
                resultDtos.add(FollowingDiaryResponseDTO.of(d,u));
            }
        }
        return ResponseEntity.ok(resultDtos);
    }

    @Operation(summary = "자신의 일지 조회", description = "자신의 일지들을 가져옵니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = DiaryToHomeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    /*
        자신의 홈 화면에 작물 당 최신일지 들을 표시함.
     */
    @GetMapping("/diary/user/{userId}")
    public ResponseEntity<?> DiaryToHome(@PathVariable("userId") String userId) {
        List<Plant> list = plantCService.searchPlantsByUserId(userId);
        List<DiaryToHomeResponseDTO> resultDtos = new ArrayList<>();
        for(Plant p : list) {
            Diary diary = diaryCService.searchEarlyDiaryByPlant(p.getId());
            resultDtos.add(DiaryToHomeResponseDTO.of(diary));
        }
        return ResponseEntity.ok(resultDtos);
    }

    @Operation(summary = "작물 일지 조회", description = "특정 작물의 일지들을 가져옵니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = DetailDiaryResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/diary/plant/{plantId}")
    public ResponseEntity<?> DetailDiary(@PathVariable("plantId") String plantId) {
        List<Diary> list = diaryCService.searchDiarysByPlantId(plantId);
        List<DetailDiaryResponseDTO> resultDtos = new ArrayList<>();
        for(Diary d : list) {
            UserResponseDTO userDto = userServiceClient.searchUser(d.getPlant().getUserId());
            resultDtos.add(DetailDiaryResponseDTO.of(d,userDto));
        }
        return ResponseEntity.ok(resultDtos);
    }

    @Operation(summary = "작물 일지 댓글 조회", description = "특정 작물 일지의 댓글들을 가져옵니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = DetailDiaryResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/diary/comment/{diaryId}")
    public ResponseEntity<?> SearchDiaryComment(@PathVariable("diaryId") String diaryId) {
        List<DiaryComment> list = diaryCService.searchDiaryComments(diaryId);
        List<DiaryCommentResponseDTO> resultDtos = new ArrayList<>();
        for(DiaryComment c : list) {
            resultDtos.add(DiaryCommentResponseDTO.of(c));
        }
        return ResponseEntity.ok(resultDtos);
    }

    @Operation(summary = "작물 일지 추천", description = "좋아요 순 상위 5개 작물 일지를 가져옵니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = DetailDiaryResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/diary/top")
    public ResponseEntity<?> searchTopDiary() {
        List<Diary> list = diaryCService.searchDiarysTopLike();
        List<DetailDiaryResponseDTO> resultDtos = new ArrayList<>();
        for(Diary d : list) {
            UserResponseDTO userDto = userServiceClient.searchUser(d.getPlant().getUserId());
            resultDtos.add(DetailDiaryResponseDTO.of(d,userDto));
        }
        return ResponseEntity.ok(resultDtos);
    }

    @Operation(summary = "특정 작물의 일지 묶음 조회", description = "특정 작물의 일지 묶음 전체를 조회합니다.", tags = { "Diary Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = DetailDiariesPerPlantDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/diary/group/{plantId}")
    public ResponseEntity<?> searchTotalDiaryPerPlant(@PathVariable("plantId") String plantId) {
        Optional<DetailDiariesPerPlantDTO> resultDto = detailDiariesPerPlantDTORepository.findByPlantId(plantId);
        return ResponseEntity.ok(resultDto.get());
    }
}
