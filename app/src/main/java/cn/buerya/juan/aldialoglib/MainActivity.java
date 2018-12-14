package cn.buerya.juan.aldialoglib;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import cn.buerya.juan.aldialoglib.util.DialogUtil;
import cn.buerya.juan.dialoglibrary.ADialog;
import cn.buerya.juan.dialoglibrary.IDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDefaultDialog(View view) {
        DialogUtil.createDefaultDialog(
                MainActivity.this,
                "我是标题",
                "默认内容",
                "知道了",
                new IDialog.OnClickListener() {
                    @Override
                    public void onViewClick(IDialog dialog) {
                        dialog.dismiss();
                    }
                }
        );
    }

    public void showDefaultDialogTwo(View view) {
        DialogUtil.createDefaultDialog(
                MainActivity.this,
                "标题",
                "两个按钮的默认Dialog",
                "取消",
                new IDialog.OnClickListener() {
                    @Override
                    public void onViewClick(IDialog dialog) {
                        dialog.dismiss();
                    }
                },
                "知道了",
                new IDialog.OnClickListener() {
                    @Override
                    public void onViewClick(IDialog dialog) {
                        dialog.dismiss();
                    }
                }
        );
    }

    public void showBaseUseDialog(View view) {
        new ADialog.Builder(this)
                .setDialogView(R.layout.layout_dialog)
                .setScreenWidthP(0.85f) //设置屏幕宽度比例 0.0f-1.0f
                .setGravity(Gravity.CENTER)//设置Gravity
                .setWindowBackgroundP(0.2f)//设置背景透明度 0.0f-1.0f 1.0f完全不透明
                .setCancelable(true)//设置是否屏蔽物理返回键 true不屏蔽  false屏蔽
                .setCancelableOutSide(true)//设置dialog外点击是否可以让dialog消失
                .setOnDismissListener(new IDialog.OnDismissListener() {
                    @Override
                    public void onDismiss(IDialog dialog) {
                        //监听dialog dismiss的回调
                        Toast.makeText(getBaseContext(), "dismiss回调", Toast.LENGTH_SHORT).show();
                    }
                })
                .setBuildChildListener(new IDialog.OnBuildListener() {
                    //设置子View
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        //dialog: IDialog
                        //view： DialogView
                        //layoutRes :Dialog的资源文件 如果一个Activity里有多个dialog 可以通过layoutRes来区分
                        final EditText editText = view.findViewById(R.id.et_content);
                        Button btn_ok = view.findViewById(R.id.btn_ok);
                        btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String editTextStr = null;
                                if (!TextUtils.isEmpty(editText.getText())) {
                                    editTextStr = editText.getText().toString();
                                }
                                dialog.dismiss();
                                Toast.makeText(getBaseContext(), editTextStr, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).show();
    }
}
