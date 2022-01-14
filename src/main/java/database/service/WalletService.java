package database.service;

import database.model.Wallet;
import database.repository.WalletRepository;

import java.sql.SQLException;

public class WalletService {

    private WalletRepository walletRepository;
    private final int initialDeposit=10000;


    public WalletService (WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    public void createWallet () throws SQLException, ClassNotFoundException {
        walletRepository.createWallet(initialDeposit);
    }


    public void showBalance (int walletID) throws SQLException, ClassNotFoundException {
        System.out.println(walletRepository.find(walletID));
    }


    public Wallet findByID (int id) throws SQLException, ClassNotFoundException {
        Wallet wallet=walletRepository.find(id);
        return wallet;
    }


    public void update(Wallet wallet) throws SQLException, ClassNotFoundException {
        walletRepository.update(wallet);
    }
}
