package com.sparta.AlphaTeam.DataManagement.Database;



import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(int id);
    List<T> getAll();
    void add(T t);
}
