package com.renewr.reward.service;

import com.renewr.reward.dto.ERC20;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

@Service
public class ERC20Service {

    private final Web3j web3j;

    private final Credentials credentials;

    private final String contractAddress = "0x564a86944638f8598E95b742b967970893475cB0";

    private final ERC20 token;

    public static String ADMIN_ADDRESS = "0xBF87Db6bd55E7584f7a9b30aD93791D1cd4c3e70";

    public ERC20Service(@Value("${infura.API_URL}") String infuraUrl,
                        @Value("${metamask.PRIVATE_KEY}") String privateKey) {
        this.web3j = Web3j.build(new HttpService(infuraUrl));
        this.credentials = Credentials.create(privateKey);
        this.token = ERC20.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }

    public TransactionReceipt transfer(String toAddress, BigInteger amount) throws Exception {
        return token.transfer(toAddress, amount).send();
    }

    public TransactionReceipt transferFrom(String fromAddress, String toAddress, BigInteger amount) throws Exception {
        return token.transferFrom(fromAddress, toAddress, amount).send();
    }

}