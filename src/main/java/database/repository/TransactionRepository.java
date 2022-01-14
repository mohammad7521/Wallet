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

}
