package com.ssafy.maryfarmuserservice.api.controller.user;

import com.ssafy.maryfarmuserservice.api.dto.query.response.UserResponseDTO;
import com.ssafy.maryfarmuserservice.api.dto.user.*;
import com.ssafy.maryfarmuserservice.domain.user.Recommend;
import com.ssafy.maryfarmuserservice.domain.user.Tier;
import com.ssafy.maryfarmuserservice.domain.user.User;
import com.ssafy.maryfarmuserservice.service.UserCService;
import com.ssafy.maryfarmuserservice.util.file.dto.FileDetail;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UserCommand {
    private final UserCService userCService;
    private final FileUploadService fileUploadService;
    @Operation(summary = "회원 가입 요청", description = "회원 정보를 등록합니다.", tags = { "User Command" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/auth/user/signup")
    public ResponseEntity<?> saveUser(@RequestBody CreateUserRequestDTO dto) {
        User user = User.of(dto.getKakaoId(), dto.getUserName(), Tier.씨앗);
        User saveUser = userCService.saveUser(user);
        return ResponseEntity.ok(saveUser.getId());
    }

    @Operation(summary = "로그인 요청", description = "회원 정보를 통해 로그인합니다.", tags = { "User Command" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/auth/user/signin")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO dto) {
        User user = userCService.loginUser(dto.getKakaoId());
        if(user==null) return ResponseEntity.ok(0);
        else return ResponseEntity.ok(1);
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.", tags = { "User Command" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/user/modify")
    public ResponseEntity<?> modifyUser(@RequestPart MultipartFile image, @RequestPart ModifyUserRequestDTO dto) {
        FileDetail saveFile = fileUploadService.save(image);
        User user = userCService.updateUser(dto.getUserId(), dto.getUserName(),saveFile.getPath());
        return ResponseEntity.ok(user.getId());
    }

    @Operation(summary = "실제 텃밭 등록", description = "실제 텃밭 주소를 등록합니다.", tags = { "User Command" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/user/land/regist")
    public ResponseEntity<?> registLand(@RequestBody LandRegistRequestDTO dto) {
        User user = userCService.registLand(dto.getUserId(), dto.getLatitude(),dto.getLongitude());
        return ResponseEntity.ok(user.getId());
    }

    @Operation(summary = "추천용 응답 저장", description = "작물 추천을 받기 위한 응답을 저장합니다.", tags = { "User Command" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/user/recommend")
    public ResponseEntity<?> recommendPlant(@RequestBody UserRecommendRequestDTO dto) {
        String fullCode = dto.getMagnitude()+"/"+dto.getColor()+"/"+dto.getSeason()+"/"
                +dto.getPrice()+"/"+dto.getSize()+"/"+dto.getPeriod();
        Recommend recommend = userCService.saveRecommend(dto.getUserId(), fullCode);
        return ResponseEntity.ok(recommend.getId());
    }
}
