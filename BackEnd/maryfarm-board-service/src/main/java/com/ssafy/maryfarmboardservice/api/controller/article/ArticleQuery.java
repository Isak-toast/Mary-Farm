package com.ssafy.maryfarmboardservice.api.controller.article;

import com.ssafy.maryfarmboardservice.api.dto.article.SearchArticleRequestDTO;
import com.ssafy.maryfarmboardservice.api.dto.query.DetailArticleView.DetailArticleDTO;
import com.ssafy.maryfarmboardservice.api.dto.query.TotalArticleView.ArticleDTO;
import com.ssafy.maryfarmboardservice.api.dto.query.TotalArticleView.SearchArticleByTypeDTO;
import com.ssafy.maryfarmboardservice.mongo_repository.DetailArticleView.DetailArticleDTORepository;
import com.ssafy.maryfarmboardservice.mongo_repository.TotalArticleView.SearchArticleByTypeDTORepository;
import com.ssafy.maryfarmboardservice.service.article.ArticleCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ArticleQuery {
    private final ArticleCService articleCService;
    private final DetailArticleDTORepository detailArticleDTORepository;
    private final SearchArticleByTypeDTORepository searchArticleByTypeDTORepository;
    @Operation(summary = "특정 타입의 전체 게시글 화면 조회", description = "특정 타입의 전체 게시글 화면을 조회합니다.", tags = { "Board Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ArticleDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/board/search")
    public ResponseEntity<?> searchArticleAll(@RequestBody SearchArticleRequestDTO dto) throws IOException {
        Optional<SearchArticleByTypeDTO> resultDto = searchArticleByTypeDTORepository.findByType(dto.getType());
        List<ArticleDTO> resultDtos = resultDto.get().getArticles();
        return ResponseEntity.ok(resultDtos);
    }

    @Operation(summary = "게시글 상세 화면 조회", description = "게시글의 상세 화면을 조회합니다.", tags = { "Board Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = DetailArticleDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/board/search/{articleId}")
    public ResponseEntity<?> searchDetailArticle(@PathVariable("articleId") String articleId) throws IOException {
        Optional<DetailArticleDTO> resultDto = detailArticleDTORepository.findByArticleId(articleId);
        articleCService.addViews(articleId);
        return ResponseEntity.ok(resultDto.get());
    }
}
