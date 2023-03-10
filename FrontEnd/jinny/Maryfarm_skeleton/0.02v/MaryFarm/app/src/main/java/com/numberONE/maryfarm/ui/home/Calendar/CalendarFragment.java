package com.numberONE.maryfarm.ui.home.Calendar;

import static com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_RANGE;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Calendar.CalendarDateModel;
import com.numberONE.maryfarm.Retrofit.Calendar.CalendarPickModel;
import com.numberONE.maryfarm.Retrofit.Calendar.ItemModel;
import com.numberONE.maryfarm.Retrofit.Calendar.MemoModel;
import com.numberONE.maryfarm.Retrofit.Calendar.RetrofitFactory;
import com.numberONE.maryfarm.Retrofit.Calendar.RetrofitService;
import com.numberONE.maryfarm.databinding.FragmentCalendarBinding;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarFragment extends Fragment {
    private FragmentCalendarBinding binding;
// ?????? ??????
    public String userId = "123456";                            // ????????? ?????????
    public CalendarDay userSelectDate = CalendarDay.today();    // ???????????? ????????? ??????
    public static Integer userSelectCheckbokPosition = 0;       // ???????????? ????????? ????????????
    private final String TAG = this.getClass().getSimpleName();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
    @BindView(R.id.calendarView) MaterialCalendarView widget;
    // ??????????????????
    RecyclerView recyclerView_plants, recyclerView_memo;
    RecyclerView.LayoutManager layoutManager_plants, layoutManager_memo;
    RecyclerView.Adapter adapter_plants, adapter_memo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        ViewGroup view = binding.getRoot();
        ButterKnife.bind(this, view);

// ???????????? ?????? ???????????? ???????????? ????????????
        // ?????? ??????
        List<String> plantNameList = new ArrayList<>();
        final String[][] plantName = new String[1][10000];
        // ?????? ???
        List<String> plantCreatedAtList = new ArrayList<>();
        final String[][] plantCreatedAt = new String[1][10000];
        // ?????? ???
        List<String> plantHarvestTimeList = new ArrayList<>();
        final String[][] plantHarvestTime = new String[1][10000];

        //?????????????????? ??? ??????????????? ???????????? ??? ???????????? ??????
        CalendarDateModel calendarDate = new CalendarDateModel();
        calendarDate.userId = userId;
        calendarDate.year = userSelectDate.getYear();
        calendarDate.month = userSelectDate.getMonth();
        Log.i(TAG, "userSelectDate:" + userSelectDate);
        RetrofitService networkService = RetrofitFactory.create();
        networkService.getList(calendarDate)
            .enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response){
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse: ????????? ??????");
                    // ?????????????????? ???????????? ?????? ?????? ?????? ????????? ??? ?????? ????????? ?????? ??? ???
                    // Myadapter adapter = new Myadapter(response.body());
                    // binding.calendarPlantsType.setAdapter(adapter);
                    List<ItemModel> body = response.body();
                    for(ItemModel m : body) {
                        plantNameList.add(m.getPlantName());
                        plantCreatedAtList.add(m.getCreatedAt());
                        plantHarvestTimeList.add(m.getHarvestTime());
                    }
                    plantName[0] = plantNameList.stream().toArray(String[]::new);
                    plantCreatedAt[0] = plantCreatedAtList.stream().toArray(String[]::new);
                    plantHarvestTime[0] = plantHarvestTimeList.stream().toArray(String[]::new);
                    Log.i(TAG, "onResponse: "+plantName[0][0] + plantCreatedAt[0][0] + plantHarvestTime[0][0]);

                    // ????????? ??????
                    recyclerView_plants = binding.calendarPlantsType;
                    layoutManager_plants = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
                    recyclerView_plants.setLayoutManager(layoutManager_plants);
                    adapter_plants = new CalendarPlantsAdapter(plantName[0], plantCreatedAt[0], plantHarvestTime[0]);
                    recyclerView_plants.setAdapter(adapter_plants);
                }
            }
                @Override
                public void onFailure(Call<List<ItemModel>> call, Throwable t){
                    Log.e(TAG, "onFailure: ?????? ?????? ??????");
                    Log.e(TAG, "onFailure:", t);
                }
            });
//???????????? ???

        widget.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                userSelectDate = date;
                // ?????? ???????????? ???????????? ????????? ???????????? ?????? ???????????? (????????? ????????? ?????? ??????!)
