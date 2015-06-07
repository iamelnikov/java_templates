package mongo.connector.impl;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

public class StandartPoolConnectionFactory {

	private MongoClient mongoClient;
	private String dbName;
	public StandartPoolConnectionFactory(String host, int port, String dbName, int connectionsPerHost, boolean autoConnectRetry) throws UnknownHostException{
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		builder.autoConnectRetry(autoConnectRetry);
		builder.connectionsPerHost(connectionsPerHost);
		MongoClientOptions mongoClientOptions = builder.build();
		mongoClient = new MongoClient(host, mongoClientOptions);
		
		this.dbName = dbName;
	}
	
	public DB getConnection(){
		return mongoClient.getDB(dbName);
	}
}
