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
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.rockerhieu.emojicon.emoji.Emojicon;

/**
 * Created by rockerhieu on 8/10/16.
 */
public class EmojiconGridView extends GridView implements AdapterView.OnItemClickListener {
    @Emojicon.Type
    private int type;
    private Emojicon[] data;
    private boolean useSystemDefaults;
    private EmojiconGridFragment.OnEmojiconClickedListener onEmojiconClickedListener;
    private EmojiAdapter emojiAdapter;
    private List<Emojicon> emojiList;

    public EmojiconGridView(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.emojiconGridViewStyle);
        setOnItemClickListener(this);
        setSaveEnabled(true);
        emojiList = new ArrayList<>();
        emojiAdapter = new EmojiAdapter(context, emojiList);
        setAdapter(emojiAdapter);
    }

    public EmojiconGridView(Context context) {
        this(context, null);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.data = data;
        ss.type = type;
        ss.useSystemDefaults = useSystemDefaults;
        ss.scrollX = getScrollX();
        ss.scrollY = getScrollY();
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        type = ss.type;
        data = ss.data;
        useSystemDefaults = ss.useSystemDefaults;
        setScrollX(ss.scrollX);
        setScrollY(ss.scrollY);
        setEmojiData(type, data, useSystemDefaults);
    }

    public void setEmojiData(@Emojicon.Type int type, Emojicon[] data, boolean useSystemDefaults) {
        this.type = type;
        if (this.type != Emojicon.TYPE_UNDEFINED) {
            this.data = Emojicon.getEmojicons(type);
        } else {
            this.data = data;
        }
        this.useSystemDefaults = useSystemDefaults;
        if (this.data == null) {
            emojiList.clear();
        } else {
            emojiList.clear();
            Collections.addAll(emojiList, this.data);
        }
        emojiAdapter.notifyDataSetChanged();
    }

    public void setOnEmojiconClickedListener(EmojiconGridFragment.OnEmojiconClickedListener onEmojiconClickedListener) {
        this.onEmojiconClickedListener = onEmojiconClickedListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (onEmojiconClickedListener != null) {
            onEmojiconClickedListener.onEmojiconClicked((Emojicon) parent.getItemAtPosition(position));
        }
    }

    public static class SavedState extends BaseSavedState {
        @Emojicon.Type
        int type;
        Emojicon[] data;
        boolean useSystemDefaults;
        int scrollX;
        int scrollY;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            //noinspection WrongConstant
            this.type = in.readInt();
            this.data = (Emojicon[]) in.readParcelableArray(Emojicon.class.getClassLoader());
            this.useSystemDefaults = in.readInt() != 0;
            this.scrollX = in.readInt();
            this.scrollY = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.type);
            out.writeParcelableArray(data, flags);
            out.writeInt(this.useSystemDefaults ? 1 : 0);
            out.writeInt(this.scrollX);
            out.writeInt(this.scrollY);
        }

        //required field that makes Parcelables from a Parcel
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}
