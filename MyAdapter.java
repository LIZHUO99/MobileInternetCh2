package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MessageViewHolder> {

    private static final String TAG = "MyAdapter";

    private int mNumberItems;

    private List<Message> mMessages;

    private final ListItemClickListener onClickListener;

    private static int viewHolderCount;

    public MyAdapter(List<Message> messages, ListItemClickListener listener) {
        mNumberItems = messages.size();
        mMessages = messages;
        onClickListener = listener;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MessageViewHolder viewHolder = new MessageViewHolder(view);

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder numberViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        numberViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView userNickname;
        private final ImageView officialRobot;
        private final TextView messageDescription;
        private final TextView messageTime;
        private final CircleImageView messageAvatar;

//        private final TextView listItemNumberView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
//            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            userNickname = (TextView) itemView.findViewById(R.id.tv_title);
            officialRobot = (ImageView) itemView.findViewById(R.id.robot_notice);
            messageDescription = (TextView) itemView.findViewById(R.id.tv_description);
            messageTime = (TextView) itemView.findViewById(R.id.tv_time);
            messageAvatar = (CircleImageView) itemView.findViewById(R.id.iv_avatar);

            itemView.setOnClickListener(this);
        }

        public void bind(int position) {

            Message message = mMessages.get(position);

            messageDescription.setText(message.getDescription());
            userNickname.setText(message.getTitle());
            messageTime.setText(message.getTime());

            switch (message.getIcon()){
                case "TYPE_ROBOT":
                    messageAvatar.setImageResource(R.drawable.session_robot);
                    break;
                case "TYPE_GAME":
                    messageAvatar.setImageResource(R.drawable.icon_micro_game_comment);
                    break;
                case "TYPE_STRANGER":
                    messageAvatar.setImageResource(R.drawable.session_stranger);
                    break;
                case "TYPE_SYSTEM":
                    messageAvatar.setImageResource(R.drawable.session_system_notice);
                    break;
                case "TYPE_USER":
                    messageAvatar.setImageResource(R.drawable.icon_girl);
            }

            if (message.isOfficial()){
                officialRobot.setVisibility(View.VISIBLE);
            }
            else{
                officialRobot.setVisibility(View.INVISIBLE);
            }

//            viewHolderIndex.setText(String.format("ViewHolder index: %s", getAdapterPosition()));
//            int backgroundColorForViewHolder = ColorUtils.
//                    getViewHolderBackgroundColorFromInstance(itemView.getContext(), getAdapterPosition() % 10);
//            itemView.setBackgroundColor(backgroundColorForViewHolder);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (onClickListener != null) {
                onClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
