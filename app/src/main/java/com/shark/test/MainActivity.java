package com.shark.test;

import android.app.Activity;
import android.os.Bundle;

import com.shark.chart.point.ChartDatePoint;
import com.shark.chart.scene.DailyScene;
import com.shark.chart.scene.DailyWeightScene;
import com.shark.chart.view.ChartView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends Activity {
    private ChartView weightChart;
    private DailyWeightScene dailyWeightScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailyWeightScene = new DailyWeightScene(this, 50);
        weightChart = (ChartView) findViewById(R.id.weight_chartview);
        weightChart.setSceneAdapter(dailyWeightScene);
        dailyWeightScene.setOnChartDatePointChangedListener(new DailyWeightScene.OnChartDatePointChangedListener() {
            @Override
            public void onChartDatePointChanged(ChartDatePoint point) {

            }
        });
        dailyWeightScene.setDataLoader(new DailyScene.DataLoader() {
            @Override
            public List<ChartDatePoint> getData(Calendar baseDay, DailyScene.DayType dayType, int dayCount) {

                List<ChartDatePoint> list = new ArrayList<>();

                switch (dayType) {
                    case HISTORY_DAY:
                        for (int i = 0; i < dayCount; i++) {
                            Calendar date = (Calendar) baseDay.clone();
                            date.add(Calendar.DAY_OF_MONTH, -1 * (i + 1));
                            ChartDatePoint dateVo = new ChartDatePoint();
                            dateVo.setHasData(false);
                            dateVo.setY(50);
                            dateVo.setCalendar(date);
                            list.add(0, dateVo);
                        }
                        break;
                    case FUTURE_DAY:
                        for (int i = 0; i < dayCount; i++) {
                            Calendar date = (Calendar) baseDay.clone();
                            date.add(Calendar.DAY_OF_MONTH, i + 1);
                            ChartDatePoint dateVo = new ChartDatePoint();
                            dateVo.setHasData(false);
                            dateVo.setY(50);
                            dateVo.setCalendar(date);
                            list.add(dateVo);
                        }
                        break;
                    case RECENT_DAY:
                        for (int i = 0; i < dayCount / 2; i++) {
                            Calendar date = (Calendar) baseDay.clone();
                            date.add(Calendar.DAY_OF_MONTH, -1 * (i + 1));
                            ChartDatePoint dateVo = new ChartDatePoint();
                            dateVo.setHasData(false);
                            dateVo.setY(50);
                            dateVo.setCalendar(date);
                            list.add(0, dateVo);
                        }

                        ChartDatePoint today = new ChartDatePoint();
                        today.setY(50);
                        today.setHasData(false);
                        today.setCalendar(baseDay);
                        list.add(today);

                        for (int i = 0; i < dayCount / 2; i++) {
                            Calendar date = (Calendar) baseDay.clone();
                            date.add(Calendar.DAY_OF_MONTH, i + 1);
                            ChartDatePoint dateVo = new ChartDatePoint();
                            dateVo.setHasData(false);
                            dateVo.setY(50);
                            dateVo.setCalendar(date);
                            list.add(dateVo);
                        }

                        break;
                }
                return list;
            }

        });

    }
}
