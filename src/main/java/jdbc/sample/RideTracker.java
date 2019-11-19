package jdbc.sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.newrelic.api.agent.Trace;

public class RideTracker {

	@Trace(dispatcher=true)
	public void batchUpdate(Connection conn, int i) throws SQLException {
		try (PreparedStatement prepSelectStmt = conn.prepareStatement("SELECT * from ride");
				PreparedStatement prepUpdateStmt = conn.prepareStatement("UPDATE ride SET duration=? where id=?")) {
			try (ResultSet resultset = prepSelectStmt.executeQuery()) {
				while (resultset.next()) {
					String name = resultset.getString("name");
					int duration = resultset.getInt("duration");
					int id = resultset.getInt("id");
					System.out.println("Updating " + name + " = " + duration);
					prepUpdateStmt.setInt(1, duration+1);
					prepUpdateStmt.setInt(2, id);
					int count = prepUpdateStmt.executeUpdate();
					System.out.println(count + " rows updated");
				}
			}
		}
	}
}