//                CalendarDateModel calendarDate = new CalendarDateModel();
//                calendarDate.userId = userId;
//                calendarDate.year = date.getYear();
//                calendarDate.month = date.getMonth();
////                Log.i(TAG, "userSelectDate:" + date);
//                RetrofitService networkService = RetrofitFactory.create();
//                networkService.getList(calendarDate)
//                        .enqueue(new Callback<List<ItemModel>>() {
//                            @Override
//                            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response){
//                                if(response.isSuccessful()){
//                                    Log.i(TAG, "onResponse: ????????? ??????");
//                                    List<ItemModel> body = response.body();
                //                      ????????? ?????? ??? ??????
//                                    for(ItemModel m : body) {
//                                        plantNameList.add(m.getPlantName());
//                                        plantCreatedAtList.add(m.getCreatedAt());
//                                        plantHarvestTimeList.add(m.getHarvestTime());
//                                    }
//                                    plantName[0] = plantNameList.stream().toArray(String[]::new);
//                                    plantCreatedAt[0] = plantCreatedAtList.stream().toArray(String[]::new);
//                                    plantHarvestTime[0] = plantHarvestTimeList.stream().toArray(String[]::new);
////                                    Log.i(TAG, "onResponse: "+plantName[0][0] + plantCreatedAt[0][0] + plantHarvestTime[0][0]);
//
//                                    // ????????? ??????
//                                    recyclerView_plants = binding.calendarPlantsType;
//                                    layoutManager_plants = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
//                                    recyclerView_plants.setLayoutManager(layoutManager_plants);
//                                    adapter_plants = new CalendarPlantsAdapter(plantName[0], plantCreatedAt[0], plantHarvestTime[0]);
//                                    recyclerView_plants.setAdapter(adapter_plants);
//                                }
//                            }
//                            @Override
//                            public void onFailure(Call<List<ItemModel>> call, Throwable t){
//                                Log.e(TAG, "onFailure: ?????? ?????? ??????");
//                                Log.e(TAG, "onFailure:", t);
//                            }
//                        });
            }
        });
// ?????? ???????????? ?????? -> ?????? ?????? ????????????
        CalendarPlantsAdapter.setOnPlantCheckListener(new CalendarPlantsAdapter.OnPlantCheckListener() {
            //?????? ??????
            @Override
            public void onPlantCheck(View v, int pos, Boolean checked, String createdAt, String harvestTime, Integer checkboxId) {

                // ?????? ???????????? ??????
//                if (userSelectCheckbokPosition != checkboxId){
//                    CheckBox checkedbox = view.findViewById(userSelectCheckbokPosition);
//                    checkedbox.setChecked(false);
//                    userSelectCheckbokPosition = checkboxId;
//                }
                if(checked) {
                    binding.calendarView.addDecorator(new RangeDecorator(getActivity()));
                    widget.setSelectionMode(SELECTION_MODE_RANGE);

                    Log.i(TAG, "onPlantCheck:"+createdAt + harvestTime);
                    //Log.i(TAG, "onPlantCheck:"+ Integer.parseInt(createdAt.substring(createdAt.length()-2, createdAt.length())));
                    Integer startdate;
                    Integer enddate;
                    if (Integer.parseInt(createdAt.substring(createdAt.length()-5, createdAt.length()-3)) == userSelectDate.getMonth()) {
                        startdate = Integer.parseInt(createdAt.substring(createdAt.length()-2, createdAt.length()));
                    } else {
                        startdate = 1;
                    }
                    if(harvestTime != null) {
                        enddate = Integer.parseInt(harvestTime.substring(harvestTime.length()-2, harvestTime.length()));
                    } else{
                        enddate = CalendarDay.today().getDay();
                    }
                    Log.i(TAG, "onPlantCheck:" + startdate + enddate);
                    for(int i=startdate;i<=enddate;i++){
                        widget.setDateSelected(CalendarDay.from(userSelectDate.getYear(),userSelectDate.getMonth(),i), true);
                    }
                } else {
                    widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
                    binding.calendarView.addDecorator(new DayDecorator(getActivity()));
                }
            }
        });
