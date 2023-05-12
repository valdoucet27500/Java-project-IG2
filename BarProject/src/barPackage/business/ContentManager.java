package barPackage.business;

import barPackage.dataAccess.utils.ContentDataAccess;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Content;

public class ContentManager {
    private ContentDataAccess contentDataAccess;

    public ContentManager() {
        contentDataAccess = DataConfiguration.getContentDataAccess();
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
}
