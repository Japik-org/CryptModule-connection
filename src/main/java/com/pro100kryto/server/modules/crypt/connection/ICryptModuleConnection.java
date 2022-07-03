package com.pro100kryto.server.modules.crypt.connection;

import com.pro100kryto.server.module.IModuleConnection;

import java.rmi.RemoteException;
import java.util.Arrays;

public interface ICryptModuleConnection extends IModuleConnection {

    byte[] crypt(byte[] data, byte[] salt) throws RemoteException;

    default boolean check(byte[] crypt, byte[] data, byte[] salt) throws RemoteException {
        return Arrays.equals(
                crypt,
                crypt(data, salt)
        );
    }
    byte[] decrypt(byte[] crypt, byte[] salt) throws RemoteException;

    byte[] randomSalt(int len) throws RemoteException;

    byte[] combine(byte[] arr1, byte[] arr2) throws RemoteException;
}
