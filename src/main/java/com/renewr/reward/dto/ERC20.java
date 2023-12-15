package com.renewr.reward.dto;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.tx.TransactionManager;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class ERC20 extends Contract {

    protected ERC20(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        super("", contractAddress, web3j, credentials, gasProvider);
    }

    public RemoteCall<TransactionReceipt> transfer(String to, BigInteger value) {
        final Function function = new Function(
                "transfer",
                Arrays.asList(new Address(to), new Uint256(value)),
                Collections.emptyList());

        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String from, String to, BigInteger value) {
        final Function function = new Function(
                "transfer",
                Arrays.asList(new Address(from), new Address(to), new Uint256(value)),
                Collections.emptyList());

        return executeRemoteCallTransaction(function);
    }

    public static ERC20 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        return new ERC20(contractAddress, web3j, credentials, gasProvider);
    }

}