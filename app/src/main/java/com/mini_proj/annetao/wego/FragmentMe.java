package com.mini_proj.annetao.wego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by huangweiping on 16/7/10.
 */
public class FragmentMe extends Fragment implements View.OnClickListener{

    private CircleImageView imageView;
    private TextView userName;
    private TextView birthday;
    private TextView sex;
    private TextView tags;

    private TextView signOutBtn;

    private MaterialDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.fragment_me, container, false);

        imageView = (CircleImageView) messageLayout.findViewById(R.id.image);
        if (User.getInstance().getAvatorUrl() != null) Picasso.with(getActivity()).load(User.getInstance().getAvatorUrl()).into(imageView);
        userName = (TextView) messageLayout.findViewById(R.id.name);
        userName.setText(User.getInstance().getName());
        sex = (TextView) messageLayout.findViewById(R.id.sex);
        String sexString = "男";
        switch (User.getInstance().getGender()) {
            case 0:
                sexString = "男";
                break;
            case 1:
                sexString = "女";
                break;
            case 2:
                sexString = "保密";
                break;
        }
        sex.setText(sexString);
        birthday = (TextView) messageLayout.findViewById(R.id.birthday);
        tags = (TextView) messageLayout.findViewById(R.id.tags);
        tags.setText(User.getInstance().getTagShowString());

        signOutBtn = (TextView) messageLayout.findViewById(R.id.sign_out);
        signOutBtn.setOnClickListener(this);

        return messageLayout;
    }

    public void updateByUserInfo(){
        userName.setText(User.getInstance().getName());
        String sexString = "男";
        switch (User.getInstance().getGender()) {
            case 0:
                sexString = "男";
                break;
            case 1:
                sexString = "女";
                break;
            case 2:
                sexString = "保密";
                break;
        }
        sex.setText(sexString);
        birthday.setText(User.getInstance().getYear()+"-"+User.getInstance().getMonth()+"-"+User.getInstance().getDay());
        tags.setText(User.getInstance().getTagShowString());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_out:
                //TODO 登出数据处理
                dialog = new MaterialDialog.Builder(getContext())
                        .title("退出登录中")
                        .content("请稍候...")
                        .cancelable(false)
                        .progress(true, 0)
                        .show();
                signOutBtn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog != null) dialog.dismiss();
                        User.getInstance().setLogin(false);
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                    }
                }, 2000);
                break;

        }
    }

}
