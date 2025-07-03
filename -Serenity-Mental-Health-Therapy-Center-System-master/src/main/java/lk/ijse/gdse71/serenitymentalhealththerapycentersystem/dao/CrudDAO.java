package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    String getNextId();
    boolean save(T entity);

    List<T> getAll();

    boolean update(T entity);

    boolean delete(String id);

    List<T> search(String searchText);
}
