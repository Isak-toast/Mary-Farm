package com.numberONE.maryfarm;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.kakao.sdk.user.UserApiClient;
import com.numberONE.maryfarm.databinding.ActivityMainBinding;
import com.numberONE.maryfarm.ui.board.BoardMainFragment;
import com.numberONE.maryfarm.ui.chat.ChatFragment;
import com.numberONE.maryfarm.ui.chat.ChatRoomFragment;
import com.numberONE.maryfarm.ui.diary.WriteFragment;
import com.numberONE.maryfarm.ui.home.HomeFragment;
import com.numberONE.maryfarm.ui.myfarm.MyfarmFragment;
import com.numberONE.maryfarm.ui.search.SearchMainFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //프래그먼트 전환용
    FragmentTransaction ft;

    //Drawer 조절용 토글 버튼 객체 생성
    ActionBarDrawerToggle barDrawerToggle;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //상단 메뉴 배경색 지정
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFFF));

        //하단 메뉴
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.menu_bottom_home, R.id.menu_bottom_chat, R.id.menu_bottom_write, R.id.menu_bottom_alarm, R.id.menu_bottom_farm)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        // 상단 로고
        getSupportActionBar().setIcon(R.drawable.logo_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // 상단 로고 위치 지정이 안 됌
//        getSupportActionBar().setIcon(R.drawable.logo_icon);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);   //홈 아이콘을 숨김처리합니다.
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
//        getSupportActionBar().setDisplayShowTitleEnabled(false); //액션바에 표시되는 제목의 표시유무를 설정합니다.

        //item icon 색조를 적용하지 않도록 , 이 설정 없을경우 item icon 전부 회색
        binding.drawerNav.setItemIconTintList(null);

//        drawerlayout
        binding.drawerNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.hamburger_1:
                        Toast.makeText(MainActivity.this,"이장님 말씀", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hamburger_2:
                        Toast.makeText(MainActivity.this,"텃밭학교 ", Toast.LENGTH_SHORT).show();
                        ft=getSupportFragmentManager().beginTransaction();
                        SearchMainFragment searchFragment=new SearchMainFragment();
                        ft.replace(R.id.main_activity,searchFragment);
                        ft.commit();
//                        FragmentManager fragmentManager=getSupportFragmentManager();
//                        fragmentManager.beginTransaction().add(R.id.main_activity,searchFragment).commit();
                        break;
                    case R.id.hamburger_3:
                        Toast.makeText(MainActivity.this,"마을회관", Toast.LENGTH_SHORT).show();
                        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                        BoardMainFragment boardFragment=new BoardMainFragment();
                        ft.replace(R.id.main_activity,boardFragment);
                        ft.commit();
                        break;
                    case R.id.hamburger_4:
                        Toast.makeText(MainActivity.this,"직거래 장터", Toast.LENGTH_SHORT).show();
                        break;
                        // 로그아웃 구현 필요
                    case R.id.logout:
                        Toast.makeText(MainActivity.this,"정상적으로 로그아웃되었습니다.",Toast.LENGTH_SHORT).show();
                        UserApiClient.getInstance();

                        break;

                }

                //클릭시 drawer 닫기
                binding.drawerLayout.closeDrawer(binding.drawerNav);

                return false;
            }
        });

        barDrawerToggle=new ActionBarDrawerToggle(this,binding.drawerLayout, R.string.app_name, R.string.app_name);
        //ActionBar( 제목줄)의 홈 or 업버튼의 위치에 토글아이콘 보이게
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        //햄버거 모양으로 보이도록 토글버튼 동기 맞추기
//        barDrawerToggle.syncState();
//
//        //햄버거 아이콘과 화살표 아이콘이 자동으로 변환환하도록
//        binding.drawerLayout.addDrawerListener(barDrawerToggle);

//        drawerlayout 끝


    } // onCreate 끝
    public void onChatFragmentChange(Integer index, String roomId) {
        if(index == 1){
            Bundle bundle = new Bundle();
            bundle.putString("roomId", roomId);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ChatRoomFragment fragment2 = new ChatRoomFragment();//프래그먼트2 선언
            fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
            transaction.replace(R.id.main_activity, fragment2)
                    .setReorderingAllowed(true)
                    .addToBackStack("ChatList")
                    .commit();
//            transaction.commit();
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_activity, ChatRoomFragment.class, null)
//                    // setReorderingAllowed(true) and the optional string argument for
//                    // addToBackStack() are both required if you want to use saveBackStack().
//                    .setReorderingAllowed(true)
//                    .addToBackStack("ChatList")
//                    .commit();
        }
    }
    //액션바의 메뉴를 클릭하는 이벤트를 듣는 메소드를 통해 클릭 상황 전달
    //토글 버튼이 클릭 상황을 인지하도록
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        barDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
    // 상단메뉴 다른 버튼
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        barDrawerToggle=new ActionBarDrawerToggle(this,binding.drawerLayout, R.string.app_name, R.string.app_name);
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }
    // 검색 버튼 로직 만들기
    SearchView.OnQueryTextListener queryTextListener =new SearchView.OnQueryTextListener() {
        @Override // 최종 검색을 위해 제출버튼 눌렀을 때
        public boolean onQueryTextSubmit(String query) {
            searchView.setQuery("",false);
            searchView.setIconified(true);
            Toast t = Toast.makeText(MainActivity.this,query,Toast.LENGTH_SHORT);
            t.show();
            return false;
        }

        @Override // 유저가 한글자 한글자 입력할 때
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
}