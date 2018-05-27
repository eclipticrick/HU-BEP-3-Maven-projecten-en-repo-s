package nl.ecliptic.bep.friendspammer;

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


    public static void main(String ...args) {
        logger.debug("test");
    }

}
