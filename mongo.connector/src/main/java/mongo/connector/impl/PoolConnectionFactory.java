package mongo.connector.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import mongo.connector.ConnectionFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class PoolConnectionFactory implements ConnectionFactory {

	private Map<DB, Boolean> pool;
	private Semaphore poolSemaphore;

	private String[] hostArray;
	private int[] portArray;
	private String dbName;

	private MongoClient mongoClient;
	private ReadWriteLock mongoClientLock = new ReentrantReadWriteLock(true);

	public PoolConnectionFactory(String host, int port, String dbName,
			int poolSize) {
		this.hostArray = new String[1];
		this.hostArray[0] = host;
		this.portArray = new int[1];
		this.portArray[0] = port;

		this.dbName = dbName;
		this.pool = new ConcurrentHashMap<DB, Boolean>();
		this.poolSemaphore = new Semaphore(poolSize, true);
	}

	public PoolConnectionFactory(Object[][] servers, String dbName) {

	}

	private void initMongoClient() throws UnknownHostException {
		Lock lock = mongoClientLock.writeLock();
		lock.lock();
		if (this.hostArray.length == 1)
			this.mongoClient = new MongoClient(hostArray[0], portArray[0]);
		else {
			int size = this.hostArray.length;
			final List<ServerAddress> serverArrayList = new ArrayList<ServerAddress>(size);
			for (int i = 0; i < size; i++) {
				ServerAddress serverAddress = new ServerAddress(
						this.hostArray[i], this.portArray[i]);
				serverArrayList.add(serverAddress);
			}
			this.mongoClient = new MongoClient(serverArrayList);
		}
		lock.unlock();
	}

	private MongoClient getMongoClient() throws UnknownHostException {
		if (this.mongoClient == null)
			initMongoClient();
		return mongoClient;
	}

	private synchronized DB initNewSingleConnection()
			throws UnknownHostException {
		return getMongoClient().getDB(dbName);
	}

	private DB getAlreadyOpenedAvaliableConnection() {
		Iterator<Map.Entry<DB, Boolean>> i = this.pool.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry<DB, Boolean> entry = i.next();
			if (entry.getValue())
				return entry.getKey();
		}
		return null;
	}

	private DB getAvaliableConnection() throws UnknownHostException {
		DB db = getAlreadyOpenedAvaliableConnection();
		if (db == null)
			db = initNewSingleConnection();
		this.pool.put(db, true);
		return db;
	}

	public DB getConnection() throws UnknownHostException {
		try {
			poolSemaphore.acquire();
			return getAvaliableConnection();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
			return null;
		}
	}

	public void releaseDB(DB db) {
		if (db == null)
			return;

		this.pool.put(db, false);
		this.poolSemaphore.release();
	}

}
