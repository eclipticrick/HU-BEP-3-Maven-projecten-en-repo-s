package nl.ecliptic.bep.friendspammer;

import com.mongodb.MongoCredential;

class MongoConfig {
    private static String database = "hu-bep-friendspammer";

    private MongoConfig() {
        throw new IllegalStateException("This is a utility class");
    }

    static MongoCredential getMongoCredential(){
        String userName = "eclipticrick";
        String password = "12345678";
        return MongoCredential.createCredential(userName, database, password.toCharArray());
    }
    static String getDatabase(){
        return database;
    }
}
