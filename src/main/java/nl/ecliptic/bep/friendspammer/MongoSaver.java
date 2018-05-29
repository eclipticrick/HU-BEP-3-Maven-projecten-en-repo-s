package nl.ecliptic.bep.friendspammer;

import nl.ecliptic.bep.friendspammer.resources.HistoryRecord;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

public class MongoSaver {

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    static void saveEmail(String to, String from, String subject, String text, Boolean html) {
        String userName = "eclipticrick";
        String password = "12345678";
        String database = "hu-bep-friendspammer";
        MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());

        try (MongoClient mongoClient = new MongoClient(new ServerAddress("ds237620.mlab.com", 37620), credential, MongoClientOptions.builder().build()) ) {
            MongoDatabase db = mongoClient.getDatabase( database );
            MongoCollection<Document> c = db.getCollection("email");
            Document  doc = new Document ("to", to)
                    .append("from", from)
                    .append("subject", subject)
                    .append("text", text)
                    .append("asHtml", html);
            c.insertOne(doc);
        }
        catch (MongoException mongoException) {
            logger.info("ERROR WHILE SAVING TO MONGO");
            logger.error("mongoException", mongoException);
        }
    }

    static ArrayList<HistoryRecord> getEmailHistory() throws MongoException {
        String userName = "eclipticrick";
        String password = "12345678";
        String database = "hu-bep-friendspammer";

        MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());

        try (MongoClient mongoClient = new MongoClient(new ServerAddress("ds237620.mlab.com", 37620), credential, MongoClientOptions.builder().build()) ) {
            MongoDatabase db = mongoClient.getDatabase( database );
            MongoCollection<Document> c = db.getCollection("email");
            Iterator<Document> it = c.find().iterator();

            ArrayList<HistoryRecord> history = new ArrayList<>();
            while(it.hasNext()) {
                Document email = it.next();
                HistoryRecord historyRecord = new HistoryRecord();

                historyRecord.setTo     ( (String)email.get("to") );
                historyRecord.setFrom   ( (String)email.get("from") );
                historyRecord.setSubject( (String)email.get("subject") );
                historyRecord.setText   ( (String)email.get("text") );
                historyRecord.setAsHtml ( (String)email.get("asHtml") );

                history.add(historyRecord);
            }
            mongoClient.close();
            return history;
        }
        catch (MongoException mongoException) {
            logger.info("ERROR WHILE RETRIEVING FROM MONGO");
            logger.error("mongoException", mongoException);
            throw new MongoException("MongoException thrown");
        }
    }

    public static void main(String ...args) {
        logger.debug("test");
        logger.debug("test");
    }

}
