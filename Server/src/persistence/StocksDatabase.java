package persistence;

import model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * StocksDatabase is used for saving/loading data to/from database about stocks
 */
public class StocksDatabase implements StocksPersistence {
    private static StocksDatabase instance;

    private StocksDatabase() {
    }

    public synchronized static StocksDatabase getInstance() {
        if (instance == null) {
            instance = new StocksDatabase();
        }
        return instance;
    }

    /**
     * Loads stocks for specific user and for specific company
     *
     * @param user user that owns the stocks
     * @param company company of the stocks
     * @return stock from database if not found will return null
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    @Override
    public Stock load(User user, Company company) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("select * from stock where user_name = ?  and symbol = ?");
            statement.setString(1, user.getUserName().toString());
            statement.setString(2, company.getSymbol());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("user_name");
                String symbol = resultSet.getString("symbol");
                int amount = resultSet.getInt("amount");
                return new Stock(symbol, username, amount);
            }
            return null;
        }
    }

    /**
     * loads all of stocks from database
     *
     * @return stocks list from database
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    @Override
    public Stocks loadAll() throws SQLException {
        Stocks stocks = new Stocks();
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("select * from stock");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("user_name");
                String symbol = resultSet.getString("symbol");
                int amount = resultSet.getInt("amount");
                Stock s = new Stock(symbol, username, amount);
                s.setPrice(resultSet.getDouble("price"));
                stocks.addStock(s);
            }
            return stocks;
        }
    }

    /**
     * updates all stocks in database with stocks list
     *
     * @param stocks list will be updated to database
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    @Override
    public void update(Stocks stocks) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            for (Stock s : stocks.getAllStocks()) {
                try {
                    PreparedStatement statement = connection.prepareStatement("UPDATE stock SET amount = ?,price = ? where user_name = ? AND symbol = ?");
                    statement.setString(3, s.getUsername());
                    statement.setString(4, s.getSymbol());
                    statement.setInt(1, s.getAmount());
                    statement.setDouble(2, s.getPrice());
                    statement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    /**
     * Saves specific stock to database
     *
     * @param stock stock to be saved
     * @param user owner of the stock
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    @Override
    public void save(Stock stock, User user) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("insert into stock(user_name,symbol,amount,price)values (?,?,?,?)");
            statement.setString(1, user.getUserName().getName());
            statement.setString(2, stock.getSymbol());
            statement.setInt(3, stock.getAmount());
            statement.setDouble(4, stock.getPrice());
            statement.executeUpdate();
        }
    }

    /**
     * Saves all stocks to database
     *
     * @param stocks stocks to be saved
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    @Override
    public void saveAll(Stocks stocks) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            for (Stock s : stocks.getAllStocks()) {
                try {
                    PreparedStatement statement = connection.prepareStatement("insert into stock(user_name,symbol,amount,price)values (?,?,?,?)");
                    statement.setString(1, s.getUsername());
                    statement.setString(2, s.getSymbol());
                    statement.setInt(3, s.getAmount());
                    statement.setDouble(4, s.getPrice());
                    statement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    /**
     * removes stock from database
     * @param stock stock to be removed
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    @Override
    public void remove(Stock stock) throws SQLException {

    }
}
