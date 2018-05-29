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

public class Testing {
    @Test public void test_fail() {
        // fail(":)");
    }
    @Test public void getEmailHistoryTest() {
        ArrayList<HistoryRecord> history = MongoSaver.getEmailHistory();
        //assertThat(history, hasItem(new HistoryRecord()));
        //assertThat(history.size(), greaterThan(4));
        assertThat(history.size(), is(not(equalTo(0))));
        assertThat(history.size(), is(not(equalTo(1))));
        assertThat(history.size(), is(not(equalTo(2))));
    }
}