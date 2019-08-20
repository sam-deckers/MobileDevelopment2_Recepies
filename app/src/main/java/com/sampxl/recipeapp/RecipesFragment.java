package com.sampxl.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RecipesFragment extends Fragment implements RecipeAdapter.OnItemClickListener {



    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private ArrayList<RecipeItem> mRecipeList;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recipes, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recycler
        mRecyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mRecipeList = new ArrayList<>();

        // define an adapter


        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON();
    }



    private void parseJSON() {
        String url = "https://api.myjson.com/bins/15kgvf";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("recipes");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                Log.d("amount", String.valueOf(i));
                                String name = hit.getString("name");
                                String imageUrl = hit.getString("imageUrl");
                                String steps = hit.getString("steps");

                                Log.d("new recipe", name + " "  + steps + " " + imageUrl);

                                mRecipeList.add(new RecipeItem(imageUrl, name, steps));
                            }

                            mRecipeAdapter = new RecipeAdapter(getActivity(), mRecipeList);
                            mRecyclerView.setAdapter(mRecipeAdapter);
                            mRecipeAdapter.setOnItemClickListener(RecipesFragment.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
    if(getActivity().findViewById(R.id.image_view_detail) == null){
        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        RecipeItem clickedItem = mRecipeList.get(position);

        detailIntent.putExtra("imageUrl", clickedItem.getImageUrl());
        detailIntent.putExtra("name", clickedItem.getName());
        detailIntent.putExtra("steps", clickedItem.getSteps());

        startActivity(detailIntent);
    }
    else{
        RecipeItem clickedItem = mRecipeList.get(position);
        ImageView imageUrl =  getActivity().findViewById(R.id.image_view_detail);
        TextView name =  getActivity().findViewById(R.id.text_view_creator_detail);
        TextView steps =  getActivity().findViewById(R.id.text_view_like_detail);
        Picasso.with(getActivity()).load(clickedItem.getImageUrl()).fit().centerInside().into(imageUrl);
        name.setText(clickedItem.getName());
        steps.setText(clickedItem.getSteps());

    }

    }
}
