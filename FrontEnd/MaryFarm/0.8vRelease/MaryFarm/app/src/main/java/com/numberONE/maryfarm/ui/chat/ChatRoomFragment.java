package com.numberONE.maryfarm.ui.chat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.databinding.FragmentChatroomBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ua.naiksoftware.stomp.StompClient;

public class ChatRoomFragment extends Fragment {

    private FragmentChatroomBinding binding;
    private static final String TAG = "ChatRoomFragment";
    private static String roomId = "2c92808c86489b5101864939e4150002";
    private Adapter mAdapter;
    private List<String> mDataSet = new ArrayList<>();
    private StompClient mStompClient;
    private Disposable mRestPingDisposable;
    private final SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private RecyclerView mRecyclerView;
    private Gson mGson = new GsonBuilder().create();

    private CompositeDisposable compositeDisposable;
    RecyclerView recyclerView_chatroom;
    RecyclerView.LayoutManager layoutManager_chatroom;
    RecyclerView.Adapter adapter_chatroom;
    TextView chatRoomName;
    ImageView chattier;
    Button chatsend;

    //@SuppressLint("NewApi")는 해당 프로젝트의 설정 된 minSdkVersion 이후에 나온 API를 사용할때  warning을 없애고 개발자가 해당 APi를 사용할 수 있게 합니다.
    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatroomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            roomId = getArguments().getString("roomId"); // 프래그먼트1에서 받아온 값 넣기
            Log.i(TAG, "onCreateView: "+roomId);
        }
        String[] nickname = {"왕감자", "블루베리맘", "당근전문가", "성주꿀참외"};
        int[] tier = {R.drawable.tier1,R.drawable.tier2,R.drawable.tier5,R.drawable.tier8};

        chatRoomName = binding.chatRoomName;
        chatRoomName.setText(nickname[Integer.parseInt(roomId)]);
        chattier = binding.chatTitleTier;
        chattier.setImageResource(tier[Integer.parseInt(roomId)]);

        // 어댑터 연결
        recyclerView_chatroom = binding.messagesChatList;
        layoutManager_chatroom = new LinearLayoutManager(getActivity());
        recyclerView_chatroom.setLayoutManager(layoutManager_chatroom);
        adapter_chatroom = new ChatroomAdapter(roomId);
        recyclerView_chatroom.setAdapter(adapter_chatroom);
        // 전송 버튼 클릭시 메세지 게시
        chatsend = binding.sendButton;
        chatsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage;
                newMessage = binding.messageEditText.getText().toString();
                binding.chatsendContent.setText(newMessage);
                long now = System.currentTimeMillis();
                Date time = new Date(now);
                TimeZone tz;
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
                tz = TimeZone.getTimeZone("Asia/Seoul");
                dateFormat.setTimeZone(tz);
                String getTime = dateFormat.format(time);
                Log.i(TAG, "onBindViewHolder: i는"+getTime);
                binding.chatsendDate.setText(getTime);
                binding.chatsendvisible.setVisibility(View.VISIBLE);
                binding.messageEditText.setText("");
            }
        });
//        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://i8b308.p.ssafy.io:8000/maryfarm-chat-service/ws-chat/websocket");
//        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
//            switch (lifecycleEvent.getType()) {
//                case OPENED:
//                    Log.d(TAG, "Stomp connection opened");
//                    break;
//                case ERROR:
//                    Log.e(TAG, "Error", lifecycleEvent.getException());
//                    break;
//                case CLOSED:
//                    Log.d(TAG, "Stomp connection closed");
//                    break;
//                case FAILED_SERVER_HEARTBEAT:
////                    toast("Stomp failed server heartbeat");
//                    break;
//            }
//        });
//        mStompClient.connect();
////
//        mStompClient.topic("/topic/group/"+roomId).subscribe(topicMessage -> {
//            Log.d(TAG+"토픽", topicMessage.getPayload());
////            JSONParser parser=new JSONParser();
////            Object obj=parser.parse(topicMessage.getPayload());
//        });
//
//        mStompClient.send("/api/message/send", "My first STOMP message!").subscribe();
//        stompClient.send("send할 주소", jsonObject.toJSONString()).subscribe();

