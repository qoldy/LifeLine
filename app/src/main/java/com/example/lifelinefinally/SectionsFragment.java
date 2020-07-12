package com.example.lifelinefinally;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SectionsFragment extends Fragment {
    ImageButton diary, news, profile, settings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sections, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        diary = getView().findViewById(R.id.diaryButton);
        news = getView().findViewById(R.id.newsButton);
        profile = getView().findViewById(R.id.profileButton);
        settings = getView().findViewById(R.id.settingsButton);

        switch (((LifeLine)getActivity().getApplication()).userActions.currentSection){
            case "diary": {
                diary.setBackground(getActivity().getDrawable(R.drawable.diary_selected));
                news.setBackground(getActivity().getDrawable(R.drawable.news));
                profile.setBackground(getActivity().getDrawable(R.drawable.profile));
                settings.setBackground(getActivity().getDrawable(R.drawable.settings));
                break;
            }
            case "profile": {
                diary.setBackground(getActivity().getDrawable(R.drawable.diary));
                news.setBackground(getActivity().getDrawable(R.drawable.news));
                profile.setBackground(getActivity().getDrawable(R.drawable.profile_selected));
                settings.setBackground(getActivity().getDrawable(R.drawable.settings));
                break;
            }
            case "news": {
                diary.setBackground(getActivity().getDrawable(R.drawable.diary));
                news.setBackground(getActivity().getDrawable(R.drawable.news_selected));
                profile.setBackground(getActivity().getDrawable(R.drawable.profile));
                settings.setBackground(getActivity().getDrawable(R.drawable.settings));
                break;
            }
            case "settings": {
                diary.setBackground(getActivity().getDrawable(R.drawable.diary));
                news.setBackground(getActivity().getDrawable(R.drawable.news));
                profile.setBackground(getActivity().getDrawable(R.drawable.profile));
                settings.setBackground(getActivity().getDrawable(R.drawable.settings_selected));
                break;
            }
        }

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LifeLine)getActivity().getApplication()).userActions.currentSection="diary";
                ((LifeLine)getActivity().getApplication()).userActions.isReport=false;
                Intent intent = new Intent(getContext(), DiaryActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LifeLine)getActivity().getApplication()).userActions.currentSection="news";
                Intent intent = new Intent(getContext(), NewsActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LifeLine)getActivity().getApplication()).userActions.currentSection="profile";
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LifeLine)getActivity().getApplication()).userActions.currentSection="settings";
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}