package database.service;

import database.ConnectionProvider;
import database.Status;
import database.Type;
import database.model.Transaction;
import database.model.Wallet;
import database.repository.TransactionRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionService {

    private WalletService walletService;
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository,WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.walletService=walletService;
    }

    public boolean withDraw (int walletID,int amount) throws SQLException, ClassNotFoundException {

        Wallet wallet=walletService.findByID(walletID);
        if (wallet==null || wallet.getAmount()<amount) {
            return false;
        }

        Transaction transaction = new Transaction(-1, wallet, amount, Status.ACCEPTED, Type.WITHDRAW);
        transactionRepository.add(transaction);
        wallet.setAmount(wallet.getAmount()-amount);
        walletService.update(wallet);
        return true;
    }


    public boolean deposit (int walletID,int amount) throws SQLException, ClassNotFoundException {

        Wallet wallet=walletService.findByID(walletID);
        if (wallet==null) {
            return false;
        }

        Transaction transaction = new Transaction(-1, wallet, amount, Status.ACCEPTED, Type.DEPOSIT);
        transactionRepository.add(transaction);
        wallet.setAmount(wallet.getAmount()+amount);
        walletService.update(wallet);
        return true;
    }




}
