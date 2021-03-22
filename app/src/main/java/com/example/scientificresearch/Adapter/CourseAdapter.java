package com.example.scientificresearch.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.Course;
import com.example.scientificresearch.Model.History;
import com.example.scientificresearch.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> listCourse;
    private Context mContext;
    public CourseAdapter(List<Course> ListCourse, Context mContext) {
        this.listCourse = ListCourse;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View courseView = inflater.inflate(R.layout.it_course, parent, false);
        CourseAdapter.ViewHolder viewHolder = new CourseAdapter.ViewHolder(courseView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = listCourse.get(position);
        holder.txtNameCourse.setText(course.getName());
        holder.txtClass.setText(course.getClassName());
        holder.txtMark.setText(course.getMark()+"");
    }

    @Override
    public int getItemCount() {
        return listCourse.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private View itemview;
        public TextView txtNameCourse;
        public TextView txtMark;
        public TextView txtClass;
        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            txtNameCourse = itemView.findViewById(R.id.txtNameCourse);
            txtMark = itemView.findViewById(R.id.txtMark);
            txtClass = itemView.findViewById(R.id.txtClass);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Functions.ShowToast(v.getContext(),txtNameCourse.getText()+"");
                }
            });
        }
    }
}
