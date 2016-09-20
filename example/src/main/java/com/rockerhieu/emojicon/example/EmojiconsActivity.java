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

package com.rockerhieu.emojicon.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

import io.github.rockerhieu.emojicon.EmojiconPage;
import io.github.rockerhieu.emojicon.EmojiconsView;
import io.github.rockerhieu.emojicon.emoji.Emojicon;

public class EmojiconsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emojicons);
        EmojiconsView emojiconsView = (EmojiconsView) findViewById(R.id.emojicons_view);
        emojiconsView.setPages(Arrays.asList(
                new EmojiconPage(Emojicon.TYPE_PEOPLE, null, false, R.drawable.ic_emoji_people_light),
                new EmojiconPage(Emojicon.TYPE_NATURE, null, false, R.drawable.ic_emoji_nature_light),
                new EmojiconPage(Emojicon.TYPE_OBJECTS, null, false, R.drawable.ic_emoji_objects_light),
                new EmojiconPage(Emojicon.TYPE_PLACES, null, false, R.drawable.ic_emoji_places_light),
                new EmojiconPage(Emojicon.TYPE_SYMBOLS, null, false, R.drawable.ic_emoji_symbols_light)
        ));
    }
}
