/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restjee.dao.provider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author tuvshuu
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MongoClientProvider {

    private MongoClient mongoClient = null;
    private String databaseName;

    @Lock(LockType.READ)
    public MongoClient getMongoClient() {
        if (mongoClient == null) {
            init();
        }
        return mongoClient;
    }

    @Lock(LockType.READ)
    public String getDataName() {
        if (databaseName == null) {
            init();
        }
        return databaseName;
    }

    @PostConstruct
    public void init() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("config.properties");
            Properties properties = new Properties();
            properties.load(input);

            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            mongoClient = new MongoClient(new ServerAddress(properties.getProperty("mongodb.host"),
                    27017),
                    //Integer.parseInt(properties.getProperty("mongodb.port"), 27017)),
                    MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
            databaseName = properties.getProperty("mongodb.db");
            System.out.println("\nDATABASE NAME - " + databaseName + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
