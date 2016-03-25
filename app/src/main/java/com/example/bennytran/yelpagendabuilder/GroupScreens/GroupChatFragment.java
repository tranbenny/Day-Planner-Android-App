package com.example.bennytran.yelpagendabuilder.GroupScreens;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.FirebaseModels.Message;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.Util.Constants;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Calendar;


public class GroupChatFragment extends Fragment {

    private static final String LOG_TAG = GroupChatFragment.class.getSimpleName();

    private Firebase mMessageRef;
    private String primaryUser;
    private ArrayList<Message> messages;

    private ListView mMessagesListView;
    private MessagesAdapter mAdapter;
    private EditText mMessageField;
    private Button mSendButton;

    public GroupChatFragment() {
        // Required empty public constructor
    }


    public static GroupChatFragment newInstance() {
        GroupChatFragment fragment = new GroupChatFragment();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        primaryUser = yelpAgendaBuilder.getInstance().user;
        messages = new ArrayList<Message>();
        mAdapter = new MessagesAdapter(getActivity(), messages, primaryUser);

        mMessageRef = new Firebase(Constants.getGroupInfoURL(yelpAgendaBuilder.getInstance().currentGroup));
        loadMessages();
    }

    // load all messages from backend group
    private void loadMessages() {
        mMessageRef.child("messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);

                messages.add(message);
                mAdapter.addAllMessages(messages);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_chat, container, false);

        mMessagesListView = (ListView) view.findViewById(R.id.lvMessages);
        mMessagesListView.setAdapter(mAdapter);


        mMessageField = (EditText) view.findViewById(R.id.messageText);
        mSendButton = (Button) view.findViewById(R.id.btnSendMessage);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send message to data base and update listview adapter
                String text = mMessageField.getText().toString();
                Calendar c = Calendar.getInstance();
                String timestamp = c.get(Calendar.MONTH)+ "/" + c.get(Calendar.DAY_OF_MONTH)
                        + "/" + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR_OF_DAY) + ":"
                        + c.get(Calendar.MINUTE);
                Message message = new Message(text, primaryUser, timestamp);
                Firebase newMessage = mMessageRef.child("messages").push();
                newMessage.setValue(message);

                mMessageField.setText("");
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mMessageField.getWindowToken(), 0);
            }
        });

        return view;
    }



    public class MessagesAdapter extends BaseAdapter {

        private ArrayList<Message> messages;
        private String user;

        private LayoutInflater mInflater;

        public MessagesAdapter(Activity activity, ArrayList<Message> messages, String user) {
            this.messages = messages;
            this.user = user;

            mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowMessage = mInflater.inflate(R.layout.group_message_list_item, null);
            Message message = messages.get(position);

            TextView messageText = (TextView) rowMessage.findViewById(R.id.messageValue);
            messageText.setText(message.getBody());
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) messageText.getLayoutParams();
            Log.i(LOG_TAG, message.getSender());
            if (message.getSender().equals(user)) {
                Log.i(LOG_TAG, "should be shifted to the right");
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            } else {
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
            messageText.setLayoutParams(params);

            return rowMessage;
        }

        public void addAllMessages(ArrayList<Message> messages) {
            this.messages = messages;
        }
    }

}
