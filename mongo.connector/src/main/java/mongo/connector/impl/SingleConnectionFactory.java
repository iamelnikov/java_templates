package mongo.connector.impl;

import java.net.UnknownHostException;

import mongo.connector.ConnectionFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class SingleConnectionFactory implements ConnectionFactory{

	private String host;
	private int port;
	private String dbName;
	
	public SingleConnectionFactory(String host, int port, String dbName){
		this.host = host;
		this.port = port;
		this.dbName = dbName;
	}
	
	public DB getConnection() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(host, port);
		return mongoClient.getDB(dbName);
	}

}
