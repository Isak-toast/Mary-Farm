package com.numberONE.maryfarm.Retrofit;

import com.numberONE.maryfarm.Retrofit.Board.BoardArticle;
import com.numberONE.maryfarm.Retrofit.Board.BoardWrite;
import com.numberONE.maryfarm.Retrofit.Diary.DiaryInit;
import com.numberONE.maryfarm.Retrofit.Diary.DiaryTopRecommend;
import com.numberONE.maryfarm.Retrofit.Diary.UserItem;
import com.numberONE.maryfarm.Retrofit.dto.FirstHomeView.FirstHomeViewDTO;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface RetrofitApiSerivce {

//     diary api 시작

    // 새 일지 작성
    @Multipart
    @POST("maryfarm-plant-service/api/diary/init")
    Call<DiaryInit> postInitFeed(@Part MultipartBody.Part file
            , @PartMap Map<String, RequestBody> params);

    // 기존 일지에 일지 추가
    @Multipart
    @POST("maryfarm-plant-service/api/diary/add")
    Call<DiaryInit> postAddFeed(@Part MultipartBody.Part file
            , @PartMap Map<String,RequestBody> params);

    // 팔로워 피드 가져오기
    //@GET("maryfarm-plant-service/api/user/home/{userid}")
    @GET("api/user/home/{userid}")
    Call<FirstHomeViewDTO> getFollowerFeed(@Path("userid") String userid);

    // 하단 추천 피드 가져오기
    @GET("maryfarm-plant-service/api/diary/top")
    Call<List<DiaryTopRecommend>> getOthersFeed();

//    diary api 끝

//    board api 시작

    // 게시판 지역에 해당하는 글 전부 가져오기
    @POST("maryfarm-board-service/api/board/search")
    Call<List<BoardArticle>> postRegion(@Body String type);

    // 글 하나에 해당하는 정보 가져오기 ( 글 + 글에 해당하는 댓글 )
    @GET("maryfarm-board-service/api/board/search/{articleId}")
    Call<BoardArticle> getArticles(@Path("articleId") String articleId);

    // 게시판 게시글 작성
    @POST("maryfarm-board-service/api/board/create")
    Call<Void> writeArticle(@Body BoardWrite boardWrite);

    // 게시글 댓글 작성
    @POST("maryfarm-board-service/api/board/comment/create")
    Call<Void> writeCommnet(@Body BoardWrite boardWrite);

//      board api 끝
}
