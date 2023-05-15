package barPackage.business;

import barPackage.dataAccess.utils.ContentDataAccess;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Content;
import barPackage.model.Outdate;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class ContentManager {
    private static ContentDataAccess contentDataAccess;

    public ContentManager() {
        contentDataAccess = DataConfiguration.getContentDataAccess();
    }

    public ObservableList<Content> getAllContents() throws ReadErrorException {
        return contentDataAccess.getAllContents();
    }

    public void addContent(Content content) throws AddErrorException {
        contentDataAccess.addContent(content);
    }

    public void updateContent(Content content, Content newContent) throws ReadErrorException {
        contentDataAccess.updateContent(content, newContent);
    }

    public void deleteContent(Content content) throws DeleteErrorException {
        contentDataAccess.deleteContent(content);
    }

    public ObservableList<Content> getAllContentAvailables() throws ReadErrorException {
        return contentDataAccess.getAllContentAvailables();
    }
    public ObservableList<Outdate> getAllOutdatedContent(LocalDate outdate) throws ReadErrorException {
        return contentDataAccess.getAllOutdatedContent(outdate);
    }
    public void consumeContent(Content content, double parseDouble) throws UpdateErrorException {
        contentDataAccess.consumeContent(content, parseDouble);
    }
}
