package com.ssafy.maryfarmboardservice.service.article;

import com.ssafy.maryfarmboardservice.domain.board.Article;
import com.ssafy.maryfarmboardservice.domain.board.ArticleComment;
import com.ssafy.maryfarmboardservice.domain.board.BoardType;
import com.ssafy.maryfarmboardservice.jpa_repository.article.ArticleCommentCRepository;
import com.ssafy.maryfarmboardservice.jpa_repository.article.ArticleCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleCService {
    private final RedisTemplate redisTemplate;
    private final ArticleCRepository articleCRepository;
    private final ArticleCommentCRepository articleCommentCRepository;

    @Transactional
    public Article saveArticle(String userId, String userName, String type, String title, String content) {
        Article article = Article.of(userId, userName, BoardType.valueOf(type), title, content);
        Article saveArticle = articleCRepository.save(article);
        return saveArticle;
    }

    @Transactional
    public ArticleComment saveArticleComment(String articleId, String userId, String userName, String content) {
        Optional<Article> article = articleCRepository.findById(articleId);
        ArticleComment comment = ArticleComment.of(article.get(), userId, userName, content);
        return articleCommentCRepository.save(comment);
    }

    public void addViews(String articleId) {
        addViewCntToRedis(articleId);
    }

    public void addViewCntToRedis(String articleId) {
        String key = "articleViewCnt::"+articleId;
        //hint 캐시에 값이 없으면 레포지토리에서 조회 있으면 값을 증가시킨다.
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if(valueOperations.get(key)==null) {
            valueOperations.set(
                    key,
                    String.valueOf(articleCRepository.findById(articleId).get().getViews()),
                    Duration.ofMinutes(5));
            valueOperations.increment(key);
        }
        else valueOperations.increment(key);
        log.info("value:{}",valueOperations.get(key));
    }

    @Transactional
    @Scheduled(cron = "0 0/1 * * * ?")
    public void deleteViewCntCacheFromRedis() {
        Set<String> redisKeys = redisTemplate.keys("articleViewCnt*");
        Iterator<String> it = redisKeys.iterator();
        while (it.hasNext()) {
            String data = it.next();
            String articleId = data.split("::")[1];
            Integer viewCnt = Integer.parseInt((String) redisTemplate.opsForValue().get(data));
            Article article = articleCRepository.findById(articleId).get();
            article.addViews(viewCnt);
            log.info("Article add View complete!");
            redisTemplate.delete(data);
        }
    }
}
