package com.example.picturesharing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adapter.RecentCourseAdapter;
import com.example.data.RecentTeacherData;
import com.example.data.StudentData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentClassActivity extends Fragment {
    private static final String TAG = "StudentClassActivity";
    private ListView studentClassList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BottomBarActivity bottomBarActivity = (BottomBarActivity)getActivity(); //获取主活动中的对象
        View view = inflater.inflate(R.layout.activity_students_course,container,false);
        studentClassList = view.findViewById(R.id.students_courses);
        StudentData studentData = bottomBarActivity.getStudentData();
        while (studentData.getSize()==0){
            studentData = bottomBarActivity.getStudentData();
        }
        List<Map<String,Object>> studentList = getStudentList(studentData);
        studentClassList.setAdapter(new RecentCourseAdapter(getContext(),studentList));

        return view;
    }
    public List<Map<String,Object>> getStudentList(StudentData studentData){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < (studentData.getRecords()).size(); i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("coursePhoto",studentData.getRecords().get(i).getCoursePhoto());
            map.put("courseName", studentData.getRecords().get(i).getCourseName());
            map.put("collegeName", studentData.getRecords().get(i).getCollegeName());
            map.put("courseId", studentData.getRecords().get(i).getCourseId());
            list.add(map);
        }
        return list;
    }

}
