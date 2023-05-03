package barPackage.business.interfaces;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import javafx.collections.ObservableList;

import java.util.HashSet;

public interface Manager<T> {
    public void add(T item) throws AddErrorException;
    public void update(T item, T newItem) throws UpdateErrorException;
    public void delete(T item) throws DeleteErrorException;
    public ObservableList<T> getAll() throws ReadErrorException;
    public HashSet<String> getColumnsNames() throws ReadErrorException;
}
