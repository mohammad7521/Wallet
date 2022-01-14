package database.repository;

import database.ConnectionProvider;
import database.model.Wallet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WalletRepository {

    public WalletRepository () throws SQLException, ClassNotFoundException {
        String createWalletTable="CREATE TABLE IF NOT EXISTS WALLET(" +
                "id serial primary key," +
                "amount decimal," +
                ")";
        PreparedStatement preparedStatement= ConnectionProvider.getConnection().prepareStatement(createWalletTable);
        preparedStatement.execute();
        preparedStatement.close();
    }



    public int createWallet (int amount) throws SQLException, ClassNotFoundException {
        String createWallet="INSERT INTO WALLET(AMOUNT) VALUES (?) RETURN id";
        PreparedStatement preparedStatement=ConnectionProvider.getConnection().prepareStatement(createWallet);
        preparedStatement.setDouble(1,amount);
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        int returnID=resultSet.getInt(1);
        preparedStatement.close();
        return returnID;
    }



    public boolean removeWallet (int id) throws SQLException, ClassNotFoundException {
        String addToWallet="DELETE FROM WALLET WHERE id=(?)";
        PreparedStatement preparedStatement=ConnectionProvider.getConnection().prepareStatement(addToWallet);
        preparedStatement.setInt(1,id);
        int removed=preparedStatement.executeUpdate();
        preparedStatement.close();

        return removed>0;
    }



    public Wallet find (int id) throws SQLException, ClassNotFoundException {
        Wallet wallet=null;
        String findWallet="SELECT * FROM WALLET WHERE id=(?)";
        PreparedStatement preparedStatement=ConnectionProvider.getConnection().prepareStatement(findWallet);
        preparedStatement.setDouble(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();

        if (resultSet.next()) {
            double amount = resultSet.getDouble(2);
            wallet=new Wallet(id,amount);
        }
        preparedStatement.close();
        return wallet;
    }




    public List<Wallet> findAll() throws SQLException, ClassNotFoundException {
        String showWallets="SELECT * FROM WALLET";
        PreparedStatement preparedStatement=ConnectionProvider.getConnection().prepareStatement(showWallets);
        ResultSet resultSet=preparedStatement.executeQuery();

        List<Wallet> listWallets=new ArrayList<>();

        while(resultSet.next()) {
            Wallet wallet=new Wallet(resultSet.getInt(1),resultSet.getDouble(2));
            listWallets.add(wallet);
        }
        return listWallets;
    }



    public boolean update (Wallet wallet) throws SQLException, ClassNotFoundException {
        String updateWallet="UPDATE WALLET SET amount=(?) WHERE id=(?)";
        PreparedStatement preparedStatement=ConnectionProvider.getConnection().prepareStatement(updateWallet);
        preparedStatement.setDouble(1,wallet.getAmount());
        preparedStatement.setInt(2,wallet.getId());
        int update=preparedStatement.executeUpdate();

        return update>0;
    }

}
