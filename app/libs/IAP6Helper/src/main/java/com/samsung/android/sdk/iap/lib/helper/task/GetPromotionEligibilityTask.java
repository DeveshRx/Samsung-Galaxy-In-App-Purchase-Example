package com.samsung.android.sdk.iap.lib.helper.task;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.samsung.android.iap.IAPConnector;
import com.samsung.android.sdk.iap.lib.R;
import com.samsung.android.sdk.iap.lib.helper.HelperDefine;
import com.samsung.android.sdk.iap.lib.service.PromotionEligibility;
import com.samsung.android.sdk.iap.lib.vo.PromotionEligibilityVo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Asynchronized Task to load a list of items
 */
public class GetPromotionEligibilityTask extends BaseTask {
    public static final String FUNCTION_ID_GET_PROMOTION_ELIGIBILITY = "9004";
    private static final String TAG = GetPromotionEligibilityTask.class.getSimpleName();
    ArrayList<PromotionEligibilityVo> mPromotionEligibility
            = new ArrayList<PromotionEligibilityVo>();
    private String mProductId = "";
    private JSONObject mExtraData = new JSONObject();

    public GetPromotionEligibilityTask
            (
                    PromotionEligibility _baseService,
                    IAPConnector _iapConnector,
                    Context _context,
                    String _productID,
                    boolean _showErrorDialog,
                    int _mode
            ) {
        super(_baseService, _iapConnector, _context, _showErrorDialog, _mode);
        mProductId = _productID;

        _baseService.setPromotionEligibility(mPromotionEligibility);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        JSONObject jsonObject = null;
        try {
            mExtraData.put("itemID", mProductId);
            mExtraData.put("mode", mMode);
            // 1) call getProductsDetails() method of IAPService
            // ---- Order Priority ----
            //  1. if productIds is not empty, the infomations abouts products included in the productIds are returned
            //  2. if productIds is empty, the infomations about all products in this package are returned on a page by page
            // ============================================================
            Bundle bundle = mIapConnector.requestServiceAPI(
                    mPackageName,
                    FUNCTION_ID_GET_PROMOTION_ELIGIBILITY,
                    mExtraData.toString());
            // ============================================================

            // 2) save status code, error string and extra String.
            // ============================================================
            if (bundle != null) {
                mErrorVo.setError(bundle.getInt(HelperDefine.KEY_NAME_STATUS_CODE),
                        bundle.getString(HelperDefine.KEY_NAME_ERROR_STRING));

                mErrorVo.setExtraString(bundle.getString(
                        HelperDefine.KEY_NAME_IAP_UPGRADE_URL));
            } else {
                Log.e(TAG, "Bundle is null");
                mErrorVo.setError(
                        HelperDefine.IAP_ERROR_COMMON,
                        mContext.getString(
                                R.string.mids_sapps_pop_unknown_error_occurred));
            }
            // ============================================================

            // 3) If item list is loaded successfully,
            //    make item list by Bundle data
            // ============================================================
            if (mErrorVo.getErrorCode() == HelperDefine.IAP_ERROR_NONE) {
                if (bundle != null) {
                    String resultObject = bundle.getString(HelperDefine.KEY_NAME_RESULT_OBJECT);
                    if (!TextUtils.isEmpty(resultObject)) {
                        try {
                            jsonObject = new JSONObject(resultObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            jsonObject = null;
                        }
                    } else {
                        jsonObject = null;
                    }

                    if (jsonObject != null) {
                        String historyList = jsonObject.getString("LIST");
                        if (TextUtils.isEmpty(historyList) == false) {
                            JSONArray jsonArray = new JSONArray(historyList);
                            Log.v(TAG, "jsonArray : " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject itemObject = jsonArray.getJSONObject(i);
                                Log.v(TAG, "purchaseProductObject : " + itemObject.toString());
                                PromotionEligibilityVo promotionEligibilityVo = new PromotionEligibilityVo(itemObject);
                                mPromotionEligibility.add(promotionEligibilityVo);
                            }
                        }
                    } else {
                        Log.d(TAG, "Bundle Value 'RESULT_LIST' is null.");
                    }
                }
            }
            // ============================================================
            // 4) If failed, print log.
            // ============================================================
            else {
                Log.e(TAG, "Error : " + mErrorVo.getErrorString());
                return true;
            }
            // ============================================================
        } catch (Exception e) {
            Log.e(TAG, "Exception : " + e.getMessage());
            mErrorVo.setError(
                    HelperDefine.IAP_ERROR_COMMON,
                    mContext.getString(
                            R.string.mids_sapps_pop_unknown_error_occurred));

            e.printStackTrace();
            return false;
        }

        return true;
    }
}
