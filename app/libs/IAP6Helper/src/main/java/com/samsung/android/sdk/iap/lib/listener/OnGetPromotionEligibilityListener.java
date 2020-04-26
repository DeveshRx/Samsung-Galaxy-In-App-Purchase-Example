package com.samsung.android.sdk.iap.lib.listener;

import com.samsung.android.sdk.iap.lib.helper.task.GetOwnedListTask;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.PromotionEligibilityVo;

import java.util.ArrayList;

/**
 * Callback Interface used with
 * {@link GetOwnedListTask}
 */
public interface OnGetPromotionEligibilityListener {
    /**
     * Callback method to check promotion eligibility
     *
     * @param _errorVo
     * @param _promotionEligibility
     */
    void onGetPromotionEligibility(ErrorVo _errorVo, ArrayList<PromotionEligibilityVo> _promotionEligibility);
}