//        mRecyclerView = binding.recyclerView;
//        // 리스트뷰에 보여줄 목록 데이트 (테스트 용)
//        ArrayList<HashMap<String, String>> list = new ArrayList<>();
//
//        HashMap<String, String> item01 = new HashMap<>();
//        item01.put("key01", "목록01");
//        item01.put("key02", "내용입니다01");
//        list.add(item01);
//
//        HashMap<String, String> item02 = new HashMap<>();
//        item02.put("key01", "목록02");
//        item02.put("key02", "내용입니다02");
//        list.add(item02);
//
//        // STEP01. 레이아웃의 리스트뷰를 mListview라는 ListView로 선언해준다
////        ListView mListView = findViewById(R.id.mListView);
//
//        // STEP02. From 설정
//        String[] from = {"key01", "key02"};
//
//        // STEP03. To 설정
//        int[] to = new int[] {android.R.id.text1, android.R.id.text2};
//
//        mAdapter = new SimpleAdapter(mDataSet);
////        mAdapter.setHasStableIds(true);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
//
//        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://i8b308.p.ssafy.io:8000/maryfarm-user-service/ws-chat/websocket");
//
//        resetSubscriptions();

        return root;
    }

//    public void disconnectStomp(View view) {
//        mStompClient.disconnect();
//    }
//
//    public static final String LOGIN = "login";
//
//    public static final String PASSCODE = "passcode";
//
//    public void connectStomp(View view) {
//
//        List<StompHeader> headers = new ArrayList<>();
//        headers.add(new StompHeader(LOGIN, "guest"));
//        headers.add(new StompHeader(PASSCODE, "guest"));
//
//        mStompClient.withClientHeartbeat(1000).withServerHeartbeat(1000);
//
//        resetSubscriptions();
//
//        Disposable dispLifecycle = mStompClient.lifecycle()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(lifecycleEvent -> {
//                    switch (lifecycleEvent.getType()) {
//                        case OPENED:
//                            toast("Stomp connection opened");
//                            break;
//                        case ERROR:
//                            Log.e(TAG, "Stomp connection error", lifecycleEvent.getException());
//                            toast("Stomp connection error");
//                            break;
//                        case CLOSED:
//                            toast("Stomp connection closed");
//                            resetSubscriptions();
//                            break;
//                        case FAILED_SERVER_HEARTBEAT:
//                            toast("Stomp failed server heartbeat");
//                            break;
//                    }
//                });
//
//        compositeDisposable.add(dispLifecycle);
//
//        // Receive greetings
//        Disposable dispTopic = mStompClient.topic("/topic/group/"+roomId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(topicMessage -> {
//                    Log.d(TAG, "Received " + topicMessage.getPayload());
//                    addItem(mGson.fromJson(topicMessage.getPayload(), EchoModel.class));
//                }, throwable -> {
//                    Log.e(TAG, "Error on subscribe topic", throwable);
//                });
//
//        compositeDisposable.add(dispTopic);
//
//        mStompClient.connect(headers);
//    }
//
//    public void sendEchoViaStomp(View v) {
////        if (!mStompClient.isConnected()) return;
//        compositeDisposable.add(mStompClient.send("/topic/group/"+roomId, "Echo STOMP " + mTimeFormat.format(new Date()))
//                .compose(applySchedulers())
//                .subscribe(() -> {
//                    Log.d(TAG, "STOMP echo send successfully");
//                }, throwable -> {
//                    Log.e(TAG, "Error send STOMP echo", throwable);
//                    toast(throwable.getMessage());
//                }));
//    }
//
//    public void sendEchoViaRest(View v) {
//        mRestPingDisposable = RestClient.getInstance().getExampleRepository()
//                .sendRestEcho("Echo REST " + mTimeFormat.format(new Date()))
//                .compose(applySchedulers())
//                .subscribe(() -> {
//                    Log.d(TAG, "REST echo send successfully");
//                }, throwable -> {
//                    Log.e(TAG, "Error send REST echo", throwable);
//                    toast(throwable.getMessage());
//                });
//    }
//
//    private void addItem(EchoModel echoModel) {
//        mDataSet.add(echoModel.getEcho() + " - " + mTimeFormat.format(new Date()));
//        mAdapter.notifyDataSetChanged();
//        mRecyclerView.smoothScrollToPosition(mDataSet.size() - 1);
//    }
//
//    private void toast(String text) {
//        Log.i(TAG, text);
//        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
//    }
//
//    protected CompletableTransformer applySchedulers() {
//        return upstream -> upstream
//                .unsubscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    private void resetSubscriptions() {
//        if (compositeDisposable != null) {
//            compositeDisposable.dispose();
//        }
//        compositeDisposable = new CompositeDisposable();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//            mStompClient.disconnect();
//            if (mRestPingDisposable != null) mRestPingDisposable.dispose();
//            if (compositeDisposable != null) compositeDisposable.dispose();
        super.onDestroy();
        binding = null;
    }
}