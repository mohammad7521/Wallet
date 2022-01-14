package database.repository;

import database.ConnectionProvider;
import database.model.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionService {

    public boolean withDraw(int amount,int walletID) throws SQLException, ClassNotFoundException {
        String withDraw="UPDATE WALLET SET amount=(?) WHERE walletID=(?)";
        PreparedStatement preparedStatement= ConnectionProvider.getConnection().prepareStatement(withDraw);
        preparedStatement.setDouble(1,transaction.getAmount());
        preparedStatement.setInt(2,transaction.getWallet().getId());
        preparedStatement.setObject(3,transaction.getStatus());
        preparedStatement.setObject(4,transaction.getType());
        int create=preparedStatement.executeUpdate();

        return create>0;
    }


}
