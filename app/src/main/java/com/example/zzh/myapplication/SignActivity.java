package com.example.zzh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignActivity extends AppCompatActivity {

    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }


    private boolean isPhoneNumber(String phoneStr) {
        //定义电话格式的正则表达式
        String regex = "^((13[0-9])|(14[^4,\\D])|(15[^4,\\D])|(17[^4,\\D])|(18[0,5-9]))\\d{8}$";
        //设定查看模式
        Pattern p = Pattern.compile(regex);
        //判断Str是否匹配，返回匹配结果
        Matcher m = p.matcher(phoneStr);
        return m.find();
    }

    protected void success_show(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View lView1=getLayoutInflater().inflate(R.layout.success_activity,null);
        builder.setView(lView1);
        TextView textView_sign_name_show=(TextView)findViewById(R.id.sign_name_show);
        //textView_sign_name_show.setText("");
        builder.setPositiveButton("确定",null);
        builder.create().show();
    }
    public static void actionstart(Context context,String login_name,String login_passwd){
        Intent intent=new Intent(context,SignActivity.class);
        intent.putExtra("login_name",login_name);
        intent.putExtra("login_passwd",login_passwd);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_activity);
        //如果注册界面输入录了用户名，接收
        Intent getIn = getIntent();
        String login_name = getIn.getStringExtra("login_name");
        String login_passwd = getIn.getStringExtra("login_passwd");
        final EditText editText_sign_name = (EditText) findViewById(R.id.sign_name);
        editText_sign_name.setText(login_name);
        editText_sign_name.setSelection(login_name.length());


        //用户名判空
        editText_sign_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if(editText_sign_name.getText().toString().isEmpty()){
                        editText_sign_name.setError("用户名不能为空");
                    }
                }
            }
        });


        //判断邮箱地址是否合法
        final EditText editText_sign_sign_email=(EditText)findViewById(R.id.sign_email);
        editText_sign_sign_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!isEmail(editText_sign_sign_email.getText().toString())){
                        editText_sign_sign_email.setError("请输入正确的邮箱地址");
                    }
                }
            }
        });



        //判断电话格式是否合法
        final EditText editText_sign_sign_phone=(EditText)findViewById(R.id.sign_phone);
        editText_sign_sign_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (!isPhoneNumber(editText_sign_sign_phone.getText().toString())){
                        editText_sign_sign_phone.setError("请输入正确的电话号码");
                    }
                }
            }
        });


        //判断两次密码是否一致
        final EditText editText_editText_sign_sign_passwd=(EditText)findViewById(R.id.sign_passwd);
        final EditText editText_editText_sign_sign_passwd_two=(EditText)findViewById(R.id.sign_passwd_two);
        editText_editText_sign_sign_passwd_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String psw=editText_editText_sign_sign_passwd.getText().toString();
                String psw_two=editText_editText_sign_sign_passwd_two.getText().toString();
                if (!hasFocus){
                    if (!(psw.equals(psw_two))){
                        editText_editText_sign_sign_passwd_two.setError("两次密码不一致！");
                    }
                }
            }
        });
        //注册跳转
        Button button_sign=(Button)findViewById(R.id.read_in);
        button_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                success_show(v);
            }
        });

    }
}
