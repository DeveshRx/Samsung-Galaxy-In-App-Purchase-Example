package com.samsung.android.sdk.iap.lib.dialog;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.samsung.android.sdk.iap.lib.R;

// --------------
// Title
// --------------
// Message
// --------------
// OK button
// --------------

public class BaseDialogFragment extends DialogFragment implements View.OnClickListener {
    private String title;
    private String message;
    private Runnable onClickRunnable = null;
    private Activity activity = null;
    private int dialogWidth;

    private static final String KEY_DIALOG_TITLE = "dialog_title";
    private static final String KEY_DIALOG_MSG = "dialog_message";
    private static final String TAG = "BaseDialogFragment";

    public static BaseDialogFragment newInstance(String title, String message) {
        Log.d(TAG, "newInstance");
        Bundle bundle = new Bundle();
        if (bundle == null) {
            Log.e(TAG, "Fail to create a Bundle instance.");
            return null;
        }
        bundle.putString(KEY_DIALOG_TITLE, title);
        bundle.putString(KEY_DIALOG_MSG, message);
        BaseDialogFragment fragment = new BaseDialogFragment();
        if (fragment == null) {
            Log.e(TAG, "Fail to create a BaseDialogFragment instance.");
            return null;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            Log.e(TAG, "No argument.");
            return;
        }
        this.title = bundle.getString(KEY_DIALOG_TITLE);
        this.message = bundle.getString(KEY_DIALOG_MSG);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        dialogWidth = getDialogWidth();
        getDialog().getWindow().setLayout(dialogWidth, ActionBar.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog");

        View view = getActivity().getLayoutInflater().inflate(R.layout.base_dialog, null);
        ((TextView) view.findViewById(R.id.dialog_title)).setText(title);
        ((TextView) view.findViewById(R.id.dialog_message)).setText(message);
        ((Button) view.findViewById(R.id.dialog_ok_btn)).setText(android.R.string.ok);
        view.findViewById(R.id.dialog_ok_btn).setOnClickListener(this);

        Dialog dialog = new Dialog(getActivity(), R.style.Theme_DialogTransparent);
        if (dialog == null) {
            Log.e(TAG, "Fail to create a Dialog instance.");
            return null;
        }
        dialog.setContentView(view);
        dialog.setCancelable(activity != null ? true : false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.65f);

        return dialog;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        dialogWidth = getDialogWidth();
        getDialog().getWindow().setLayout(dialogWidth, ActionBar.LayoutParams.WRAP_CONTENT);
    }

    public void setOnClickListener(final Runnable onClickRunnable) {
        Log.d(TAG, "setOnClickListener");
        this.onClickRunnable = onClickRunnable;
    }

    public void setFinishActivity(Activity activity) {
        Log.d(TAG, "setFinishActivity");
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick");
        if (onClickRunnable != null) {
            onClickRunnable.run();
        }
        if (activity != null) {
            activity.finish();
        }
        dismiss();
    }

    private int getDialogWidth() {
        TypedValue outValue = new TypedValue();
        getResources().getValue(R.integer.dialog_width_percentage, outValue, true);
        float ratio = outValue.getFloat();
        int width = (int) (getResources().getDisplayMetrics().widthPixels * ratio);
        Log.d(TAG, "Ratio: " + ratio + ", DialogWidth: " + width);
        return width;
    }
}