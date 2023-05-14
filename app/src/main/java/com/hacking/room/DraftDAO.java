package com.hacking.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface DraftDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(DraftData data);

    @Query("DELETE FROM draftDataKnowledgeRace")
    void deleteAll();

    @Query("DELETE FROM draftDataKnowledgeRace WHERE createAt")
    void deleteV2();

//    currrent - createat>=1ngay;

    @Query("SELECT * FROM draftDataKnowledgeRace WHERE raceLegID =:raceLegID AND testID=:testID")
    Flowable<DraftData> getDraftDataByID(String raceLegID, String testID);

    @Query("UPDATE draftDataKnowledgeRace SET data=:newData WHERE raceLegID=:raceLegID AND testID=:testID ")
    Completable update(String newData, String raceLegID, String testID);

    @Query("UPDATE draftDataKnowledgeRace SET timeTakeTest = timeTakeTest+ :timeTakeTestUpdate,data =:newData WHERE raceLegID=:raceLegID AND testID=:testID ")
    Completable update(int timeTakeTestUpdate, String raceLegID, String testID, String newData);


}
