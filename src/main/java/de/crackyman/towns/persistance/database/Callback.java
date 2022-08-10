package de.crackyman.towns.persistance.database;

public interface Callback<T> {

    void onSuccess(T param);
    void onFailure();

}
