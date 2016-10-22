package com.example.sam.textadventure.calc;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sam.textadventure.R;

/**
 * Created by Sam on 22/10/2016.
 */
public class ChapterFragment extends Fragment {
    private View rootView;
    private TextView chapterText;
    private Button beginButton;

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chapter, container, false);
        beginButton = (Button) rootView.findViewById(R.id.beginButton);
        beginButton.setVisibility(View.GONE);
        chapterText = (TextView) rootView.findViewById(R.id.chapterView);
        Bundle bundle = getArguments();
        Integer chapterValue = bundle.getInt("Chapter");
        switch (chapterValue) {
            case 1:
                chapterText.setText(getActivity().getString(R.string.chapter1) + "\n \n Swipe Right to continue.");
                break;
            case 2:
                chapterText.setText(getActivity().getString(R.string.chapter2));
                break;
            case 3:
                chapterText.setText(getActivity().getString(R.string.chapter3));
                break;
            case 4:
                chapterText.setText(getActivity().getString(R.string.chapter4));
                break;
            case 5:
                chapterText.setText(getActivity().getString(R.string.chapter5));
                break;
            case 6:
                chapterText.setText(getActivity().getString(R.string.chapter6));
                beginButton.setVisibility(View.VISIBLE);
                break;
        }

        return rootView;
    }


    public static Fragment newInstance(int position) {
        ChapterFragment result = new ChapterFragment();
        Bundle args = new Bundle();
        switch (position) {
            case 0:
                args.putInt("Chapter", 1);
                break;
            case 1:
                args.putInt("Chapter", 2);
                break;
            case 2:
                args.putInt("Chapter", 3);
                break;
            case 3:
                args.putInt("Chapter", 4);
                break;
            case 4:
                args.putInt("Chapter", 5);
                break;
            case 5:
                args.putInt("Chapter", 6);
                break;
        }
        result.setArguments(args);
        return result;
    }


}


