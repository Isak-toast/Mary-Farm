package com.ssafy.maryfarmuserservice.api.controller.user;

import com.ssafy.maryfarmuserservice.api.dto.query.FirstHomeView.FirstHomeViewDTO;
import com.ssafy.maryfarmuserservice.api.dto.query.MyFarmView.MyFarmViewDTO;
import com.ssafy.maryfarmuserservice.api.dto.query.response.UserResponseDTO;
import com.ssafy.maryfarmuserservice.domain.user.User;
import com.ssafy.maryfarmuserservice.mongo_repository.FirstHomeView.FirstHomeViewDTORepository;
import com.ssafy.maryfarmuserservice.mongo_repository.MyFarmView.MyFarmViewDTORepository;
import com.ssafy.maryfarmuserservice.service.UserCService;
import com.ssafy.maryfarmuserservice.util.file.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class UserQuery {
    private final UserCService userCService;
    private final FileUploadService fileUploadService;
    private final FirstHomeViewDTORepository firstHomeViewDTORepository;
    private final MyFarmViewDTORepository myFarmViewDTORepository;

    @Operation(summary = "회원 조회", description = "특정 회원을 조회합니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> searchUser(@PathVariable("userId") String userId) {
        User user = userCService.findUser(userId);
        UserResponseDTO dto = UserResponseDTO.of(user);
        return ResponseEntity.ok(dto);
    }
    /*
        DiaryService에서 FeignClient로 사용중이므로 없애면 안됨.
     */
    @Operation(summary = "팔로워 목록 조회", description = "특정 회원의 팔로워 목록을 조회합니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/user/followers/{userId}")
    public ResponseEntity<?> searchFollower(@PathVariable("userId") String userId) {
        List<User> list = userCService.searchFollowers(userId);
        List<UserResponseDTO> resultDtos = new ArrayList<>();
        for(User u : list) {
            resultDtos.add(UserResponseDTO.of(u));
        }
        return ResponseEntity.ok(resultDtos);
    }

    @Operation(summary = "유저 메인 홈화면 조회", description = "특정 회원의 메인 홈화면을 조회합니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = FirstHomeViewDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/user/home/{userId}")
    public ResponseEntity<?> searchHome(@PathVariable("userId") String userId) {
        Optional<FirstHomeViewDTO> resultDto = firstHomeViewDTORepository.findByUserId(userId);
        return ResponseEntity.ok(resultDto.get());
    }

    @Operation(summary = "유저 내농장 화면 조회", description = "특정 회원의 내농장 화면을 조회합니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = MyFarmViewDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/user/farm/{userId}")
    public ResponseEntity<?> searchFarm(@PathVariable("userId") String userId) {
        Optional<MyFarmViewDTO> resultDto = myFarmViewDTORepository.findByUserId(userId);
        return ResponseEntity.ok(resultDto.get());
    }
}