// ?????? ?????? ???
        binding.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(
                    @NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                userSelectDate = date;

                CalendarPickModel calendarPick = new CalendarPickModel();
                calendarPick.userId = userId;
                calendarPick.year = userSelectDate.getYear();
                calendarPick.month = userSelectDate.getMonth();
                calendarPick.day = userSelectDate.getDay();
//                Log.i(TAG, "onDateSelected: ????????????"+calendarPick.toString().trim());
                RetrofitService networkService = RetrofitFactory.create();
                networkService.getPlant(calendarPick)
                        .enqueue(new Callback<List<MemoModel>>() {
                            @Override
                            public void onResponse(Call<List<MemoModel>> call, Response<List<MemoModel>> response){
                                Log.i(TAG, "onResponse ?????????: "+response);
                                if(response.isSuccessful()){
                                    Log.i(TAG, "onResponse: ????????? ?????? ??????");
                                    List<MemoModel> body = response.body();

                                    // [????????????, ???, ????????????, ?????????, ?????????, ??????] ????????? ?????? ????????? ???????????? ?????????
//                                    plantMemo = []
//                                    for(MemoModel m : body) {
//                                        plantMemo.add(m.getPlant(), m.getWater());
//                                    }
//                                    plantName[0] = plantNameList.stream().toArray(String[]::new);
//                                    plantCreatedAt[0] = plantCreatedAtList.stream().toArray(String[]::new);
//                                    plantHarvestTime[0] = plantHarvestTimeList.stream().toArray(String[]::new);
//                                    Log.i(TAG, "onResponse: "+plantName[0][0] + plantCreatedAt[0][0] + plantHarvestTime[0][0]);

                                    // ????????? ??????
                                    recyclerView_memo = binding.calendarPickDate;
                                    layoutManager_memo = new LinearLayoutManager(getActivity());
                                    recyclerView_memo.setLayoutManager(layoutManager_memo);
                                    adapter_memo = new CalendarPickPlantAdapter(body);
                                    recyclerView_memo.setAdapter(adapter_memo);
                                    binding.calendarPickDate.setVisibility(View.VISIBLE);
                                }
                            }
                            @Override
                            public void onFailure(Call<List<MemoModel>> call, Throwable t){
                                Log.e(TAG, "onFailure: ?????? ?????? ??????");
                                Log.e(TAG, "onFailure:", t);
                            }
                        });
//                if(selecteddate[0] == date){
//                    widget.setSelected(false);
//                    binding.calendarPickDate.setVisibility(View.GONE);
//                    selecteddate[0] = null;
//                } else {
//                    selecteddate[0] = date;
//                    binding.calendarView.addDecorator(new DayDecorator(getActivity()));
//                    widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);

//                }
            }
        });
    // ?????? ?????? ??????
        CalendarPickPlantAdapter.setOnItemClickListener(new CalendarPickPlantAdapter.OnItemClickListener() {
            //?????? ??????
            @Override
            public void onItemClick(View v, int pos, int id) {
                ImageButton calendar_water = v.findViewById(R.id.calendar_water);
                ImageButton calendar_scissors = v.findViewById(R.id.calendar_scissors);
                ImageButton calendar_pill = v.findViewById(R.id.calendar_pill);
                ImageButton calendar_shovel = v.findViewById(R.id.calendar_shovel);
                ImageButton calendar_note = v.findViewById(R.id.calendar_note);
                switch (v.getId()) {
                    case R.id.calendar_water:
                        if ( id == 1 ) {
                            widget.addDecorator(new EventDecorator(Color.BLUE, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } else {
                            widget.addDecorator(new EventDecorator(Color.WHITE, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } break;
                    case R.id.calendar_scissors:
                        if ( id == 1 ) {
                            widget.addDecorator(new EventDecorator(Color.MAGENTA, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } else {
                            widget.addDecorator(new EventDecorator(Color.WHITE, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } break;
                    case R.id.calendar_pill:
                        if ( id == 1 ) {
                            widget.addDecorator(new EventDecorator(Color.CYAN, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } else {
                            widget.addDecorator(new EventDecorator(Color.WHITE, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } break;
                    case R.id.calendar_shovel:
                        if ( id == 1 ) {
                            widget.addDecorator(new EventDecorator(Color.RED, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } else {
                            widget.addDecorator(new EventDecorator(Color.WHITE, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } break;
                    case R.id.calendar_note:

                        if ( id == 1 ) {
                            widget.addDecorator(new EventDecorator(Color.GREEN, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } else {
                            widget.addDecorator(new EventDecorator(Color.WHITE, Collections.singleton(widget.getSelectedDate()), getActivity()));
                        } break;
                }
            }
        });
// ?????? ?????????
        // ??? ?????? ????????? ???????????? ????????? ??????
        binding.calendarView.state()
                .edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.SATURDAY)) // ??? ???????????? ????????? ??????????????? ????????????
                .commit();
        // ???, ????????? ????????? ????????? ?????? (MonthArrayTitleFormatter??? ????????? ??????????????? ?????? setTitleFormatter()??? ?????????)
        binding.calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        binding.calendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));
        // ?????? ????????? ?????? ???, ?????? ?????? ????????? ??????
        binding.calendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader);
        // ?????? ?????? ??? ?????? ????????? ??????????????? ??????????????? ???
        binding.calendarView.setOnRangeSelectedListener(new OnRangeSelectedListener() {
            @Override
            public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
                // ?????? ????????? ?????? ?????????, ???????????? ????????? ???????????? ???????????? ????????? ????????? ????????? ?????? ?????? ????????????
                // UTC ????????? ???????????? ?????? ??? ????????????????????? ???????????? ????????? ????????? ????????? ?????? ???????????? ?????? ??????
                String startDay = dates.get(0).getDate().toString();
                String endDay = dates.get(dates.size() - 1).getDate().toString();
                Log.i(TAG, "????????? : " + startDay + ", ????????? : " + endDay);
                Log.i(TAG, "??????????????? : " + dates);
            }
        });
        // ?????? ?????? ??? ?????? ????????? ??????????????? ??????????????? ??????
        binding.calendarView.addDecorators(new DayDecorator(getActivity()),
                new SundayDecorator(),
                new SaturdayDecorator());

        // ?????? ????????? ???????????? ???/?????? ????????? ?????? ?????????
        binding.calendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                // CalendarDay?????? ???????????? LocalDate ???????????? ???????????? ???????????? ????????????
                // ????????? MaterialCalendarView?????? ???/??? ??????????????? ?????????????????? CalendarDay ????????? getDate()??? ???/?????? ?????? ?????? LocalDate ????????? ?????????
                // LocalDate??? ???????????? ????????? ????????????
                LocalDate inputText = day.getDate();
                String[] calendarHeaderElements = inputText.toString().split("-");
                StringBuilder calendarHeaderBuilder = new StringBuilder();
                calendarHeaderBuilder.append(calendarHeaderElements[0])
                        .append(" ")
                        .append(calendarHeaderElements[1]);
                return calendarHeaderBuilder.toString();
            }
        });
// ?????? ????????? ???
     return view;
    }

// ?????? ?????? ?????? ?????? ???
    @OnClick(R.id.pick_date)
    void onSelectedClicked() {
        showDatePickerDialog(getActivity(), widget.getSelectedDate(),
                (view, year, monthOfYear, dayOfMonth) ->
                        widget.setSelectedDate(CalendarDay.from(year, monthOfYear + 1, dayOfMonth))
        );
    }
    // ?????? ?????? ?????? ??? ?????? ??????
    public static void showDatePickerDialog(
            Context context, CalendarDay day,
            DatePickerDialog.OnDateSetListener callback) {
        if (day == null) {
            day = CalendarDay.today();
        }
        DatePickerDialog dialog = new DatePickerDialog(
                context, 0, callback, day.getYear(), day.getMonth() - 1, day.getDay()
        );
        dialog.show();
    }

// ????????? ???????????????
    // ?????? ??????
    private static class DayDecorator implements DayViewDecorator {
        private final Drawable drawable;
        public DayDecorator(Context context) {
            drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector);
        }
        // true??? ?????? ??? ?????? ????????? ?????? ????????? ??????????????? ????????????
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return true;
        }

        // ?????? ?????? ??? ?????? ????????? ??????????????? ??????????????? ??????
        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
            //view.addSpan(new StyleSpan(Typeface.BOLD));   // ?????? ?????? ?????? ???????????? ?????? ?????????
        }
    }
    // ????????? ??????
    private static class RangeDecorator implements DayViewDecorator {
        private final Drawable drawable;
        public RangeDecorator(Context context) {
            drawable = ContextCompat.getDrawable(context, R.drawable.calendar_range_selector);
        }
        // true??? ?????? ??? ?????? ????????? ?????? ????????? ??????????????? ????????????
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return true;
        }

        // ?????? ?????? ??? ?????? ????????? ??????????????? ??????????????? ??????
        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
            //view.addSpan(new StyleSpan(Typeface.BOLD));   // ?????? ?????? ?????? ???????????? ?????? ?????????
        }
    }
    // ????????? ?????????
    public class SundayDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();
        public SundayDecorator() {
        }
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            int sunday = day.getDate().with(DayOfWeek.SUNDAY).getDayOfMonth();
            return sunday == day.getDay();
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));
        }
    }
    // ????????? ?????????
    public class SaturdayDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();
        public SaturdayDecorator() {
        }
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            int sunday = day.getDate().with(DayOfWeek.SATURDAY).getDayOfMonth();
            return sunday == day.getDay();
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }
    }
    // ???????????? ??? ??????
    public class EventDecorator implements DayViewDecorator {

        //private final Drawable drawable;
        private int color;
        private HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates, Activity context) {
            //drawable = context.getResources().getDrawable(R.drawable.more);
            this.color = color;
            this.dates = new HashSet<>(dates);
        }
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }
        @Override
        public void decorate(DayViewFacade view) {
            //view.setSelectionDrawable(drawable);
            view.addSpan(new DotSpan(5, color)); // ???????????? ???
        }
    }
}

