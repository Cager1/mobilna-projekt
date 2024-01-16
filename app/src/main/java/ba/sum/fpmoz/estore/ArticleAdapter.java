package ba.sum.fpmoz.estore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<ArticleItem> articleList;

    public ArticleAdapter(List<ArticleItem> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ArticleItem articleItem = articleList.get(position);
        holder.articleThumbnail.setImageResource(articleItem.getArticleThumbnail());
        holder.cijena.setText(articleItem.getCijena());
        holder.favouriteBtn.setText("Button " + position);
        holder.favouriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cageru ovo tebi ostavljam :D
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView articleThumbnail;
        Button favouriteBtn;
        TextView cijena;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
             articleThumbnail = itemView.findViewById(R.id.articleThumbnail);
            favouriteBtn = itemView.findViewById(R.id.favouriteBtn);
            cijena = itemView.findViewById(R.id.cijena);
        }

    }
}

