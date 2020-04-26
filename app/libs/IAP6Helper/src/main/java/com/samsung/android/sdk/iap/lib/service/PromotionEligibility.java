package com.samsung.android.sdk.iap.lib.service;

import android.content.Context;
import android.util.Log;

import com.samsung.android.sdk.iap.lib.R;
import com.samsung.android.sdk.iap.lib.helper.HelperDefine;
import com.samsung.android.sdk.iap.lib.helper.IapHelper;
import com.samsung.android.sdk.iap.lib.listener.OnGetPromotionEligibilityListener;
import com.samsung.android.sdk.iap.lib.vo.PromotionEligibilityVo;

import java.util.ArrayList;

/**
 * Created by sangbum7.kim on 2018-02-28.
 */

public class PromotionEligibility extends BaseService {
    private static final String TAG = PromotionEligibility.class.getSimpleName();

    private static OnGetPromotionEligibilityListener mOnGetPromotionEligibilityListener = null;
    private static String mProductId = "";
    protected ArrayList<PromotionEligibilityVo> mPromotionEligibility = null;

    public PromotionEligibility(IapHelper _iapHelper, Context _context, OnGetPromotionEligibilityListener _onGetPromotionEligibilityListener) {
        super(_iapHelper, _context);
        mOnGetPromotionEligibilityListener = _onGetPromotionEligibilityListener;
    }

    public static void setProductId(String _productId) {
        mProductId = _productId;
    }

    public void setPromotionEligibility(ArrayList<PromotionEligibilityVo> _promotionEligibility) {
        this.mPromotionEligibility = _promotionEligibility;
    }

    @Override
    public void runServiceProcess() {
        Log.v(TAG, "runServiceProcess");
        if (mIapHelper != null) {
            if (mIapHelper.safeGetPromotionEligibility(this,
                    mProductId,
                    mIapHelper.getShowErrorDialog()) == true) {
                return;
            }
        }
        mErrorVo.setError(HelperDefine.IAP_ERROR_INITIALIZATION, mContext.getString(R.string.mids_sapps_pop_unknown_error_occurred));
        onEndProcess();
    }

    @Override
    public void onReleaseProcess() {
        Log.v(TAG, "PromotionEligibility.onReleaseProcess");
        try {
            if (mOnGetPromotionEligibilityListener != null)
                mOnGetPromotionEligibilityListener.onGetPromotionEligibility(mErrorVo, mPromotionEligibility);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}
