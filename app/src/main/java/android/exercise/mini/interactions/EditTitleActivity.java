package android.exercise.mini.interactions;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {

  private void hideKeybaord(View v) {
    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_title);

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    // setup - start from static title with "edit" button
    fabStartEdit.setVisibility(View.VISIBLE);
    fabEditDone.setVisibility(View.GONE);
    textViewTitle.setText("Page title here");
    textViewTitle.setVisibility(View.VISIBLE);
    editTextTitle.setText("Page title here");
    editTextTitle.setVisibility(View.GONE);

    // handle clicks on "start edit"

    fabStartEdit.setOnClickListener(v -> {
      /* 1. animate out the "start edit" FAB */
      fabStartEdit.setVisibility(View.INVISIBLE);

      /* 2. animate in the "done edit" FAB */
      fabEditDone.setVisibility(View.VISIBLE);
      ObjectAnimator bounceDone = ObjectAnimator.ofFloat(fabEditDone,
                                                        "translationY",
                                                        -100f,
                                                        0f);
      bounceDone.setDuration(1000);    //1sec
      bounceDone.setInterpolator(new BounceInterpolator());
      bounceDone.setRepeatCount(0);
      bounceDone.start();

      /* 3. hide the static title (text-view) */
      textViewTitle.setVisibility(View.INVISIBLE);

      /* 4. show the editable title (edit-text) */
      editTextTitle.setVisibility(View.VISIBLE);
    });

    // handle clicks on "done edit"
    fabEditDone.setOnClickListener(v -> {
      /* 1. animate out the "done edit" FAB */
      fabEditDone.setVisibility(View.INVISIBLE);

      /* 2. animate in the "start edit" FAB */
      fabStartEdit.setVisibility(View.VISIBLE);
      ObjectAnimator bounceStart = ObjectAnimator.ofFloat(fabStartEdit,
              "translationY",
              -100f,
              0f);
      bounceStart.setDuration(1000);    //1sec
      bounceStart.setInterpolator(new BounceInterpolator());
      bounceStart.setRepeatCount(0);
      bounceStart.start();

      /* 3. take the text from the user's input in the edit-text and put it inside the static
            text-view */
      textViewTitle.setText(editTextTitle.getText().toString());

      /* 4. show the static title (text-view) */
      textViewTitle.setVisibility(View.VISIBLE);

      /* 5. hide the editable title (edit-text) */
      editTextTitle.setVisibility(View.INVISIBLE);

      /* 6. make sure that the keyboard is closed */
      hideKeybaord(fabEditDone);
    });
  }

  @Override
  public void onBackPressed() {
    // BACK button was clicked
    /*
    if user is now editing, tap on BACK will revert the edit. do the following: */
    /* 1. hide the edit-text */

    /* 2. show the static text-view with previous text (discard user's input) */
    /* 3. animate out the "done-edit" FAB */
    /* 4. animate in the "start-edit" FAB */




    /* else, the user isn't editing. continue normal BACK tap behavior to exit the screen.
    call `super.onBackPressed()`

    notice:
    to work with views, you will need to find them first.
    to find views call `findViewById()` in a same way like in `onCreate()`
     */
  }
}