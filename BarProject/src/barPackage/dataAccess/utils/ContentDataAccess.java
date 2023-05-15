package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Content;
import barPackage.model.Outdate;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public interface ContentDataAccess {
    public ObservableList<Content> getAllContents() throws ReadErrorException;
    public void addContent(Content content) throws AddErrorException;
    public void deleteContent(Content content) throws DeleteErrorException;
    public void updateContent(Content content, Content newContent) throws ReadErrorException;

    public ObservableList<Content> getAllContentAvailables() throws ReadErrorException;

    public ObservableList<Outdate> getAllOutdatedContent(LocalDate outdate) throws ReadErrorException;

    public void consumeContent(Content content, Double parseDouble) throws UpdateErrorException;

}
