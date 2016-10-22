package com.example.sam.textadventure.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.sam.textadventure.R;
import com.example.sam.textadventure.calc.ChapterFragment;

/**
 * Created by Sam on 22/10/2016.
 */
public class StoryActivity extends FragmentActivity {
    //The number of chapters
    private static final int NUM_CHAPTERS = 6;
    private static final int GAME_STATE = 1;
    //The pager widget, which handles animation and allows swiping horizontally to access previous
    private ViewPager mPager;

    //The pager adapter, which provides the pages to the view pager widget.
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    //A simple pager adapter that represents 3 ChapterFragment objects, in sequence.
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_CHAPTERS;
        }

        @Override
        public Fragment getItem(int position) {
            return ChapterFragment.newInstance(position);
        }

    }

    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("STATE", GAME_STATE);
        this.setResult(RESULT_OK, intent);
        finish();
    }
}
