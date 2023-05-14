package com.hacking.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "draftDataKnowledgeRace")
public class DraftData {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "raceLegID")
    String raceLegID;

    @ColumnInfo(name = "testID")
    String testID;

    @ColumnInfo(name = "data")
    String data;

    @ColumnInfo(name = "createAt")
    Long createAt;

    @ColumnInfo(name = "workTime")
    int workTime;

    @ColumnInfo(name = "timeTakeTest")
    int timeTakeTest;

    public DraftData(String raceLegID, String testID, String data, int workTime) {
        createAt = System.currentTimeMillis();
        timeTakeTest = 0;
        this.raceLegID = raceLegID;
        this.testID = testID;
        this.workTime =workTime;
        this.data = data;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaceLegID() {
        return raceLegID;
    }

    public void setRaceLegID(String raceLegID) {
        this.raceLegID = raceLegID;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "DraftData{" +
                "id=" + id +
                ", raceLegID='" + raceLegID + '\'' +
                ", testID='" + testID + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
