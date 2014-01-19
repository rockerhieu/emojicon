## Usage

* `EmojiconTextView`: a `TextView` which can render emojis.
* `EmojiconEditText`: a `EditText` which can render emojis.
* `EmojiconGridFragment`: a fragment contains emojis in a `GridView` for the user to choose.
* `EmojiconsFragment`: a fragment contains many set of emojis for the user to choose.

### Example

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:emojicon="http://schemas.android.com/apk/res-auto"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical">

    <com.rockerhieu.emojicon.EmojiconTextView
            android:id="@+id/txtEmojicon"
            android:text="I \ue32d emojicon"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

    <com.rockerhieu.emojicon.EmojiconEditText
            android:id="@+id/editEmojicon"
            android:text="I \ue32d emojicon"
            emojicon:emojiconSize="28sp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
    <fragment
            android:id="@+id/emojicons"
            android:layout_width="match_parent"
            android:layout_height="220dp"
            class="com.rockerhieu.emojicon.EmojiconsFragment"/>
</LinearLayout>
```

![image](https://github.com/rockerhieu/emojicon/raw/master/images/sample.jpg)

_Note: You can change the size of emojis in XML layout through attribute `emojiconSize`._

## Download

Via Gradle:

```
dependencies {
    repositories {
    	mavenCentral()
        maven {
            url 'https://github.com/rockerhieu/mvn-repo/raw/master/'
        }
    }

    compile 'com.rockerhieu.emojicon:library:<VERSION>'
}
```

Find out `<Version>` [here](https://github.com/rockerhieu/mvn-repo/tree/master/com/rockerhieu/emojicon/library)

## Building in Eclipse

This project depends on the Android V4 Support Library for using Fragment, so
you have to import it into your workspace. Copy the jar file from
`/Android_SDK/extras/android/compitibility/v4/` to the `libs` folder of the project.

Next, you'll need to install the [Eclipse Integration Gradle Plugin](https://github.com/spring-projects/eclipse-integration-gradle).
and restart Eclipse.

You can then import the Emojicon project by doing File/Import/Gradle/Gradle Project
and the clicking the Browse button to point to the Emojicon directory.  Click
on Build Model, and then you can put a checkbox next to "example" and finish the import.  This
will show up as "example" in your Eclipse workspace.  You can rename it by right clicking on it
and choosing Refactor/Rename.

If it complains that "/gen already exists but is not a source folder", right click on the
gen folder and then BuildPath/SourceFolder.

## Acknowledgements

Emojicon is using emojis graphics from [emoji-cheat-sheet.com](https://github.com/arvida/emoji-cheat-sheet.com/tree/master/public/graphics/emojis).


## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/rockerhieu/emojicon/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.