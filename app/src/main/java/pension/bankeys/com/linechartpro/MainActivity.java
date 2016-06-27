package pension.bankeys.com.linechartpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChartView view = (LineChartView) findViewById(R.id.mLineChartView);
        List<String> colum = new ArrayList<>();
        colum.add("100");
        colum.add("75");
        colum.add("50");
        colum.add("25");
        colum.add("00");
        List<String> row = new ArrayList<>();
        row.add("2016");
        row.add("2017");
        row.add("2018");
        row.add("2019");
        row.add("2020");
        view.setColumTxtList(colum);
        view.setRowTxtList(row);
        List<Score> list = new ArrayList<>();
        Score score1 = new Score();
        score1.setDate("2016");
        score1.setScore(75);
        list.add(score1);
        Score score2 = new Score();
        score2.setDate("2017");
        score2.setScore(30);
        list.add(score2);
        Score score3 = new Score();
        score3.setDate("2018");
        score3.setScore(50);
        list.add(score3);
        Score score4 = new Score();
        score4.setDate("2019");
        score4.setScore(80);
        list.add(score4);
        Score score5 = new Score();
        score5.setDate("2020");
        score5.setScore(10);
        list.add(score5);
        view.setList(list);
    }
}
