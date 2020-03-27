package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.Queue;
import java.util.LinkedList;
import android.view.ViewGroup;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int getAllChildViewCount(View view) {
        //广度优先搜索，队列
        if (view == null){
            return 0;
        }
        int sum = 0;
        Queue<ViewGroup> queue = new LinkedList<ViewGroup>();
        if (view instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) view;
            queue.offer(viewGroup);
            while(queue.peek() != null){
                viewGroup = queue.poll();
                for (int i = 0; i < viewGroup.getChildCount(); i++){
                    if(viewGroup.getChildAt(i) instanceof ViewGroup){
                        queue.offer((ViewGroup) viewGroup.getChildAt(i));
                    }
                    else{
                        sum++;
                    }
                }
            }
        }
        else{
            sum++;
        }
        return sum;
    }
}
