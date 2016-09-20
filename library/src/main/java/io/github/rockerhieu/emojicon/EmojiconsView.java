/*
 * Copyright (c) 2016 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.rockerhieu.emojicon;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rockerhieu on 8/31/16.
 */
public class EmojiconsView extends FrameLayout implements ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    private List<EmojiconPage> pages;
    private ViewGroup tabsContainer;
    private View[] tabs;
    private View lastTab;

    public EmojiconsView(Context context) {
        this(context, null);
    }

    public EmojiconsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.emojicons_view, this);
        viewPager = (ViewPager) findViewById(R.id.emojis_pager);
        tabsContainer = (ViewGroup) findViewById(R.id.emojis_tab);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewPager.removeOnPageChangeListener(this);
    }

    public void setPages(@NonNull List<EmojiconPage> pages) {
        this.pages = pages;
        if (tabs == null || tabs.length != pages.size()) {
            tabs = new View[pages.size()];
        } else {
            Arrays.fill(tabs, null);
        }

        for (int i = 0; i < tabsContainer.getChildCount() - 2; i++) {
            tabsContainer.removeViewAt(0);
        }

        int index = 0;
        for (EmojiconPage page : pages) {
            addTabIcon(page, index++);
            addTabDivider();
        }
        onPageSelected(0);
        viewPager.setAdapter(new EmojiconGridViewPagerAdapter(getContext(), pages));
    }

    private void addTabDivider() {
        View divider = new View(getContext());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            viewPager.setBackgroundColor(getContext().getResources().getColor(R.color.horizontal_vertical));
        } else {
            viewPager.setBackgroundColor(getContext().getColor(R.color.horizontal_vertical));
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
        tabsContainer.addView(divider, tabsContainer.getChildCount() - 2, params);
    }

    private void addTabIcon(EmojiconPage page, int index) {
        ImageButton icon = new ImageButton(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        icon.setBackground(null);
        icon.setScaleType(ImageView.ScaleType.CENTER);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon.setImageDrawable(getContext().getResources().getDrawable(page.getIcon()));
        } else {
            icon.setImageDrawable(getContext().getDrawable(page.getIcon()));
        }
        tabsContainer.addView(icon, tabsContainer.getChildCount() - 2, params);
        tabs[index] = icon;
        final int indexToMove = index;
        icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(indexToMove, true);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (tabs == null || position >= tabs.length) {
            return;
        }
        if (lastTab != null) {
            lastTab.setSelected(false);
        }
        lastTab = tabs[position];
        lastTab.setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private static class EmojiconGridViewPagerAdapter extends PagerAdapter {
        private Context context;
        private final List<EmojiconPage> pages;
        private EmojiconGridView.SavedState[] savedStates;

        public EmojiconGridViewPagerAdapter(Context context, @NonNull List<EmojiconPage> pages) {
            this.context = context;
            this.pages = pages;
            this.savedStates = new EmojiconGridView.SavedState[pages.size()];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            EmojiconPage emojiconPage = pages.get(position);
            EmojiconGridView emojiGridView = new EmojiconGridView(context);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                emojiGridView.setId(View.generateViewId());
            } else {
                emojiGridView.setId(Utils.generateViewId());
            }
            container.addView(emojiGridView);
            emojiGridView.setEmojiData(emojiconPage.getType(), emojiconPage.getData(), emojiconPage.isUseSystemDefaults());
            if (savedStates[position] != null) {
                SparseArray sparseArray = new SparseArray(1);
                sparseArray.put(emojiGridView.getId(), savedStates[position]);
                emojiGridView.restoreHierarchyState(sparseArray);
            }
            return emojiGridView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            EmojiconGridView view = (EmojiconGridView) object;
            savedStates[position] = (EmojiconGridView.SavedState) view.onSaveInstanceState();
            container.removeView((View) object);
        }

        @Override
        public Parcelable saveState() {
            Bundle state = new Bundle();
            state.putParcelableArray("states", savedStates);
            return state;
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            if (state != null) {
                Bundle bundle = (Bundle) state;
                Parcelable[] states = bundle.getParcelableArray("states");
                savedStates = new EmojiconGridView.SavedState[states.length];
                for (int i = 0; i < states.length; i++) {
                    savedStates[i] = (EmojiconGridView.SavedState) states[i];
                }
            }
            super.restoreState(state, loader);
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
