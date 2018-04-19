/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restjee.se;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Arrays;
import org.bson.Document;

/**
 *
 * @author tuvshuu
 */
public class CarMongoSEMain {

    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase db = mongoClient.getDatabase("testdb");
            Document doc = new Document("name", "MongoDB")
                    .append("type", "database")
                    .append("count", 1)
                    .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                    .append("info", new Document("x", 203).append("y", 102));
            MongoCollection<Document> collection = db.getCollection("carCollection");
            System.out.println(collection.count());

            System.out.println("Using Cursor");
            FindIterable<Document> docList = collection.find();
            MongoCursor<Document> cursor = docList.iterator();
            while (cursor.hasNext()) {
                Document doc1 = cursor.next();
                System.out.println(doc.toString());
            }

            System.out.println("\nSelect or create COLLECTION");
            String carColName = "CarCol";
            MongoCollection<Document> carCol = db.getCollection(carColName);
            System.out.println("\nCREATE NEW RECORD");
            Document carDoc = new Document();
            carDoc.append("Make", "Toyota")
                    .append("Model", "Prius")
                    .append("ManuFacturingYear", 2017)
                    .append("Miles", 0.0)
                    .append("Color", "BLUE")
                    .append("_id", 1);
            carCol.insertOne(carDoc);

            System.out.println("\nSELECT RECORDS");
            carCol.find().forEach(printBlock);
            System.out.println("\nSELECT BY FIELD");
            carCol.find(Filters.eq("Make", "Toyota")).forEach(printBlock);

            System.out.println("\nUPDATE");
            carCol.updateOne(Filters.eq("Make", "Toyota"), Updates.combine(Updates.set("Miles", 0.1)));
            carCol.find(Filters.eq("Make", "Toyota")).forEach(printBlock);

            System.out.println("\nDELETE");
            carCol.deleteOne(Filters.gt("Miles", 0.0));
            carCol.find().forEach(printBlock);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Block<Document> printBlock = (final Document document) -> {
        System.out.println(document.toJson());
    };
}
