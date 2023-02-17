package com.numberONE.maryfarm.Retrofit.Board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardArticle {
    @SerializedName("articleId")
    @Expose
    private String articleId;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("views")
    @Expose
    private int views;

    @SerializedName("likes")
    @Expose
    private int likes;

    @SerializedName("commentCount")
    @Expose
    private int commentCnt;

    @SerializedName("createdDate")
    @Expose
    private String createdDate;

    @SerializedName("lastModifiedDate")
    @Expose
    private String lastModifiedDate;

//    @SerializedName("comments")
//    @Expose
//    private List<BoardComments> comments;

    private HashMap<Object,Object> comments =new HashMap<>();

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

//    public List<BoardComments> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<BoardComments> comments) {
//        this.comments = comments;
//    }


    public HashMap<Object, Object> getComments() {
        return comments;
    }

    public void setComments(HashMap<Object, Object> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "BoardArticle{" +
                "articleId='" + articleId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", views=" + views +
                ", likes=" + likes +
                ", commentCnt=" + commentCnt +
                ", createdDate='" + createdDate + '\'' +
                ", lastModifiedDate='" + lastModifiedDate + '\'' +
                ", comments=" + comments +
                '}';
    }
}
