[![Android Gems](http://www.android-gems.com/badge/rockerhieu/emojicon.svg?branch=master)](http://www.android-gems.com/lib/rockerhieu/emojicon)

![master on Travis CI](https://travis-ci.org/rockerhieu/emojicon.png?branch=master) ![emojicon on Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.rockerhieu/emojicon/badge.svg)

## Emojicon

Do you like emojis in Whatsapp, iMessage? [Emojicon](http://rockerhieu.github.io/emojicon/) is a library to implement such a thing for Android.

Fore more information please see [the website](http://rockerhieu.github.io/emojicon/)

## Example

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:emojicon="http://schemas.android.com/apk/res-auto"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical">

    <io.github.rockerhieu.emojicon.EmojiconTextView
            android:id="@+id/txtEmojicon"
            android:text="I \ue32d emojicon"
            emojicon:emojiconAlignment="baseline"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

    <io.github.rockerhieu.emojicon.EmojiconEditText
            android:id="@+id/editEmojicon"
            android:text="I \ue32d emojicon"
            emojicon:emojiconSize="28sp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
    <fragment
            android:id="@+id/emojicons"
            android:layout_width="match_parent"
            android:layout_height="220dp"
            class="io.github.rockerhieu.emojicon.EmojiconsFragment"/>
</LinearLayout>
```

![image](https://github.com/rockerhieu/emojicon/raw/master/images/sample.jpg)

_Note: You can change the size and alignment of emojis in XML layout through attribute `emojiconSize` and `emojiconAlignment`, respectively._

## Usage

* `EmojiconTextView`: a `TextView` which can render emojis.
* `EmojiconEditText`: a `EditText` which can render emojis.
* `EmojiconMultiAutoCompleteTextView`: a `MultiAutoCompleteTextView` which can render emojis.
* `EmojiconGridFragment`: a fragment contains emojis in a `GridView` for the user to choose.
* `EmojiconsFragment`: a fragment contains many set of emojis for the user to choose.

## Building in IntelliJ

Via Gradle:

```
compile 'io.github.rockerhieu:emojicon:<latest-version>'
```

## Building in Eclipse

![Go home you're drunk](http://img2.wikia.nocookie.net/__cb20130819142928/cardfight/images/thumb/5/55/Go-home-youre-drunk.jpg/500px-Go-home-youre-drunk.jpg)

## Acknowledgements

Emojicon is using emojis graphics from [emoji-cheat-sheet.com](https://github.com/arvida/emoji-cheat-sheet.com/tree/master/public/graphics/emojis).

## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/rockerhieu/emojicon/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.

## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

```
Copyright 2014 Hieu Rocker

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
