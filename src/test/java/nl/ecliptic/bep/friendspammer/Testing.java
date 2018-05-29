package nl.ecliptic.bep.friendspammer;

import nl.ecliptic.bep.friendspammer.MongoSaver;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import nl.ecliptic.bep.friendspammer.resources.HistoryRecord;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Testing {
    @Test public void test_fail() {
        // fail(":)");
    }
    @Test public void getEmailHistoryTest() {
        List<HistoryRecord> historyInProgram = MongoSaver.getEmailHistory();
        //List<HistoryRecord> historyFromMongoDB = new ArrayList<>();

        assertThat(historyInProgram.size(), is(not(equalTo(0))));
    }
}