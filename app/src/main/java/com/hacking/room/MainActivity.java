package com.hacking.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnGetData;
    Button btnUpDateData;
    Button btnUpDateDataAfterSeconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAddData);
        btnGetData = findViewById(R.id.btnGetData);
        btnUpDateData = findViewById(R.id.btnUpDateData);
        btnUpDateDataAfterSeconds = findViewById(R.id.btnUpDateDataAfterSeconds);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        btnUpDateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
        btnUpDateDataAfterSeconds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAfterOneSecond();
            }
        });
    }

    private void updateAfterOneSecond() {
        String newData = "new data test v2 id 49 897" + "lần" + new Random().nextInt(100);
        Completable completable = AppDatabase.getInstance(this).draftDao()
                .update(1, "49", "897",newData);
        completable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                    Toast.makeText(MainActivity.this, "update user thành công"
                            , Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(MainActivity.this, "" + throwable.getClass().getCanonicalName()
                            , Toast.LENGTH_SHORT).show();
                });
    }

    private void updateData() {
        Completable completable = AppDatabase.getInstance(this).draftDao().
                update("new data test id 49 897" + "lần" + new Random().nextInt(100), "49", "897");
        completable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                    Toast.makeText(MainActivity.this, "update user thành công"
                            , Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(MainActivity.this, "" + throwable.getClass().getCanonicalName()
                            , Toast.LENGTH_SHORT).show();

                });
    }

    private void getData() {
        Flowable<DraftData> flowable = AppDatabase.getInstance(this).draftDao()
                .getDraftDataByID("61", "529");
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DraftData>() {
                    @Override
                    public void accept(DraftData data) throws Exception {
                        Log.d("testing", data.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("testing", throwable.getClass().getCanonicalName());
                    }
                });


    }


    private void addData() {
        Random random = new Random();
        String raceLegID = random.nextInt(100) + "";
        String testID = random.nextInt(1000) + "";
        String dataTest = "đây là dữ liệu bài test" + " " + raceLegID + " " + testID;
        DraftData draftData = new DraftData(raceLegID, testID, dataTest, 40);
        Completable completable = AppDatabase.getInstance(this).draftDao().insert(draftData);
        completable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                    Toast.makeText(MainActivity.this, "insert user thành công"
                            , Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(MainActivity.this, "" + throwable.getClass().getCanonicalName()
                            , Toast.LENGTH_SHORT).show();

                });
    }


}