package mongo.connector;

import java.net.UnknownHostException;

import com.mongodb.DB;

public interface ConnectionFactory {

	public DB getConnection() throws UnknownHostException;
}
