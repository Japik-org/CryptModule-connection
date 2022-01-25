package com.pro100kryto.server.modules.crypt.connection;

import com.pro100kryto.server.module.IModuleConnection;

import java.rmi.RemoteException;
import java.util.Arrays;

public interface ICryptModuleConnection extends IModuleConnection {

    int getCryptLen() throws RemoteException;

    byte[] crypt(byte[] cryptOut, byte[] data, byte[] salt) throws RemoteException;
    default byte[] crypt(byte[] data, byte[] salt) throws RemoteException {
        return crypt(new byte[getCryptLen()], data, salt);
    }
    default boolean check(byte[] cryptOut, byte[] crypt, byte[] data, byte[] salt) throws RemoteException {
        return Arrays.equals(
                crypt,
                crypt(cryptOut, data, salt)
        );
    }
    default boolean check(byte[] crypt, byte[] data, byte[] salt) throws RemoteException {
        return Arrays.equals(
                crypt,
                crypt(data, salt)
        );
    }
    byte[] decrypt(byte[] dataOut, byte[] crypt, byte[] salt) throws RemoteException;

    int getSaltLen() throws RemoteException;

    byte[] randomSalt(byte[] saltOut) throws RemoteException;
    default byte[] randomSalt() throws RemoteException {
        return randomSalt(new byte[getSaltLen()]);
    }

    byte[] combineSalt(byte[] saltOut, byte[] salt1, byte[] salt2) throws RemoteException;
    default byte[] combineSalt(byte[] salt1, byte[] salt2) throws RemoteException {
        return combineSalt(new byte[getSaltLen()], salt1, salt2);
    }
}
