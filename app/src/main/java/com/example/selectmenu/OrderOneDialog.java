package com.example.selectmenu;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderOneDialog extends Dialog {
    public enum ButtonID {BUTTON_NONE ,BUTTON_OK, BUTTON_CANCEL};

    public int mNum = 0;
    public ButtonID mBtnClicked = ButtonID.BUTTON_NONE;

    public OrderOneDialog(Context context,String tvnum){
        super(context);
        setContentView(R.layout.orderonedialog);
        setCancelable(true);

        final TextView tvOrderNum = (TextView)findViewById(R.id.tvOrderNum);
        Button btnDecr = (Button)findViewById(R.id.btnSub);
        final Button btnIncr = (Button)findViewById(R.id.btnAdd);
        Button btnOk = (Button)findViewById(R.id.order_dialog_ok);
        Button btnCancel = (Button)findViewById(R.id.order_dialog_cancel);
        tvOrderNum.setText(tvnum);

        Button.OnClickListener buttonListener = new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                mNum = Integer.parseInt(tvOrderNum.getText().toString());
                switch (v.getId()){
                    case R.id.btnSub:
                        if(mNum<=0) break;
                        else{
                            mNum --;
                            tvOrderNum.setText(""+mNum);
                            break;
                        }
                    case R.id.btnAdd:
                        mNum++;
                        tvOrderNum.setText(""+mNum);
                        break;
                    case R.id.order_dialog_ok:
                        mBtnClicked = ButtonID.BUTTON_OK;
                        dismiss();
                        break;
                    case R.id.order_dialog_cancel:
                        mBtnClicked = ButtonID.BUTTON_CANCEL;
                        dismiss();
                        break;
                }
            }
        };

        btnDecr.setOnClickListener(buttonListener);
        btnIncr.setOnClickListener(buttonListener);
        btnOk.setOnClickListener(buttonListener);
        btnCancel.setOnClickListener(buttonListener);
    }


}
