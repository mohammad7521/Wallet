package database.repository;

import database.ConnectionProvider;
import database.model.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRepository {

    public TransactionRepository () throws SQLException, ClassNotFoundException {

        String createStatus="CREATE TYPE transactionStatus AS ENUM('CANCELLED','PENDING','ACCEPTED')";
        String createType="CREATE TYPE TransactionType AS ENUM('DEPOSIT','WITHDRAW')";
        String createTransactionTable="CREATE TABLE IF NOT EXISTS TRANSACTION(" +
                "id serial primary key," +
                "amount decimal," +
                "walletID int," +
                "transactionStatus type," +
                "TransactionType type," +
                "constraint wID foreign key (walletID) references WALLET(id)" +
                ")";


        PreparedStatement preparedStatement=ConnectionProvider.getConnection().prepareStatement(createStatus);
        preparedStatement.execute();
        preparedStatement=ConnectionProvider.getConnection().prepareStatement(createType);
        preparedStatement.execute();
        preparedStatement= ConnectionProvider.getConnection().prepareStatement(createTransactionTable);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void add (Transaction transaction) throws SQLException, ClassNotFoundException {
        String createTransaction="INSERT INTO TRANSACTION VALUES(default,?,?,?,?)";
        PreparedStatement preparedStatement=ConnectionProvider.getConnection().prepareStatement(createTransaction);
        preparedStatement.setDouble(1,transaction.getAmount());
        preparedStatement.setInt(2,transaction.getWallet().getId());
        preparedStatement.setString(3,transaction.getStatus().name());
        preparedStatement.setString(4,transaction.getType().name());

    }


}
