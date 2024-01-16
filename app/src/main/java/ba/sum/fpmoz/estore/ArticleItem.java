package ba.sum.fpmoz.estore;

import android.widget.Button;

public class ArticleItem {
    private int articleThumbnail;
    private String cijena;
    private Button favouriteBtn;

    public int getArticleThumbnail() {
        return articleThumbnail;
    }

    public String getCijena() {
        return cijena;
    }

    public Button getFavouriteBtn() {
        return favouriteBtn;
    }

    public ArticleItem(int articleThumbnail, String cijena, Button favouriteBtn) {
        this.articleThumbnail = articleThumbnail;
        this.cijena = cijena;
        this.favouriteBtn = favouriteBtn;
    }
}
