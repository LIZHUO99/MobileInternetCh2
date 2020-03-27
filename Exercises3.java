package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */

public class Exercises3 extends AppCompatActivity implements MyAdapter.ListItemClickListener {

    private static final String TAG = "homework";

    private MyAdapter mAdapter;
    private RecyclerView messageListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_tips);
         
         messageListView = findViewById(R.id.rv_list);

         LinearLayoutManager layoutManager = new LinearLayoutManager(this);
         layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         messageListView.setLayoutManager(layoutManager);

         messageListView.setHasFixedSize(true);


         List<Message> messages = null;
         //load data from assets/data.xml
         try {
             InputStream assetInput = getAssets().open("data.xml");
             messages = PullParser.pull2xml(assetInput);

         } catch (Exception exception) {
             exception.printStackTrace();
         }
        mAdapter = new MyAdapter(messages, this);
        messageListView.setAdapter(mAdapter);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d(TAG, "onListItemClick: ");
        Intent intent = new Intent(this, ChatRoom.class);
        Bundle bundle = new Bundle();
        bundle.putInt("index", clickedItemIndex);
        intent.putExtra("num", bundle);  //附加index信息
        startActivity(intent);
    }
}

