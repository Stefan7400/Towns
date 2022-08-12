package de.crackyman.towns.persistance.database;

public interface Callback<T> {

    default void onSuccess(T param){}
    default void onFailure(){}

}
