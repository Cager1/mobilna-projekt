package ba.sum.fpmoz.estore;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {

    private List<ArticleItem> articleList;
    private RecyclerView homeRecyclerView;
    private ArticleAdapter articleAdapter;


    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        articleList = generateArticleItem();

        homeRecyclerView = rootView.findViewById(R.id.homeRecyclerView);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        articleAdapter = new ArticleAdapter(articleList);
        homeRecyclerView.setAdapter(articleAdapter);

        return rootView;
    }
    private List<ArticleItem> generateArticleItem(){
        List<ArticleItem> articleItems = new ArrayList<>();
        articleItems.add(new ArticleItem(R.drawable.thumbnail, "Cijena: $", new Button(getContext())));

        return articleItems;
    }
}