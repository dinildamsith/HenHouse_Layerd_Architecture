package lk.ijse.henHouse.dao;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(T entity) throws SQLException, ClassNotFoundException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException;
    public boolean exist(String id) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public ObservableList search(String id) throws SQLException, ClassNotFoundException;
    public ObservableList getIds()throws SQLException, ClassNotFoundException;
    // public boolean delete(String id) throws SQLException, ClassNotFoundException;


}
