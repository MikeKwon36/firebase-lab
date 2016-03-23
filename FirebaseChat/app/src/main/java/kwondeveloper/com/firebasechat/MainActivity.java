package kwondeveloper.com.firebasechat;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import java.util.LinkedList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionbar;
    private EditText mEditText;
    private Button mButton;
    private Firebase mFirebaseRootRef;
    private Firebase mFirebaseMessageRef;
    private ListView mListView;
    private LinkedList<String> mMessages;
    private FirebaseListAdapter<String> mFirebaseAdapter;
    private String mUserName = "User";
    private Random mUserNameGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mButton = (Button) findViewById(R.id.submit_button);
        mListView = (ListView) findViewById(R.id.list);
        mMessages = new LinkedList<String>();
        mToolbar = (Toolbar) findViewById(R.id.ActionBar);
        mFirebaseRootRef = new Firebase("https://dazzling-fire-7574.firebaseio.com/");
        mFirebaseMessageRef = mFirebaseRootRef.child("messages");
        mUserNameGenerator = new Random();

        mUserName += mUserNameGenerator.nextInt(1000);

        setSupportActionBar(mToolbar);
        mActionbar = getSupportActionBar();
        mActionbar.setTitle("Chatting as " + mUserName);

        mFirebaseAdapter = new FirebaseListAdapter<String>(
                this,String.class,android.R.layout.simple_list_item_1,mFirebaseMessageRef) {
            @Override
            protected void populateView(View view, String s, int i) {
                mMessages.add(0,s);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        };

        mListView.setAdapter(mFirebaseAdapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseMessageRef.push().setValue(mUserName + ": " + mEditText.getText().toString());
                mEditText.setText("");
            }
        });
    }
}
