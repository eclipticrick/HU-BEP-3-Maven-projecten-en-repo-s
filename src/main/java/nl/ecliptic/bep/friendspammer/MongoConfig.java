package nl.ecliptic.bep.friendspammer;

import com.mongodb.MongoCredential;

class MongoConfig {
    static MongoCredential getMongoCredential(){
        String userName = "eclipticrick";
        String password = "12345678";
        String database = getDatabase();
        return MongoCredential.createCredential(userName, database, password.toCharArray());
    }
    static String getDatabase(){
        return "hu-bep-friendspammer";
    }
}
