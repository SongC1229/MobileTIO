package com.sning.mtio.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sning.mtio.Database.Student;
import com.sning.mtio.Database.memoDbHelper;
import com.sning.mtio.Database.userDbhelper;
import com.sning.mtio.R;
import com.sning.mtio.Widget.PhotoPopupWindow;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView avatarImageView;
    //
    private PhotoPopupWindow photoPopupWindow;
    //图片路径
    public String iconPath;

    //拍照或选择图片进行切割
    private static final int REQUEST_IMAGE_GET = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SMALL_IMAGE_CUTTING = 2;
    private static final int REQUEST_BIG_IMAGE_CUTTING = 3;

    private RelativeLayout w_rl_customize, w_rl_clear, w_rl_memo, w_rl_safe, w_rl_connect,w_rl_about,w_rl_undo;

    private Student student;
    private String userId;
    private TextView w_tv_setting_user_name,w_tv_setting_user_grade,w_tv_setting_user_class;

    //个人信息
    private EditText w_et_name,w_et_user_phone,w_et_user_email,w_et_user_major,w_et_user_sex,w_et_user_year,w_et_user_class;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        userId = LoginActivity.getCurrentUser().getId();
        initView();
        changeImage();

    }

    public void initView(){
        avatarImageView = findViewById(R.id.w_iv_setting_head);
        w_rl_customize = findViewById(R.id.w_rl_customize);
        w_rl_clear = findViewById(R.id.w_rl_clear);
        w_rl_memo = findViewById(R.id.w_rl_memo);
        w_rl_safe = findViewById(R.id.w_rl_safe);
        w_rl_connect = findViewById(R.id.w_rl_connect);
        w_rl_about = findViewById(R.id.w_rl_about);
        w_rl_undo = findViewById(R.id.w_rl_undo);

        w_rl_customize.setOnClickListener(this);
        w_rl_clear.setOnClickListener(this);
        w_rl_memo.setOnClickListener(this);
        w_rl_safe.setOnClickListener(this);
        w_rl_connect.setOnClickListener(this);
        w_rl_about.setOnClickListener(this);
        w_rl_undo.setOnClickListener(this);

        w_tv_setting_user_name = findViewById(R.id.w_tv_setting_user_name);
        w_tv_setting_user_grade = findViewById(R.id.w_tv_setting_user_grade);
        w_tv_setting_user_class = findViewById(R.id.w_tv_setting_user_class);

        //数据库查询user
        userDbhelper db=new userDbhelper(this);
        Log.i("TAG",userId);
        student=db.selectById(userId);
        Log.i("TAG",student.getName());
        //获取信息
        String username = student.getName();
        Log.i("TAG",student+"1111");

        w_tv_setting_user_name.setText(student.getName());
        w_tv_setting_user_grade.setText(student.getYear()+"级");
        w_tv_setting_user_class.setText(student.getClas());

        glideImage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.w_rl_customize:
                //Toast.makeText(this,"customize",Toast.LENGTH_SHORT).show();
                personalCustomize();
                break;
            case R.id.w_rl_clear:
                clearCache();
                break;
            case R.id.w_rl_memo:
                //Toast.makeText(this,"memo",Toast.LENGTH_SHORT).show();
                deleteAllMemo();
                break;
            case R.id.w_rl_safe:
                changePassword();
//                Toast.makeText(this,"很安全啦",Toast.LENGTH_SHORT).show();
                break;
            case R.id.w_rl_connect:
                Toast.makeText(this,"@上分小队",Toast.LENGTH_LONG).show();
                break;
            case R.id.w_rl_about:
                Toast.makeText(this,"不知道说什么，祝大家猪年快乐吧",Toast.LENGTH_LONG).show();
                break;
            case R.id.w_rl_undo:
                loginOff();
                break;
        }
    }

    //注销登录
    private void loginOff() {
        Toast.makeText(SettingActivity.this,"注销",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    //修改密码
    private void changePassword() {
        AlertDialog.Builder chanageDialog = new AlertDialog.Builder(this,R.style.s_nobackdialog);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.change_password_dialog,null);
        chanageDialog.setTitle("修改密码");
        chanageDialog.setView(dialogView);
        final EditText w_et_old,w_et_new,w_et_confirm;
        w_et_old = dialogView.findViewById(R.id.w_et_old);
        w_et_new = dialogView.findViewById(R.id.w_et_new);
        w_et_confirm = dialogView.findViewById(R.id.w_et_confirm);

        chanageDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String uid = LoginActivity.getCurrentUser().getId();
                String old_pwd = w_et_old.getText().toString();
                String new_pwd = w_et_new.getText().toString();
                String confirm_pwd = w_et_confirm.getText().toString();
                userDbhelper db=new userDbhelper(SettingActivity.this);
                if(!new_pwd.equals(confirm_pwd)){
                    Toast.makeText(SettingActivity.this,"输入的新密码不一致",Toast.LENGTH_LONG).show();
                }else if( db.chagePwd(uid,old_pwd,new_pwd)!=1){
                    Toast.makeText(SettingActivity.this,"输入的旧密码有误",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SettingActivity.this,"修改成功",Toast.LENGTH_LONG).show();
                }
            }
        }).show();

    }

    //个人信息
    public void personalCustomize(){
        AlertDialog.Builder modifyDialog = new AlertDialog.Builder(this,R.style.s_nobackdialog);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.modify_dialog,null);
        modifyDialog.setTitle("个人信息");
        modifyDialog.setView(dialogView);
        Log.i("TAG",student.getName()+"name");

        //个人信息
        w_et_name = dialogView.findViewById(R.id.w_et_name);
        w_et_user_phone = dialogView.findViewById(R.id.w_et_user_phone);
        w_et_user_email = dialogView.findViewById(R.id.w_et_user_email);
        w_et_user_major = dialogView.findViewById(R.id.w_et_user_major);
        w_et_user_sex = dialogView.findViewById(R.id.w_et_user_sex);
        w_et_user_year = dialogView.findViewById(R.id.w_et_user_year);
        w_et_user_class = dialogView.findViewById(R.id.w_et_user_class);

        w_et_name.setText(student.getName());
        w_et_user_phone.setText(student.getPhone());
        w_et_user_email.setText(student.getMail());
        w_et_user_major.setText(student.getMajor());
        w_et_user_sex.setText(student.getSex()== 1 ?"男":"女");
        w_et_user_year.setText(student.getYear()+"级");
        w_et_user_class.setText(student.getClas());

        modifyDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Log.i("TAG","个性化！！！！！！！！！");
                String phone = w_et_user_phone.getText().toString();
                String email = w_et_user_email.getText().toString();
                student.setPhone(phone);
                student.setMail(email);
                userDbhelper db=new userDbhelper(SettingActivity.this);
                db.updateStu(student);
            }
        }).show();

    }

    //一键删除备忘录
    public void deleteAllMemo(){
        AlertDialog.Builder deleteAllNoteDialog = new AlertDialog.Builder(this,R.style.s_nobackdialog);
        deleteAllNoteDialog.setTitle("清空备忘录");
        deleteAllNoteDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                memoDbHelper myDbHeper=new memoDbHelper(SettingActivity.this);
                myDbHeper.delByUserId(userId);
            }
        }).show();
    }

    //一键清除缓存
    public void clearCache(){
        AlertDialog.Builder deleteAllNoteDialog = new AlertDialog.Builder(this,R.style.s_nobackdialog);
        deleteAllNoteDialog.setTitle("清除缓存");
        deleteAllNoteDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SettingActivity.this,"清除完啦",Toast.LENGTH_SHORT).show();
            }
        }).show();
    }


    //加载头像
    public void glideImage(){
        //图片加载
//        Glide.with(this).load(R.drawable.pic1)
//                .bitmapTransform(new BlurTransformation(this,25),new CenterCrop(this))
//                .into(blurImageView);

//        Glide.with(this).load(R.drawable.pic1).
        Glide.with(this).load(R.drawable.pic1).into(avatarImageView);
//                .bitmapTransform(new CropCircleTransformation(this))
//                .into(avatarImageView);
    }
    //换头像
    public void changeImage(){
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoPopupWindow = new PhotoPopupWindow(SettingActivity.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //文件权限申请
                        if(ContextCompat.checkSelfPermission(SettingActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                            //权限还没授予，进行申请
                            ActivityCompat.requestPermissions(SettingActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},200);//申请requestcode 200
                        }else{
                            //如果权限已经申请过了，直接对图片进行选择
                            photoPopupWindow.dismiss();
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            //判断系统中是否有处理该Intent的Activity
                            if(intent.resolveActivity(getPackageManager())!=null){
                                startActivityForResult(intent,REQUEST_IMAGE_GET);
                            }else{
                                Toast.makeText(SettingActivity.this,"未找到图片查看器",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //拍照及文件权限申请
                        if(ContextCompat.checkSelfPermission(SettingActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                            //权限还没授予，进行申请
                            ActivityCompat.requestPermissions(SettingActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},300);   //申请的requestCode 300
                        }else{
                            //权限已经申请，直接拍照
                            photoPopupWindow.dismiss();
                            imageCapture();
                        }
                    }
                });
                View rootView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.activity_setting,null);
                photoPopupWindow.showAtLocation(rootView,Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,150,150);
            }
        });
    }
    //判断系统及拍照
    private void imageCapture() {
        Intent intent;
        Uri pictureUri;
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .getAbsolutePath() + File.separator +"cameraDemo";
        Log.i("TAG","filePath:"+filePath);
//        String filePath = Environment.getExternalStorageDirectory()+"/"+IMAGE_FILE_NAME;
        File storageDir = new File(filePath);
        if(storageDir.exists()){
            Log.i("info","文件夹已经存在");
        }else{
            storageDir.mkdirs();
            Log.i("info","文件夹创建");
        }
        File pictureFile = null;
        try {
            pictureFile = File.createTempFile("bigIcon",".jpg",storageDir);
            //图片文件名
            String name = pictureFile.getName();
            iconPath = filePath+"/"+name;
            Log.i("TAG",iconPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        /storage/emulated/0/Pictures/cameraDemo/bigIcon1852303545.jpg
//        Log.i("TAG",pictureFile+"111111111111111111");

        //判断当前系统
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pictureUri = FileProvider.getUriForFile(this,"com.example.a82400.mobiletio.fileProvider",pictureFile);
//            content://com.example.whaxx.mtio.fileProvider/hm_Pictures/bigIcon1852303545.jpg
            Log.i("TAG",pictureUri+"123456789");
        }else{
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictureUri = Uri.fromFile(pictureFile);
            Log.i("TAG",pictureUri+"123456789");
        }
        //去拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
        Log.i("TAG",intent+"111111111111111111111111");
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }
    //处理回调结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //回调成功
        switch (requestCode){
            //小图切割
            case REQUEST_SMALL_IMAGE_CUTTING:
                if(data!=null){
                    setPicToView(data);
                }
                break;
            //相册选择
            case REQUEST_IMAGE_GET:
                startSmallPhotoZoom(data.getData());
                break;
            case REQUEST_IMAGE_CAPTURE:
                File temp = new File(iconPath);
                startBigPhotoZoom(temp);
                break;
            case REQUEST_BIG_IMAGE_CUTTING:
                if(data!=null){
                    setBigToView();
                }
                break;
        }
    }
    //小图模式切割图片,此方式直接返回截图后的bitmap，返回的图片比较小
    public void startSmallPhotoZoom(Uri uri){
        //系统自带的图片裁切功能
        Intent intent = new Intent("com.android.camera.action.CROP");
        //对传输的数据和类型进行设置
        intent.setDataAndType(uri,"image/*");
        //发送裁剪信号
        intent.putExtra("crop",true);
        //x，y方向上的比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        //裁剪区的宽和高
        intent.putExtra("outputX",300);
        intent.putExtra("outputY",300);
        if(Build.MODEL.contains("MI")){
            Uri uritempFile = Uri.parse("file://"+"/"+Environment.getExternalStorageDirectory().getPath()+"/"+"small.jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uritempFile);
            intent.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.toString());
        }else{
            //是否保留比例
            intent.putExtra("scale",true);
            //是否将数据保留在bitmap中返回
            intent.putExtra("return-data",true);
        }
        startActivityForResult(intent,REQUEST_SMALL_IMAGE_CUTTING);
    }
    //小图模式中保存图片后，设置到视图中
    public void setPicToView(Intent data){
        Bundle extras = data.getExtras();
        if(extras!=null){
            Bitmap photo = extras.getParcelable("data");    //直接获得内存中保存的bitmap
            //创建smallIcon 文件夹
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                String storage = Environment.getExternalStorageDirectory().getPath();
                File dirFile = new File(storage + "/smallIcon");
                if(!dirFile.exists()){
                    if(!dirFile.mkdirs()){
                        Log.e("TAG","文件夹创建失败");
                    }else{
                        Log.e("TAG","文件夹创建成功");
                    }
                }
                File file =new File(dirFile,System.currentTimeMillis()+".jpg");
                //保存图片
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
//            //加载图片
//            Glide.with(this).load(bytes)
//                    .bitmapTransform(new BlurTransformation(this,25),new CenterCrop(this))
//                    .into(blurImageView);

            Glide.with(this).load(bytes).into(avatarImageView);
//                    .bitmapTransform(new CropCircleTransformation(this))
//                    .into(avatarImageView);
        }
    }
    //大图模式切割图
    public void startBigPhotoZoom(File file){
        //在小图返回模式中，我们将 return-data 设置为了“true”，因此会在内存中直接返回一个 Bitmap，由于内存的原因，它将会是一个模糊的缩略图。
        //如果将 return-data 设置为“false”，那么在 onActivityResult() 的 Intent 数据中你将不会接收到任何 Bitmap，相反，我们需要将 MediaStore.EXTRA_OUTPUT 关联到一个 Uri，此 Uri 是用来存放 Bitmap 的，那么裁剪后的图片就会保存到 sd 卡中。

        Log.i("TAG",file.getName());
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(this,"com.example.a82400.mobiletio.fileProvider", file);
            //content://com.example.whaxx.mtio.fileProvider/hm_Pictures/bigIcon582653553.jpg
            //Log.i("URI",uri+"24");
        } else {
            uri = Uri.fromFile(file);
            //file:///storage/emulated/0/Pictures/cameraDemo/bigIcon-1193188761.jpg
            //Log.i("URI",uri+"22");
        }
        //开始切割
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("crop",true);
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",300);
        intent.putExtra("outputY",300);
        intent.putExtra("scale",true);
        intent.putExtra("return-data",false);   //不直接返回数据
        //截取输出的outputUri， 只能使用 Uri.fromFile，不能用FileProvider.getUriForFile
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));  //返回一个文件
        intent.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.toString());
        //数据Intent { act=com.android.camera.action.CROP dat=file: typ=image/* (has extras) }
        startActivityForResult(intent,REQUEST_BIG_IMAGE_CUTTING);
    }
    //拍照图片设置到视图中
    public void setBigToView(){
        //trim()的作用是去掉字符串两端的多余的空格
        File file = new File(iconPath.trim());
        //图片加载
//        Glide.with(this).load(file)
//                .bitmapTransform(new BlurTransformation(this,25),new CenterCrop(this))
//                .into(blurImageView);
        Glide.with(this).load(file).into(avatarImageView);
//                .bitmapTransform(new CropCircleTransformation(this))
//                .into(avatarImageView);
    }
    //权限回调函数
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 200:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    photoPopupWindow.dismiss();
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    //判断系统中是否有处理该Intent的activity
                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivityForResult(intent,REQUEST_IMAGE_GET);
                    }else {
                        photoPopupWindow.dismiss();
                    }
                }
                break;
            case 300:
                if(grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    photoPopupWindow.dismiss();
                    imageCapture();
                }else{
                    photoPopupWindow.dismiss();
                }
                break;
        }
    }
}
