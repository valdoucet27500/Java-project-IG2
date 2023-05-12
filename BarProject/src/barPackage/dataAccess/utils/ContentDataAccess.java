package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Content;
import javafx.collections.ObservableList;

public interface ContentDataAccess {
    public ObservableList<Content> getAllContents() throws ReadErrorException;
    public void addContent(Content content) throws AddErrorException;
    public void deleteContent(Content content) throws DeleteErrorException;
    public void updateContent(Content content, Content newContent) throws ReadErrorException;
}
