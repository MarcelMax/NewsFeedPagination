package marcelmax.newsfeedpagination.model;

public interface ViewType {
    /**
     * all models that are being displayed in a recyclerview have to implement this.
     * they each get a specific id tied to them so the adapter can set the view
     * accordingly
     */
    int LOADING_TYPE = 0;
    int ARTICLE_TYPE = 1;

    int getType();
}
