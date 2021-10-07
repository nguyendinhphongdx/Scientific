package com.example.scientificresearch.Ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.Model.Subject.ResponseModelSubject;
import com.example.scientificresearch.Model.Subject.Subject;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Server.ApiService.StudentService;
import com.example.scientificresearch.Server.Config;
import com.example.scientificresearch.Ui.login.LoginActivity;
import com.example.scientificresearch.utils.ConvertData;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    TextView tv_username,idUser,txtTotalClass,txtMark,rlLogout;
    EditText edtAge,edtCity,edtMail;
    List<Subject> subjects = Store.getSubject();
    CircularImageView img_profile;
    ConstraintLayout progess_Pro;
    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_profile, container, false);
        progess_Pro = view.findViewById(R.id.progess_Pro);
        callApi(view);
        return view;
    }

    private void callApi(View view) {
        getAllSubject(view);
    }

    private void getAllSubject(View view) {
        progess_Pro.setVisibility(View.VISIBLE);
        StudentService.studentService.getAllSubject(Store.getCurentUser().getID()).enqueue(new Callback<ResponseModelSubject>() {
            @Override
            public void onResponse(Call<ResponseModelSubject> call, Response<ResponseModelSubject> response) {
                if(response.isSuccessful()){
                    List<Subject> listSubject = response.body().getData();
                    Store.setSubject(listSubject);
                    subjects = listSubject;
                    setViews(view);
                    setUp();
                    setListener();
                    Log.d("HOME >>>", "onResponse:  "+response.message());
                }else{
                    Log.d("HOME >>>", "onResponse:  error");
                }
                progess_Pro.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseModelSubject> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
                progess_Pro.setVisibility(View.GONE);
            }
        });
    }

    private void setViews(View view) {
        tv_username = view.findViewById(R.id.tv_username);
        txtTotalClass = view.findViewById(R.id.txtTotalClass);
        idUser = view.findViewById(R.id.idUser);
        txtMark= view.findViewById(R.id.txtMark);
        edtAge = view.findViewById(R.id.edtAge);
        edtCity= view.findViewById(R.id.edtCity);
        edtMail = view.findViewById(R.id.edtMail);
        img_profile = view.findViewById(R.id.img_profile);
        rlLogout = view.findViewById(R.id.rlLogout);

    }
    private void setListener() {
        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void setUp() {
        tv_username.setText(Store.getCurentUser().getName());
        idUser.setText(Store.getCurentUser().getID());
        txtMark.setText(ConvertData.avgMark(subjects)+"");
        edtAge.setText(Store.getCurentUser().getAge()+"");
        txtTotalClass.setText(subjects.size()+"");
        edtMail.setText(Store.getCurentUser().getEmail());
        String url = Config.url+"/uploads/students/"+Store.getCurentUser().getImage();
        //http://localhost:5050/uploads/students/1615216633070.jpg
        Picasso.get().load(url).into(img_profile);
    }

}
