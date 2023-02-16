package com.numberONE.maryfarm.ui.AlgorithmPage;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecommendData {
    @Expose
    @SerializedName("cntntsNo") private String cntntsNo;
    @Expose
    @SerializedName("cntntsSj")private String cntntsSj;
//    private String cntntsPt;

    //    private String[] rtnStreFileNm;

//    public RecommendData(String cntntsNo, String cntntsSj, String cntntsPt) {
//        this.cntntsNo = cntntsNo.replaceAll("[^\\d]", "");
//        this.cntntsSj = cntntsSj.replaceAll("[^\\d]", "");
//        this.cntntsPt = new String("http://www.nongsaro.go.kr/cms_contents/301/" + cntntsNo + "_MF_ATTACH_01.jpg");
//        this.rtnStreFileNm = rtnStreFileNm.split("\\|");
//    }

    public RecommendData(String cntntsNo, String cntntsSj) {
        // 수정된 정규식 패턴으로 대괄호 안의 값을 추출합니다.
        this.cntntsNo = cntntsNo.replaceAll("\\[CDATA\\[\\s*(\\d+)\\s*\\]\\]", "$1");
        this.cntntsSj = cntntsSj.replaceAll("\\[CDATA\\[\\s*(\\S+)\\s*\\]\\]", "$1");
    }


    public String getCntntsNo() {
        return cntntsNo;
    }

    public void setCntntsNo(String cntntsNo) {
        this.cntntsNo = cntntsNo;
    }

    public String getCntntsSj() {
        return cntntsSj;
    }

    public void setCntntsSj(String cntntsSj) {
        this.cntntsSj = cntntsSj;
    }

    public String string() {
        return null;
    }

//    public String getCntntsPt() {
//        Log.d(TAG, "setCntntsPt: " + cntntsPt);
//        Log.d(TAG, "setCntntsPt URL : " + "http://www.nongsaro.go.kr/cms_contents/301/" + cntntsNo + "_MF_ATTACH_01.jpg");
//        return cntntsPt;
//    }
//
//    public void setCntntsPt(String cntntsPt) {
//        Log.d(TAG, "setCntntsPt: " + cntntsPt);
//        Log.d(TAG, "setCntntsPt URL : " + "http://www.nongsaro.go.kr/cms_contents/301/" + cntntsNo + "_MF_ATTACH_01.jpg");
//        this.cntntsPt = new String("http://www.nongsaro.go.kr/cms_contents/301/" + cntntsNo + "_MF_ATTACH_01.jpg");
//    }
//
//    public String[] getRtnStreFileNm() {
//        return rtnStreFileNm;
//    }
//
//    public void setRtnStreFileNm(String[] rtnStreFileNm) {
//        this.rtnStreFileNm = rtnStreFileNm;
//    }
//
//    public List<String> getUrlList() {
//        List<String> urlList = new ArrayList<>();
//        for (String url : rtnStreFileNm) {
//            urlList.add("http://www.nongsaro.go.kr/cms_contents/301/" + url);
//        }
//        return urlList;
//    }

}
