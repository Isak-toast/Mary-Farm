package com.ssafy.maryfarmboardservice.api.controller.article;

import com.ssafy.maryfarmboardservice.api.dto.article.CreateArticleRequestDTO;
import com.ssafy.maryfarmboardservice.api.dto.comment.ArticleCommentRequestDTO;
import com.ssafy.maryfarmboardservice.client.service.user.UserServiceClient;
import com.ssafy.maryfarmboardservice.domain.board.Article;
import com.ssafy.maryfarmboardservice.domain.board.ArticleComment;
import com.ssafy.maryfarmboardservice.kafka.producer.article.ArticleProducer;
import com.ssafy.maryfarmboardservice.service.article.ArticleCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ArticleCommand {
    private final ArticleCService articleCService;
    private final UserServiceClient userServiceClient;
    private final ArticleProducer articleProducer;
    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.", tags = { "Article Command" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/board/create")
    public ResponseEntity<?> createArticle(@RequestBody CreateArticleRequestDTO dto) throws IOException {
        Article article = articleCService.saveArticle(dto.getUserId(),dto.getUserName(),dto.getType(),dto.getTitle(),dto.getContent(),
                dto.getProfilePath());
        return ResponseEntity.ok(article.getId());
    }

    @Operation(summary = "게시글 댓글 작성", description = "특정 게시글의 댓글을 작성합니다.", tags = { "Article Command" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/board/comment/create")
    public ResponseEntity<?> createArticleComment(@RequestBody ArticleCommentRequestDTO dto) throws IOException {
        ArticleComment comment = articleCService.saveArticleComment(dto.getArticleId(),dto.getUserId(),dto.getUserName(),dto.getContent());
        return ResponseEntity.ok(comment.getId());
    }
}
