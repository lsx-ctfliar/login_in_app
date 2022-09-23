package com.example.picturesharing;

import static com.luck.picture.lib.thread.PictureThreadUtils.runOnUiThread;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.CourseAdapter;
import com.example.data.ClassData;
import com.example.util.MyResponse;
import com.example.util.MyUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class HomePageActivity extends Fragment {

    private static final String TAG = "HomePageActivity";
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BottomBarActivity bottomBarActivity = (BottomBarActivity)getActivity(); //获取主活动中的对象
        View view = inflater.inflate(R.layout.activity_home_page,container,false);
        listView = view.findViewById(R.id.courses_list);
        //通过接口获取数据
        ClassData classData = bottomBarActivity.getClassData();
        while (classData.getSize() == 0){
            classData = bottomBarActivity.getClassData();
        }
        if(classData.getRecords()!=null){
            List<Map<String, Object>> classDataList=getClassDataList(classData);
            listView.setAdapter(new CourseAdapter(getActivity(),classDataList));
        }
        else {
            System.out.println(TAG+": 所有课程数据列表为null-------");
        }
        return view;
    }

    public List<Map<String, Object>> getClassDataList(ClassData classData){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < (classData.getRecords()).size(); i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("coursePhoto",classData.getRecords().get(i).getCoursePhoto());
            map.put("courseName", classData.getRecords().get(i).getCourseName());
            map.put("collegeName", classData.getRecords().get(i).getCollegeName());
            map.put("courseId", classData.getRecords().get(i).getCourseId());
            list.add(map);
        }
        return list;
    }

    //接口获得数据
    public void getClassData(int current,int size,ClassData classData){
        String param = "/sign/course/all?current="+current+"&"+"size="+size;
        Request request = MyUtil.get(param);
        System.out.println(request);
        Call call = MyUtil.getCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG,"onFailure"+e.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络问题，课程数据加载!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e(TAG,"onResponse " + response.code());
                String responseJsonString = response.body().string();
                Log.e(TAG,"onResponse " + responseJsonString);
                if(MyResponse.getCode(responseJsonString).equals("200")){
                    ClassData classData1 = MyUtil.getJsonObject(responseJsonString,ClassData.class);
                    Log.e(TAG,"classData1.string " + classData1.toString());
                    classData.setCurrent(classData1.getCurrent());
                    classData.setSize(classData1.getSize());
                    classData.setTotal(classData1.getTotal());
                    classData.setRecords(classData1.getRecords());
                    Log.e(TAG,"classData.string " + classData.toString());
                }
                else {
                    System.out.println(responseJsonString);
                    Toast.makeText(getActivity(), responseJsonString, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}