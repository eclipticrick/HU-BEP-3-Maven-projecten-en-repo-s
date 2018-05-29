package nl.ecliptic.bep.friendspammer;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import nl.ecliptic.bep.friendspammer.resources.HistoryRecord;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Testing {
    @Test public void test_fail() {
        // fail(":)");
    }
    @Test public void getEmailHistoryTest() {
        List<HistoryRecord> ACTUAL = MongoSaver.getEmailHistory();
        List<HistoryRecord> EXPECTED = new ArrayList<>();


        MongoCredential credential = MongoConfig.getMongoCredential();
        String database = MongoConfig.getDatabase();

        try (MongoClient mongoClient = new MongoClient(new ServerAddress("ds237620.mlab.com", 37620), credential, MongoClientOptions.builder().build()) ) {
            MongoDatabase db = mongoClient.getDatabase( database );
            MongoCollection<Document> c = db.getCollection("email");

            for (Document email : c.find()) {
                HistoryRecord historyRecord = new HistoryRecord();
                historyRecord.setTo((String) email.get("to"));
                historyRecord.setFrom((String) email.get("from"));
                historyRecord.setSubject((String) email.get("subject"));
                historyRecord.setText((String) email.get("text"));
                historyRecord.setAsHtml((boolean) email.get("asHtml"));

                EXPECTED.add(historyRecord);
            }
            mongoClient.close();

            assertThat(ACTUAL.size(), is(not(equalTo(0))));
            assertThat(ACTUAL.size(), is(equalTo(EXPECTED.size())));

        }
        catch (MongoException ignore) {
            // ignore
        }
    }
}